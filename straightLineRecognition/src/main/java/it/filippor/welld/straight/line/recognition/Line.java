package it.filippor.welld.straight.line.recognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Line extends ArrayList<Point> {

	private static final long serialVersionUID = 1L;
	
	private static final double TOLERANCE = 0.00001d; 
	
	public static Line newLine(Point p1) {
		return new Line(p1);
	}
	
	public static Line newLine(Point p1, Point p2) {
		return new Line(p1,p2);
	}
	
	
	private Line(Point... points) {
		super(Arrays.asList(points));
	}

	public Collection<Point> getPoints() {
		return this;
	}

	public boolean canAdd(Point p) {
		if(size()<2)return true;
		
		var it = this.iterator();
		var p1 = it.next();
		var p2 = it.next();
		if(p1.x == p2.x)
			return p1.x == p.x;
		double slope = (p1.y - p2.y)/(p1.x - p2.x) ;
		
		double delta = p1.y - (p1.x * slope) ;
		
		return Math.abs( p.y - ((p.x*slope) + delta))<TOLERANCE;
	}
	

	public Point getFirst() {
		return iterator().next();
	}

	
	

}
