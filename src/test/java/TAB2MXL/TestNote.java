package TAB2MXL;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ToStringBuilder;

class TestNote {

	@BeforeEach
	void setup() {
		TabReader.instrument = "Classical Guitar";
	}

	@Test
	void testCompareTo_Negative() {
		Note n1 = new Note(1, "C", 0, 4);
		Note n2 = new Note(1, "C", 0, 5);

		assertTrue(n1.compareTo(n2) < 0);
	}

	@Test
	void testCompareTo_Positive() {
		Note n1 = new Note(1, "C", 0, 6);
		Note n2 = new Note(1, "C", 0, 5);

		assertTrue(n1.compareTo(n2) > 0);
	}

	@Test
	void testCompareTo_Equals() {
		Note n1 = new Note(1, "C", 0, 5);
		Note n2 = new Note(1, "C", 0, 5);

		assertTrue(n1.compareTo(n2) == 0);
	}
	

	@Test
	void testGetPitch() {
		Pitch actual = new Note(1, "E", 0, 0).getPitch();
		Pitch expected = new Pitch(1, "E", 0);

		assertEquals(expected, actual);
	}
     
	@Test
	void testDrumGhost() {
		Note note = new Note("SN", "g", 0);
		assertTrue(note.ghost);
	}
	
	@Test
	void testDrumRoll() {
		Note note = new Note("SN", "b", 0);
		assertTrue(note.roll);
	}
	
	@Test
	void testDrumFlam() {
		Note note = new Note("SN", "f", 0);
		assertTrue(note.flam);
	}
	
	@Test
	void testDrumDrag() {
		Note note = new Note("SN", "d", 0);
		assertTrue(note.drag);
	}
	
	@Test
	void testHiHatRoll() {
		Note note = new Note("HH", "B", 0);
		assertTrue(note.roll);
	}
	
	@Test
	void testDrumAccent() {
		Note note = new Note("SN", "O", 0);
		assertTrue(note.accent);
	}
	
	

}