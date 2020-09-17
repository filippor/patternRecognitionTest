package it.filippor.straight.line.recognition;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Line extends AbstractCollection<Point> {

	private final double slope;

	private final double delta;

	private Collection<Point> data;

	public static Line newLine(Point p1, Point p2) {
		return new Line(p1, p2);
	}

	private Line(Point p1, Point p2) {
		data = new ArrayList<Point>(Arrays.asList(p1, p2));

		slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
		if (Double.isFinite(slope))
			delta = p1.getY() - (p1.getX() * slope);
		else
			delta = p1.getX();
	}

	@Override
	public boolean add(Point p) {
		if (canAdd(p))
			return data.add(p);
		return false;
	}

	public boolean canAdd(Point p) {
		if (Double.isInfinite(slope))
			return p.getX() == delta;
		return p.getY() == ((p.getX() * slope) + delta);
	}

	public Point getFirst() {
		return iterator().next();
	}

	public boolean hasSameProperty(Line l1) {
		return (
					(Double.isFinite(slope)  && slope == l1.slope)
					||(Double.isInfinite(l1.slope))
				)
				
				&& delta == l1.delta;
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
		return data.hashCode();
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
				&& Double.doubleToLongBits(slope) == Double.doubleToLongBits(other.slope);
	}

	@Override
	public String toString() {
		return "Line [" + data + "]";
	}

}
