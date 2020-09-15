package it.filippor.welld.straight.line.recognition;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Line extends HashSet<Point> {

	private static final long serialVersionUID = 1L;
	
	public static Line newLine(Point p1, Point p2) {
		return new Line(p1,p2);
	}
	
	private Line(Point... points) {
		super(Arrays.asList(points));
	}

	public Collection<Point> getPoints() {
		// TODO Auto-generated method stub
		return this;
	}
	
	

}
