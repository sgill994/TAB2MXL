package TAB2MXL;

import java.util.HashMap;
import java.util.Map;

public class Note implements Comparable<Note> {
	public Pitch pitch;
	public Unpitch unpitch;
	public int duration;
	public String type;
	public int charIndex;
	public boolean slurStart;
	public boolean slurStop;
	public boolean slideStart;
	public boolean slideStop;
	public boolean pullStart;
	public boolean pullStop;
	public boolean hammerStart;
	public boolean hammerStop;
	public int stringNo;
	public int fret;
	public boolean dot;
	public boolean chord;
	public boolean bend;
	public boolean release;
	public int bendAlter;
	public boolean grace;

	private static final Map<String, String> DRUMSET_NOTEHEADS = drumsetNotehead();

	// ways to play SD
	public boolean ghost;
	public boolean roll;
	public boolean accent;

	// drum grace notes
	public boolean flam;
	public boolean drag;

	// beams
	public boolean beamStart;
	public boolean beamContinue1;
	public boolean beamContinue2;
	public boolean beamEnd;

	// beam for 8th notes
	public boolean beam1;

	// beam for 16th notes
	public boolean beam2;

	// beam for 32nd notes
	public boolean beam3;

	// beam for 64th notes
	public boolean beam4;

	public String notehead;

	/**
	 * Creates a guitar note
	 * 
	 * @param stringNumber a number from 1 to 6, 1 being the thinnest string and 6
	 *                     the thickest
	 * @param stringTuning the tuning note on the left of the tabs (C, C#, D, D#, E,
	 *                     F, F#, G, G#, A, A#, B);
	 * @param fret
	 * @param charIndex
	 */
	public Note(int stringNumber, String stringTuning, int fret, int charIndex) {
		this.pitch = new Pitch(stringNumber, stringTuning, fret);
		this.charIndex = charIndex;
		this.stringNo = stringNumber;
		this.fret = fret;
	}

	/**
	 * Creates a drum note
	 * 
	 * @param scoreInstrument - (ignore case except t) B/BD, S/SN/SD, ST/HT/T1/T,
	 *                        MT/LT/T2/t, FT/T3, H/HH, HF, C/CR/CC, R/RD/RC
	 * @param drumsetNote     - O, f, d, b, x, X, o
	 * @param charIndex
	 */
	public Note(String scoreInstrument, String drumsetNote, int charIndex) {
		this.unpitch = new Unpitch(scoreInstrument, drumsetNote);
		this.charIndex = charIndex;
		String instrumentNote = scoreInstrument.concat(drumsetNote);
		if (drumsetNote.equals("x") || drumsetNote.equals("X") || drumsetNote.equals("o") || drumsetNote.equals("@")
				|| drumsetNote.equals("#"))
			this.notehead = DRUMSET_NOTEHEADS.get(instrumentNote);
		else
			this.notehead = this.modifyNote(drumsetNote, scoreInstrument);
	}

	public String modifyNote(String note, String instrument) {

		if (note.equals("g"))
			ghost = true;

		if (note.equals("B")) {
			roll = true;
			accent = true;
		}

		if (note.equals("O"))
			accent = true;

		if (note.equals("f"))
			flam = true;

		if (note.equals("d"))
			drag = true;

		if (note.equals("b") && (instrument.equals("S") || instrument.equals("SN") || instrument.equals("SD")))
			roll = true;

		if (note.equals("b") && (instrument.equals("R") || instrument.equals("RD") || instrument.equals("RC")
				|| instrument.equals("Rd")))
			return "diamond";

		return "normal";
	}

