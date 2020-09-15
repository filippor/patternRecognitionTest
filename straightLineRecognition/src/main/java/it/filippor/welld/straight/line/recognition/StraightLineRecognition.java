package it.filippor.welld.straight.line.recognition;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StraightLineRecognition {
	public static Collection<Line> findstrightLine(List<Point> space, int n) {
		if(space.size()<2)return Collections.emptyList();
		
		var lines = new ArrayList<Line>();
		lines.add(Line.newLine(space.get(0), space.get(1)));

		for (int i = 2; i < space.size(); i++) {
			var p1 = space.get(i);

			for (int j = lines.size() - 1; j >= 0; j--) {
				var line = lines.get(j);
				if (!line.add(p1)) {
					for (Point point : line) {
						Line l1 = Line.newLine(point, p1);
						if (lines.stream().noneMatch(l2 -> l2.hasSameProperty(l1))) {
							lines.add(l1);
						}
					}
				}
			}

		}

		return lines.stream().filter(l -> l.size() >= n).collect(toList());
	}
}
