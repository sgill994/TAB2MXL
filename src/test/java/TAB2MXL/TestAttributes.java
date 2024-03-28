package TAB2MXL;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAttributes {
	private final String PATH = "src/test/resources/";
	
	@BeforeEach
	void setup() {
		TabReader.instrument = "Classical Guitar";
	}
	
	@Test
	void testToString() throws IOException {
		List<String> guitarTuning = new ArrayList<String>();
		guitarTuning.add("E");
		guitarTuning.add("B");
		guitarTuning.add("G");
		guitarTuning.add("D");
		guitarTuning.add("A");
		guitarTuning.add("E");
		Attributes a = new Attributes(guitarTuning, 4, 4);
		
		Scanner sc = new Scanner(new File(PATH + "test_attributes_expected.txt"));
		String expected = "";
		while (sc.hasNextLine()) {
			expected += sc.nextLine() + "\n";
		}
		sc.close();
		
		
		assertEquals(expected.substring(0, expected.length()-1), a.toString());
	}
}