	private static Map<String, String> drumsetNotehead() {
		Map<String, String> drumNoteheads = new HashMap<String, String>();

		drumNoteheads.put("Bo", "normal");
		drumNoteheads.put("BDo", "normal");

		drumNoteheads.put("So", "normal");
		drumNoteheads.put("SNo", "normal");
		drumNoteheads.put("SDo", "normal");

		drumNoteheads.put("S@", "x");
		drumNoteheads.put("SN@", "x");
		drumNoteheads.put("SD@", "x");

		drumNoteheads.put("STo", "normal");
		drumNoteheads.put("HTo", "normal");
		drumNoteheads.put("T1o", "normal");
		drumNoteheads.put("To", "normal");

		drumNoteheads.put("MTo", "normal");
		drumNoteheads.put("LTo", "normal");
		drumNoteheads.put("T2o", "normal");
		drumNoteheads.put("to", "normal");

		drumNoteheads.put("Fo", "normal");
		drumNoteheads.put("FTo", "normal");
		drumNoteheads.put("T3o", "normal");

		drumNoteheads.put("Hx", "x");
		drumNoteheads.put("HHx", "x");

		drumNoteheads.put("HX", "diamond");
		drumNoteheads.put("HHX", "diamond");

		drumNoteheads.put("Ho", "circle-x");
		drumNoteheads.put("HHo", "circle-x");

		drumNoteheads.put("Cx", "x");
		drumNoteheads.put("CRx", "x");
		drumNoteheads.put("CCx", "x");

		drumNoteheads.put("C#", "circle-x");
		drumNoteheads.put("CR#", "circle-x");
		drumNoteheads.put("CC#", "circle-x");

		drumNoteheads.put("Rx", "x");
		drumNoteheads.put("RDx", "x");
		drumNoteheads.put("Rdx", "x");
		drumNoteheads.put("RCx", "x");

		drumNoteheads.put("Hfx", "x");
		drumNoteheads.put("HFx", "x");
		drumNoteheads.put("FHx", "x");

		return drumNoteheads;
	}

