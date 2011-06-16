package kr.ac.kaist.se.aom.profiler;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class AOMProfiler implements ClassFileTransformer{

	public static void premain(String agentArgument, Instrumentation instrumentation)
	{
		if( agentArgument != null )
		{
			String[] args = agentArgument.split(",");
			accept = args;
			setAcceptDot();
		}
		instrumentation.addTransformer(new AOMProfiler());
	}
	
	private static void setAcceptDot()
	{
		for( int i = 0; i < accept.length ; i++ )
		{
			accept_dot[i] = accept[i].replace('/', '.');
		}
	}
	
	private static final String[] deny = new String[] {"sun/",  "java/", "javax/"};
	private static  String[] accept = new String[] {"org/gjt/sp"};
	private static  String[] accept_dot = new String[accept.length];
	static
	{
		setAcceptDot();
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> clazz, java.security.ProtectionDomain domain,
			byte[] bytes)
	throws IllegalClassFormatException {
	
		for( String acceptPrefix : accept )
		{
			if( className.startsWith(acceptPrefix) )
			{
				return insertLogging(className, clazz, bytes);
			}
		}
		return bytes;
	}
		
	public byte[] insertLogging(String name, Class clazz, byte[] b)
	{
		ClassPool pool = ClassPool.getDefault();
		CtClass cl = null;
		try {
			cl = pool.makeClass(new java.io.ByteArrayInputStream(b));
			if (cl.isInterface() == false) {
				CtBehavior[] methods = cl.getDeclaredBehaviors();
				
				for (int i = 0; i < methods.length; i++) {

					if (methods[i].isEmpty() == false) {
						doMethod(cl.getName(), methods[i]);
					}
				}
				b = cl.toBytecode();
			}
		} catch(Exception e) {
			System.err.println("Could not instrument  " + name
					+ ",  exception : " + e.getMessage());
		} finally {
			if (cl != null) {
				cl.detach();
			}
		}
		return b;
	}

	private void doMethod(String className, CtBehavior method) throws CannotCompileException, NotFoundException {	
		

		String stat = "";
		try
		{
			String isSynthetic = Boolean.toString((method.getMethodInfo().getAccessFlags() & AccessFlag.SYNTHETIC) != 0);
			
			stat = " kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodEnter(" + 
			"\"" + className + "\", " +
			"\"" + method.getName() + "\", "+
			"\"" + method.getSignature() + "\", " +
			isSynthetic +
			");";
			

			
			method.instrument(new ExprEditor() {
				@Override
				public void edit(MethodCall m)
				throws CannotCompileException
				{
					String stat = "{ kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallBegin(" +
					"\"" + m.getEnclosingClass().getName() + "\", " +
					"\"" + m.where().getName() +"\", " +
					"\"" + m.where().getSignature() +"\", " +
					"\"" + m.getFileName() +  "\", " +
					m.getLineNumber() + "," +
					"\"" + m.getMethodName() +  "\", " +
					"\"" + m.getSignature() +  "\" " +
					");" +
					"$_ = $proceed($$); " +
					"kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallEnd();" +
					"}";
					try
					{
						m.replace(stat);
					}
					catch(CannotCompileException ex)
					{
						AOMProfilingLogger.getErrorStream().println(stat);
						throw ex;
					}
				}
				
				@Override
				public void edit(ConstructorCall m)
				throws CannotCompileException
				{
					String stat = "{ kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallBegin(" +
					"\"" + m.getEnclosingClass().getName() + "\", " +
					"\"" + m.where().getName() +"\", " +
					"\"" + m.where().getSignature() +"\", " +
					"\"" + m.getFileName() +  "\", " +
					m.getLineNumber() + "," +
					"\"" + m.getMethodName() +  "\", " +
					"\"" + m.getSignature() +  "\" " +
					");" +
					"$_ = $proceed($$); " +
					"kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallEnd();" +
					"}";
					try
					{
						m.replace(stat);
					}
					catch(CannotCompileException ex)
					{
						AOMProfilingLogger.getErrorStream().println(stat);
						throw ex;
					}
				}
				
				@Override
				public void edit(NewExpr m)
				throws CannotCompileException
				{
					String stat = "{ kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallBegin(" +
							"\"" + m.getEnclosingClass().getName() + "\", " +
							"\"" + m.where().getName() +"\", " +
							"\"" + m.where().getSignature() +"\", " +
							"\"" + m.getFileName() +  "\", " +
							m.getLineNumber() + "," +
							"\"" + getConstructorName(m) +  "\", " +
							"\"" + m.getSignature() +  "\" " +
							");" +
							"$_ = $proceed($$); " +
							"kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodCallEnd();" +
							"}";
					try
					{
						m.replace(stat);
					}
					catch(CannotCompileException ex)
					{
						AOMProfilingLogger.getErrorStream().println(stat);
						throw ex;
					}
				}
			});
			
			//TODO : abstract method 
			if( !Modifier.isAbstract(method.getModifiers()) )
			{
				method.insertBefore(stat);
				method.insertAfter("kr.ac.kaist.se.aom.profiler.AOMProfilingLogger.logMethodExit("+isSynthetic+");", true);
			}
		} 
		catch(CannotCompileException ex )
		{
			AOMProfilingLogger.getErrorStream().println(stat);
			throw ex;
		}
	}
	
	protected String getConstructorName(NewExpr m) {
		char lastChar = m.getClassName().charAt(m.getClassName().length() - 1);
		String ret = null;
		if( Character.isDigit(lastChar) )
		{
			try {
				ret = m.getConstructor().getName();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			ret = removePackageName(m.getClassName());
		}
		return ret;
	}

	protected String removePackageName(String className) {
		int idx = className.lastIndexOf('.');
		
		if( className.length() > (idx + 1) )
		{
			return className.substring(idx + 1);
		}
		else
		{
			return className;
		}
	}

	public static boolean isTargetDot(String name)
	{
		for( String accept_prefix : accept_dot )
		{
			if( name.startsWith(accept_prefix) )
			{
				return true;
			}
		}
		return false;
	}
}
