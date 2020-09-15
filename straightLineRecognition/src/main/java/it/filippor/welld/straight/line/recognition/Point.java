package it.filippor.welld.straight.line.recognition;

import java.util.Objects;

public class Point implements Comparable<Point> {

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		double v;
		if (x == o.x)
			v = y - o.y;
		else
			v = x - o.x;
		if (v==0)
			return 0;
		else if (v>0d)
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}



}