	@Override
	public String toString() {

		String toMXL = "\t<note>\n";

		if (chord) {
			toMXL += "\t\t<chord/>\n";
		}

		if (TabReader.instrument.equals("Drumset")) {

			if (drag || flam) {

				if (drag) {
					toMXL += "\t\t<grace/>\n" + this.unpitch + "\t\t<voice>1</voice>\n" + "\t\t<type>" + "16th"
							+ "</type>\n" + "\t\t<stem>up</stem>\n" + "\t\t<beam number=\"1\">begin</beam>\n"
							+ "\t\t<beam number=\"2\">begin</beam>\n" + "\t\t<notations>\n"
							+ "\t\t\t<slur type=\"start\"/>\n" + "\t\t</notations>\n" + "\t</note>\n";

					toMXL += "\t<note>\n" + "\t\t<grace/>\n" + this.unpitch + "\t\t<voice>1</voice>\n" + "\t\t<type>"
							+ "16th" + "</type>\n" + "\t\t<stem>up</stem>\n" + "\t\t<beam number=\"1\">end</beam>\n"
							+ "\t\t<beam number=\"2\">end</beam>\n" + "\t</note>\n";

				}

				if (flam) {
					toMXL += "\t\t<grace slash=\"yes\"/>\n" + this.unpitch + "\t\t<voice>1</voice>\n" + "\t\t<type>"
							+ "eighth" + "</type>\n" + "\t\t<stem>up</stem>\n" + "\t\t<notations>\n"
							+ "\t\t\t<slur type=\"start\"/>\n" + "\t\t</notations>\n" + "\t</note>\n";
				}

				toMXL += "\t<note>\n";
			}

			toMXL += this.unpitch + "\t\t<duration>" + this.duration + "</duration>\n" + "\t\t<instrument id=\""
					+ this.unpitch.getInstrumentID() + "\"/>\n" + "\t\t<voice>1</voice>\n" + "\t\t<type>" + this.type
					+ "</type>\n" + "\t\t<stem>up</stem>\n";

			if (drag || flam || accent || roll) {
				toMXL += "\t\t<notations>\n";

				if (drag || flam)
					toMXL += "\t\t\t<slur type=\"stop\"/>\n";
				if (accent)
					toMXL += "\t\t\t<articulations>\n" + "\t\t\t\t<accent/>" + "\t\t\t</articulations>";
				if (roll)
					toMXL += "\t\t\t<ornaments>\n" + "\t\t\t\t<tremolo type=\"single\">3</tremolo>\n"
							+ "\t\t\t</ornaments>\n";

				toMXL += "\t\t</notations>\n";
			}

			if (ghost)
				toMXL += "\t\t<notehead parentheses=\"yes\">" + this.notehead + "</notehead>\n";
			else if (notehead == null)
				toMXL += "";
			else if (!notehead.equals("normal"))
				toMXL += "\t\t<notehead>" + this.notehead + "</notehead>\n";

			if (beamStart || beamContinue1 || beamContinue2 || beamEnd) {
				if (beamStart) {
					if (beam1)
						toMXL += "\t\t<beam number=\"1\">begin</beam>\n";
					if (beam2)
						toMXL += "\t\t<beam number=\"1\">begin</beam>\n" + "\t\t<beam number=\"2\">begin</beam>\n";
					if (beam3)
						toMXL += "\t\t<beam number=\"1\">begin</beam>\n" + "\t\t<beam number=\"2\">begin</beam>\n"
								+ "\t\t<beam number=\"3\">begin</beam>\n";
					if (beam4)
						toMXL += "\t\t<beam number=\"1\">begin</beam>\n" + "\t\t<beam number=\"2\">begin</beam>\n"
								+ "\t\t<beam number=\"3\">begin</beam>\n" + "\t\t<beam number=\"4\">begin</beam>\n";
				}

				if (beamContinue1 || beamContinue2) {
					if (beam1)
						toMXL += "\t\t<beam number=\"1\">continue</beam>\n";
					if (beam2)
						toMXL += "\t\t<beam number=\"1\">continue</beam>\n" + "\t\t<beam number=\"2\">continue</beam>\n";
					if (beam3)
						toMXL += "\t\t<beam number=\"1\">continue</beam>\n" + "\t\t<beam number=\"2\">continue</beam>\n"
								+ "\t\t<beam number=\"3\">continue</beam>\n";
					if (beam4)
						toMXL += "\t\t<beam number=\"1\">continue</beam>\n" + "\t\t<beam number=\"2\">continue</beam>\n"
								+ "\t\t<beam number=\"3\">continue</beam>\n" + "\t\t<beam number=\"4\">continue</beam>\n";
				}

				if (beamEnd) {
					if (beam1) 
						toMXL += "\t\t<beam number=\"1\">end</beam>\n";
					if (beam2) 
						toMXL += "\t\t<beam number=\"1\">end</beam>\n" + "\t\t<beam number=\"2\">end</beam>\n";
					if (beam3) 
						toMXL += "\t\t<beam number=\"1\">end</beam>\n" + "\t\t<beam number=\"2\">end</beam>\n"
								+ "\t\t<beam number=\"3\">end</beam>\n";
					if (beam4) 
						toMXL += "\t\t<beam number=\"1\">end</beam>\n" + "\t\t<beam number=\"2\">end</beam>\n"
								+ "\t\t<beam number=\"3\">end</beam>\n" + "\t\t<beam number=\"4\">end</beam>\n";
				}
			}
			toMXL += "\t</note>";

			return toMXL;
		}

		toMXL += this.pitch + "\t\t<duration>" + this.duration + "</duration>\n" + "\t\t<type>" + this.type
				+ "</type>\n";

		if (dot) {
			toMXL += "\t\t<dot/>\n";
		}

		toMXL += "\t\t<stem>down</stem>\n" + "\t\t<notations>\n" + "\t\t\t<technical>\n";

		if (hammerStart || hammerStop || pullStart || pullStop || bend || release) {
			if (hammerStop)
				toMXL += "\t\t\t\t<hammer-on type=\"stop\"/>\n";
			if (hammerStart)
				toMXL += "\t\t\t\t<hammer-on type=\"start\">H</hammer-on>\n";
			if (pullStop)
				toMXL += "\t\t\t\t<pull-off type=\"stop\"/>\n";
			if (pullStart)
				toMXL += "\t\t\t\t<pull-off type=\"start\">P</pull-off>\n";
			if (bend)
				toMXL += "\t\t\t\t<bend>\n" + "\t\t\t\t\t<bend-alter>" + bendAlter + "</bend-alter>\n"
						+ "\t\t\t\t</bend>\n";
			if (release)
				toMXL += "\t\t\t\t<bend>\n" + "\t\t\t\t\t<bend-alter>" + bendAlter + "</bend-alter>\n"
						+ "\t\t\t\t\t<release/>\n" + "\t\t\t\t</bend>\n";
		}

		toMXL += "\t\t\t\t<string>" + this.stringNo + "</string>\n" + "\t\t\t\t<fret>" + this.fret + "</fret>\n"
				+ "\t\t\t</technical>\n";

		if (slurStart || slideStart || slurStop || slideStop) {
			if (slurStop)
				toMXL += "\t\t\t<slur type=\"stop\"/>\n";
			if (slurStart)
				toMXL += "\t\t\t<slur type=\"start\"/>\n";
			if (slideStop)
				toMXL += "\t\t\t<slide type=\"stop\"/>\n";
			if (slideStart)
				toMXL += "\t\t\t<slide type=\"start\"/>\n";
		}
		//System.out.println(toMXL);

		toMXL += "\t\t</notations>\n" + "\t</note>";
		
		return toMXL;
		

	}

	public Pitch getPitch() {
		return this.pitch;
	}

	public Unpitch getUnpitch() {
		return this.unpitch;
	}

	@Override
	public int compareTo(Note object1) {
		return (this.charIndex - object1.charIndex);
	}

}
