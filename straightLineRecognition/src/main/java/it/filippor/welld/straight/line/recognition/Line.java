package it.filippor.welld.straight.line.recognition;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

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
		if(p11.getX() == p21.getX()) {
			vertical = true;
			slope = 0;
			delta = p11.getX();
		}else {
			vertical = false;
			slope = (p11.getY() - p21.getY())/(p11.getX() - p21.getX());
			delta = p11.getY() - (p11.getX() * slope);
		}
	}

	
	
	@Override
	public boolean add(Point p) {
		if(canAdd(p))
			return data.add(p);
		return false;
	}
	
	public boolean canAdd(Point p) {
		if(vertical) return p.getX() == delta;
		return Math.abs( p.getY() - ((p.getX()*slope) + delta))<TOLERANCE;
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


	@Override
	public int hashCode() {
		return Objects.hash(data, delta, slope, vertical);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		return Objects.equals(data, other.data)
				&& Double.doubleToLongBits(delta) == Double.doubleToLongBits(other.delta)
				&& Double.doubleToLongBits(slope) == Double.doubleToLongBits(other.slope) && vertical == other.vertical;
	}


	@Override
	public String toString() {
		return "Line [" + data + "]";
	}

	
	

}
