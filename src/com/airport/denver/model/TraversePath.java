package com.airport.denver.model;

public class TraversePath {
	private String parentNode;
	private int time;
	
	
	public TraversePath(String parentNode, int distance) {
		super();
		this.parentNode = parentNode;
		this.time = distance;
	}

	@Override
	public String toString() {
		return "TraversePath [parentNode=" + parentNode + ", time=" + time + "]";
	}

	public String getParentNode() {
		return parentNode;
	}
	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
