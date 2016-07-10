package kr.ac.kaist.se.deltatable.model;

public class DTLink<ET> {
	private DTEntity<ET> from;
	private DTEntity<ET> to;
	private DTLinkType linkType;
	
	public DTLink(DTEntity<ET> from, DTEntity<ET> to, DTLinkType linkType)
	{
		this.from = from;
		this.to = to;
		this.linkType = linkType;
	}

	public DTEntity<ET> getFrom() {
		return from;
	}

	public void setFrom(DTEntity<ET> from) {
		this.from = from;
	}

	public DTEntity<ET> getTo() {
		return to;
	}

	public void setTo(DTEntity<ET> to) {
		this.to = to;
	}

	public DTLinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(DTLinkType linkType) {
		this.linkType = linkType;
	}
}
