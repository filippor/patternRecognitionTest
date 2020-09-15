package it.filippor.welld.straight.line.recognition;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;

public class StraightLineRecognition {
	public Collection<Line> findstrightLine(Collection<Point> spaces, int n) {
		var lines = new ArrayList<Line>();
		ArrayList<Point> tmpSpace = new ArrayList<>(spaces);
		for (int i = tmpSpace.size() - 1; i >= 0; i--) {
			Point p1 = tmpSpace.remove(i);
			for (Point point : tmpSpace) {
				lines.add(Line.newLine(p1, point));
			}
		}

		return lines.stream().filter(line -> line.size() >= n).collect(toList());
	}
}
