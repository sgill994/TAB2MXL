package TAB2MXL;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class TestMeasure {
	private final String PATH = "src/test/resources/";

	@Test
	void testMeasure() {
		try {
			new Measure(0);
		} catch(Exception e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	void testSortArray() {
		TabReader test2 = new TabReader();
		test2.setInput(new File(PATH + "StairwayHeaven.txt"));
		test2.convertTabs();

		for (Measure m : test2.getMeasures()) {
			m.sortArray();
			ArrayList<Note> Notes = m.getNotes() ;
			
			for(int i =1; i < Notes.size(); i++) {
				//System.out.println(Notes.get(i).charIndex);
				assertTrue(Notes.get(i-1).charIndex <= (Notes.get(i).charIndex));
			
			}
			
			
		}
	}
}