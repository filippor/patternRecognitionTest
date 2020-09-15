package it.filippor.welld.straight.line.recognition;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StraightLineRecognition {
	

	public Collection<Line> findstrightLine(List<Point> space, int n) {
		var lines = new LinkedList<Line>();

		for (int i = 0; i < space.size(); i++) {
			Point point = space.get(i);
			List<Point> tmpSpace = new LinkedList<Point>(space.subList(0, i));
			lines.stream().filter(line -> line.canAdd(point)).forEach(line -> {
				tmpSpace.removeAll(line);
				line.add(point);
			});
			lines.addAll(tmpSpace.stream().map(p1 -> Line.newLine(point, p1)).collect(Collectors.toList()));
		}
		return lines.stream().filter(l -> l.size() >= n).collect(toList());
	}

}
