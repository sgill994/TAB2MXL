package TAB2MXL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPitch {
	
	@BeforeEach
	void setup() {
		TabReader.instrument = "Classical Guitar";
	}

	@Test
	void testStep_Normal() {
		Pitch p = (new Note(1, "G", 5, 0).getPitch());
		String expected = "C";

		assertEquals(expected, p.getStep());
	}

	@Test
	void testStep_Sharp() {
		Pitch p = (new Note(1, "G#", 5, 0).getPitch());
		String expected = "C";

		assertEquals(expected, p.getStep());
	}

	@Test
	void testAlter_Normal() {
		Pitch p = (new Note(1, "G", 5, 0).getPitch());
		int expected = 0;

		assertEquals(expected, p.getAlter());
	}

	@Test
	void testAlter_Sharp() {
		Pitch p = (new Note(1, "G#", 5, 0).getPitch());
		int expected = 1;

		assertEquals(expected, p.getAlter());
	}

	@Test
	void testOctave_Normal() {
		Pitch p = (new Note(1, "G", 5, 0).getPitch());
		int expected = 5;

		assertEquals(expected, p.getOctave());
	}

	@Test
	void testOctave_Sharp() {
		Pitch p = new Pitch(1, "G#", 5);
		int expected = 5;

		assertEquals(expected, p.getOctave());
	}

	@Test
	void testToString() {
		Pitch p = new Pitch(1, "G#", 5);
		String expected = "\t\t<pitch>\n"
				+ "\t\t\t<step>C</step>\n"
				+ "\t\t\t<alter>1</alter>\n"
				+ "\t\t\t<octave>5</octave>\n"
				+ "\t\t</pitch>\n";

		assertEquals(expected, p.toString());
	}

	@Test
	void testEquals() {
		Pitch p1 = new Pitch(1, "G#", 5);
		Pitch p2 = new Pitch(1, "G#", 5);
		Pitch p3 = new Pitch(1, "G", 5);
		
		assertTrue(p1.equals(p2));
		assertFalse(p2.equals(p3));
	}
}