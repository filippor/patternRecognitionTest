package it.filippor.welld.straight.line.recognition;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StraightLineRecognitionTest {

	public static Stream<Arguments> argumentProvider() {
		return Stream.of(
				Arguments.of(new Point[] { new Point(1, 1), new Point(2, 2) }, 2,
						new Point[][] { { new Point(1, 1), new Point(2, 2) } }),

				Arguments.of(new Point[] { new Point(1, 1), new Point(2, 2), new Point(2, 3) }, 2,
						new Point[][] { { new Point(1, 1), new Point(2, 2) }, { new Point(1, 1), new Point(2, 3) },
								{ new Point(2, 2), new Point(2, 3) } }),

				Arguments.of(new Point[] { new Point(1, 1), new Point(2, 2), new Point(3, 3) }, 2,
						new Point[][] { { new Point(1, 1), new Point(2, 2), new Point(3, 3) } }),

				Arguments.of(new Point[] { new Point(1, 1), new Point(2, 2), new Point(3, 3) }, 3,
						new Point[][] { { new Point(1, 1), new Point(2, 2), new Point(3, 3) }, })
				
				

		);
	}

	@ParameterizedTest
	@MethodSource("argumentProvider")
	void testFindstrightLine(Point[] space, int n, Point[][] expect) {

		var subject = new StraightLineRecognition();
		var result = subject.findstrightLine(Arrays.asList(space), n);

		@SuppressWarnings("unchecked")
		List<Point>[] expectWithLineSorted = Stream.of(expect).map(l -> Stream.of(l).sorted().collect(toList()))
				.collect(Collectors.toList()).toArray(new List[expect.length]);
		assertThat(result).allMatch(line -> line.size() >= 1);

		assertThat(result.stream().map(l -> l.getPoints().stream().sorted().collect(toList())))
				.containsExactlyInAnyOrder(expectWithLineSorted);
	}

}
