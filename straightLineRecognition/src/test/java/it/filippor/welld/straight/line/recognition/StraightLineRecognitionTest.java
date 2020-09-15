package it.filippor.welld.straight.line.recognition;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;






class StraightLineRecognitionTest {

	public static Stream<Arguments> argumentProvider(){
		return Stream.of(
				Arguments.of(new Point[] {new Point(1,1),new Point(2,2)},2,
						new Point[][]{{new Point(1,1),new Point(2,2)}}),
				Arguments.of(new Point[] {new Point(1,1),new Point(2,2),new Point(2,3)},2,
						new Point[][]{{new Point(1,1),new Point(2,2)},
							{new Point(1,1),new Point(2,3)},
							{new Point(2,2),new Point(2,3)}})
				);
	}
	
	@ParameterizedTest
	@MethodSource("argumentProvider")
	void testFindstrightLine(Point[] space,int n, Point[][] expect) {
		
		var subject = new StraightLineRecognition();
		var result = subject.findstrightLine(Arrays.asList(space), n);
		
		
		@SuppressWarnings("unchecked")
		HashSet<Point>[] expect1 = Stream.of(expect).map(l->new HashSet<Point>(Arrays.asList(l))).collect(Collectors.toList()).toArray(new HashSet[expect.length]);
		assertThat(result).allMatch(line -> line.size() >= 1);
		assertThat(result.stream().map(l->l.getPoints())).containsExactlyInAnyOrder(expect1); 
	}

}
