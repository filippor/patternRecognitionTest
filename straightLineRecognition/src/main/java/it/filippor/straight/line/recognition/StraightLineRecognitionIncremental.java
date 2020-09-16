package it.filippor.straight.line.recognition;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class StraightLineRecognitionIncremental {
	LinkedList<Point> space = new LinkedList<Point>();
	LinkedList<Line> lines = new LinkedList<Line>();

	public boolean contains(Point p) {
		return space.contains(p);
	}
	public boolean addPoint(Point p) {
		if (space.contains(p))
			return false;

		LinkedList<Point> tmpSpace = new LinkedList<Point>(space);
		lines.stream().filter(line -> line.canAdd(p)).forEach(line -> {
			tmpSpace.removeAll(line);
			line.add(p);
		});
//		tmpSpace.removeAll(lines.parallelStream().filter(line->line.add(p)).flatMap(l->l.stream()).collect(toList()));
		lines.addAll(tmpSpace.parallelStream().map(p1 -> Line.newLine(p, p1)).collect(Collectors.toList()));

		return space.add(p);
	}

	public Collection<Line> getLines(int n) {
		return lines.stream().filter(l -> l.size() >= n).collect(toList());
	}
	
	public void clear() {
		space.clear();
		lines.clear();
	}

	public static Collection<Line> findstrightLine(List<Point> space, int n) {
		var obj = new StraightLineRecognitionIncremental();
		for (Point point : space) {
			obj.addPoint(point);
		}
		return obj.getLines(n);
	}
	
	
	

	
}
