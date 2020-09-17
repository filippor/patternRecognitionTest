package it.filippor.test.pattern.recognition.rest;

import javax.validation.constraints.NotNull;

public class Point {
	@NotNull
	private Double x;
	@NotNull
	private Double y;
	
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	public double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public it.filippor.straight.line.recognition.Point toDomainPoint(){
		return new it.filippor.straight.line.recognition.Point(x,y);
	}
	public static Point fromDomainPoint(it.filippor.straight.line.recognition.Point p) {
		var result = new Point();
		result.x = p.getX();
		result.y = p.getY();
		return result;
	}
	
}
