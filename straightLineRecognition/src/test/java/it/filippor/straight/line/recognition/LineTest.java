package it.filippor.straight.line.recognition;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LineTest {
	public static Stream<Arguments> canAddProvider() {
		return Stream.of(Arguments.of(new Point(1, 1), new Point(2, 2), new Point(3, 3), true),
				Arguments.of(new Point(1, 1), new Point(2, 2), new Point(4, 3), false),
				Arguments.of(new Point(2, 0), new Point(2.5, 1), new Point(3, 2), true),
				Arguments.of(new Point(2, 0), new Point(2.5, -1), new Point(3, -2), true),
				Arguments.of(new Point(-2, 0), new Point(-2.5, 1), new Point(-3, 2), true),
				Arguments.of(new Point(2, 0), new Point(2, 5), new Point(2, 32), true),
				Arguments.of(new Point(2, 0), new Point(2, 5), new Point(2.1, 32), false),
				Arguments.of(new Point(1, 2), new Point(13, 2), new Point(-3, 2), true),
				Arguments.of(new Point(1, 2), new Point(13, 2), new Point(-3, -2), false),
				Arguments.of(new Point(1, 2), new Point(13, 2), new Point(-3, 2), true),

				Arguments.of(new Point(0.1, 0.2), new Point(0.2, 0.4), new Point(0.3, 0.6), true)

		);
	}

	@ParameterizedTest
	@MethodSource("canAddProvider")
	void testCanAdd(Point first, Point second, Point test, boolean result) {
		var subject = Line.newLine(first, second);
		assertThat(subject.canAdd(test)).isEqualTo(result);
	}

	@ParameterizedTest
	@MethodSource("canAddProvider")
	void testAdd(Point first, Point second, Point test, boolean result) {
		var subject = Line.newLine(first, second);
		assertThat(subject).containsExactlyInAnyOrder(first, second);

		boolean add = subject.add(test);

		assertThat(add).isEqualTo(result);
		if (result)
			assertThat(subject).containsExactlyInAnyOrder(first, second, test);
		else
			assertThat(subject).containsExactlyInAnyOrder(first, second);
	}

	@Test
	void testMultipleAdd() throws Exception {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4_wrong = new Point(4, 1);
		Point p5 = new Point(4, 4);
		var subject = Line.newLine(p1, p2);

		subject.add(p3);
		assertThat(subject).containsExactlyInAnyOrder(p1, p2, p3);
		subject.add(p4_wrong);
		assertThat(subject).containsExactlyInAnyOrder(p1, p2, p3);
		subject.add(p5);
		assertThat(subject).containsExactlyInAnyOrder(p1, p2, p3, p5);

	}

}
