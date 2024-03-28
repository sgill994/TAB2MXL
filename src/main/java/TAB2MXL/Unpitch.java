package TAB2MXL;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has been created for the unpitch in drums.
 * To match the mxl file on the wiki
 **/

public class Unpitch {
	private static final Map<String, String> DRUMSET_IDS = initDrumsetIDS();
	private static final Map<String, Integer> DRUMSET_OCTAVES = initDrumsetOctaves();
	private static final Map<String, String> DRUMSET_STEPS = initDrumsetSteps();
	private int octave;
	private String step;
	private String instrumentID;
	
	
	/**
	 * Creates a drum note
	 * 
	 * @param scoreInstrument - (ignore case except t) B/BD, S/SN/SD, ST/HT/T1/T, MT/LT/T2/t, FT/T3, H/HH, C/CR/CC, R/RD/RC
	 * @param drumsetNote - O, f, d, b, x, X, o
	 */
	public Unpitch(String scoreInstrument, String drumsetNote) {
		scoreInstrument = scoreInstrument.replaceAll("\\s", "");
		if (!scoreInstrument.equals("t")) {
			scoreInstrument = scoreInstrument.toUpperCase();
		}

		step = DRUMSET_STEPS.get(scoreInstrument);
		octave = DRUMSET_OCTAVES.get(scoreInstrument);
		instrumentID = DRUMSET_IDS.get(scoreInstrument);
		
	}

	
	public int getOctave() {
		return octave;
	}

	public String getStep() {
		return step;
	}
	
	public String getInstrumentID() {
		return instrumentID;
	}

	private static Map<String, String> initDrumsetSteps() {
		Map<String, String> drumSteps = new HashMap<String, String>();
		
		drumSteps.put("B", "F");
		drumSteps.put("BD", "F");
		
		drumSteps.put("S", "C");
		drumSteps.put("SN", "C");
		drumSteps.put("SD", "C");
		
		drumSteps.put("ST", "E");
		drumSteps.put("HT", "E");
		drumSteps.put("T1", "E");
		drumSteps.put("T", "E");
		
		drumSteps.put("MT", "D");
		drumSteps.put("LT", "D");
		drumSteps.put("T2", "D");
		drumSteps.put("t", "D");
		
		drumSteps.put("F", "A");
		drumSteps.put("FT", "A");
		drumSteps.put("T3", "A");
		
		drumSteps.put("H", "G");
		drumSteps.put("HH", "G");
		
		drumSteps.put("C", "A");
		drumSteps.put("CR", "A");
		drumSteps.put("CC", "A");
		
		drumSteps.put("R", "F");
		drumSteps.put("RD", "F");
		drumSteps.put("Rd", "F");
		drumSteps.put("RC", "F");
		
		drumSteps.put("Hf", "D");
		drumSteps.put("HF", "D");
		drumSteps.put("FH", "D");
		
		return drumSteps;
	}

	private static Map<String, Integer> initDrumsetOctaves() {
		Map<String, Integer> drumOctaves = new HashMap<String, Integer>();
		
		drumOctaves.put("B", 4);
		drumOctaves.put("BD", 4);
		
		drumOctaves.put("S", 5);
		drumOctaves.put("SN", 5);
		drumOctaves.put("SD", 5);
		
		drumOctaves.put("ST", 5);
		drumOctaves.put("HT", 5);
		drumOctaves.put("T1", 5);
		drumOctaves.put("T", 5);
		
		drumOctaves.put("MT", 5);
		drumOctaves.put("LT", 5);
		drumOctaves.put("T2", 5);
		drumOctaves.put("t", 5);
		
		drumOctaves.put("F", 4);
		drumOctaves.put("FT", 4);
		drumOctaves.put("T3", 4);
		
		drumOctaves.put("H", 5);
		drumOctaves.put("HH", 5);
		
		drumOctaves.put("C", 5);
		drumOctaves.put("CR", 5);
		drumOctaves.put("CC", 5);
		
		drumOctaves.put("R", 5);
		drumOctaves.put("RD", 5);
		drumOctaves.put("Rd", 5);
		drumOctaves.put("RC", 5);
		
		drumOctaves.put("Hf", 4);
		drumOctaves.put("HF", 4);
		drumOctaves.put("FH", 4);

		return drumOctaves;
	}
	
	private static Map<String, String> initDrumsetIDS() {
		Map<String, String> drumsetIDS = new HashMap<String, String>();
		
		drumsetIDS.put("B", "P1-I36");
		drumsetIDS.put("BD", "P1-I36");
		
		drumsetIDS.put("S", "P1-I39");
		drumsetIDS.put("SN", "P1-I39");
		drumsetIDS.put("SD", "P1-I39");
		
		drumsetIDS.put("ST", "P1-I48");
		drumsetIDS.put("HT", "P1-I48");
		drumsetIDS.put("T1", "P1-I48");
		drumsetIDS.put("T", "P1-I48");
		
		drumsetIDS.put("MT", "P1-I46");
		drumsetIDS.put("LT", "P1-I46");
		drumsetIDS.put("T2", "P1-I46");
		drumsetIDS.put("t", "P1-I46");
		
		drumsetIDS.put("F", "P1-I42");
		drumsetIDS.put("FT", "P1-I42");
		drumsetIDS.put("T3", "P1-I42");
		
		drumsetIDS.put("H", "P1-I43");
		drumsetIDS.put("HH", "P1-I43");
		
		drumsetIDS.put("C", "P1-I50");
		drumsetIDS.put("CR", "P1-I50");
		drumsetIDS.put("CC", "P1-I50");
		
		drumsetIDS.put("R", "P1-I52");
		drumsetIDS.put("RD", "P1-I52");
		drumsetIDS.put("Rd", "P1-I52");
		drumsetIDS.put("RC", "P1-I52");
		
		drumsetIDS.put("Hf", "P1-I45");
		drumsetIDS.put("HF", "P1-I45");
		drumsetIDS.put("FH", "P1-I45");

		return drumsetIDS;
	}

	@Override
	public String toString() {
		String mxl = "\t\t<unpitched>\n";
		mxl += "\t\t\t<display-step>" + step + "</display-step>\n";
		mxl += "\t\t\t<display-octave>" + octave + "</display-octave>\n";
		mxl += "\t\t</unpitched>\n";
		return mxl;
	}

	@Override
	public boolean equals(Object obj) {
		Unpitch unp = (Unpitch) obj;
		return unp.step.equals(step) && unp.octave == octave; 
	}
}
