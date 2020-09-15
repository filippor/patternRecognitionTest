package it.filippor.welld.straight.line.recognition;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Line extends AbstractCollection<Point>{

	private static final double TOLERANCE = 0.0000001d;

	private final double slope;

	private final double delta;
	
	private final boolean vertical;

	private Collection<Point> data;
	
	
	
	public static Line newLine(Point p1, Point p2) {
		return new Line(p1,p2);
	}
	
	
	private Line(Point p1, Point p2) {
		data = new ArrayList<Point>(Arrays.asList(p1,p2));
		var it = this.iterator();
		var p11 = it.next();
		var p21 = it.next();
		if(p11.x == p21.x) {
			vertical = true;
			slope = 0;
			delta = p11.x;
		}else {
			vertical = false;
			slope = (p11.y - p21.y)/(p11.x - p21.x);
			delta = p11.y - (p11.x * slope);
		}
	}

	public Collection<Point> getPoints() {
		return data;
	}
	
	@Override
	public boolean add(Point p) {
		if(canAdd(p))
			return data.add(p);
		return false;
	}
	
	public boolean canAdd(Point p) {
		if(vertical) return p.x == delta;
		return Math.abs( p.y - ((p.x*slope) + delta))<TOLERANCE;
	}

	public Point getFirst() {
		return iterator().next();
	}


	public boolean hasSameProperty(Line l1) {
		return vertical == l1.vertical
			&& Math.abs( slope - l1.slope) < TOLERANCE
			&& Math.abs( delta - l1.delta) < TOLERANCE;
	}


	@Override
	public Iterator<Point> iterator() {
		return data.iterator();
	}


	@Override
	public int size() {
		return data.size();
	}

	
	

}
