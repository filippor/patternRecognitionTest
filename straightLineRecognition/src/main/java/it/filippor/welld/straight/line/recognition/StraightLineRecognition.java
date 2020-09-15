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
			boolean added = false;
			for (int j = lines.size() - 1; j >= 0; j--) {
				var line = lines.get(j);
				if (line.canAdd(p1)) {
					line.add(p1);
					added = true;
				}else {
					for (Point point : line) {
						lines.add(Line.newLine(p1,point));
					}
				}
			}
			if (!added) {
				lines.add(Line.newLine(p1));
			}
		}

		return lines.stream().filter(l->l.size()>=n).collect(toList());
	}
}
