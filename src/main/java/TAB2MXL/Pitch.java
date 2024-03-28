package TAB2MXL;

import java.util.HashMap;
import java.util.Map;

public class Pitch {
	public static final Map<String, Integer> ALL_NOTES_MAP = initAllNotesMap();
	private static final Map<Integer, Integer> GUITAR_OCTAVES = initGuitarOctaves();
	private static final String[] ALL_NOTES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
	private String step;
	private int alter;
	private int octave;
	
	/**
	 * Creates a Pitch object which has the step, alter, and octave
	 * 
	 * @param stringNumber a number from 1 to 6, 1 being the thinnest string and 6
	 *                     the thickest
	 * @param stringTuning the tuning note on the left of the tabs (C, C#, D, D#, E,
	 *                     F, F#, G, G#, A, A#, B);
	 * @param fret
	 */
	public Pitch(int stringNumber, String stringTuning, int fret) {
		stringTuning = stringTuning.toUpperCase().replaceAll("\\s", "");
		
		int tuningValue = ALL_NOTES_MAP.get(stringTuning);
		int noteIndex = (tuningValue + fret) % ALL_NOTES.length;
		String note = ALL_NOTES[noteIndex];

		step = Character.toString(note.charAt(0));
		alter = note.length() - 1;
		octave = GUITAR_OCTAVES.get(stringNumber) + (tuningValue + fret) / ALL_NOTES.length;
		
		if (TabReader.instrument.equals("Bass")) {
			octave--;
		}
	}

	public String getStep() {
		return step;
	}

	public int getAlter() {
		return alter;
	}

	public int getOctave() {
		return octave;
	}
	
	private static Map<Integer, Integer> initGuitarOctaves() {
		Map<Integer, Integer> guitarOctaves = new HashMap<Integer, Integer>();
		guitarOctaves.put(1, 4);
		guitarOctaves.put(2, 3);
		guitarOctaves.put(3, 3);
		guitarOctaves.put(4, 3);
		guitarOctaves.put(5, 2);
		guitarOctaves.put(6, 2);

		return guitarOctaves;
	}

	private static Map<String, Integer> initAllNotesMap() {
		Map<String, Integer> allNotesMap = new HashMap<String, Integer>();
		allNotesMap.put("C", 0);
		allNotesMap.put("C#", 1);
		allNotesMap.put("D", 2);
		allNotesMap.put("D#", 3);
		allNotesMap.put("E", 4);
		allNotesMap.put("F", 5);
		allNotesMap.put("F#", 6);
		allNotesMap.put("G", 7);
		allNotesMap.put("G#", 8);
		allNotesMap.put("A", 9);
		allNotesMap.put("A#", 10);
		allNotesMap.put("B", 11);

		return allNotesMap;
	}
	
	@Override
	public String toString() {
		String mxl = "\t\t<pitch>\n";
		
		mxl += "\t\t\t<step>" + step +"</step>\n";
		mxl += "\t\t\t<alter>" + alter +"</alter>\n";
		mxl += "\t\t\t<octave>" + octave +"</octave>\n";
		
		mxl += "\t\t</pitch>\n";
		return mxl;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pitch p = (Pitch) obj;
		return p.alter == alter && p.step.equals(step) && p.octave == octave; 
	}
}
