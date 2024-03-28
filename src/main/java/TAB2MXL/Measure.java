package TAB2MXL;

import java.util.ArrayList;
import java.util.Collections;

public class Measure {
	private ArrayList<Note> notes;
	private int measureNumber;
	private Attributes a, a2;
	private int indexTotal;
	public double durationVal;
	private Repeat repeat;

	public Measure(int measureNumber) {
		this.notes = new ArrayList<Note>();
		this.measureNumber = measureNumber;
		this.a = null;
		this.a2 = null;
	}
	
	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}

	public void setAttributes(Attributes a) {
		this.a = a;
	}
	
	public void setAttributes2(Attributes a2) {
		this.a2 = a2;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	@Override
	public String toString() {
		
		String mxl = "<measure number=\"" + measureNumber + "\">\n";

		if (a != null) {
			mxl += a + "\n";
		}
		
		if (a2 != null) {
			mxl += a2 + "\n";
		}

		for (Note note : notes) {
			mxl += note + "\n";
		}
		
		if (repeat != null) {
			mxl += repeat;
		}

			mxl += "</measure>";

		return mxl;
	}

	public Note getNote(int index) {
		return notes.get(index);
	}

	public int size() {
		return notes.size();
	}

	public void sortArray() {
		Collections.sort(notes);

	}

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public int getIndexTotal() {
		return indexTotal;
	}
	
	public void setIndexTotal(int indexTotal) {
		this.indexTotal = indexTotal;
	}
	
	public void setGrace() {
		for(int i = 1; i < notes.size(); i++) {
			if (notes.get(i).slurStart && notes.get(i).slurStop && notes.get(i-1).grace)
				notes.get(i).grace = true;
		}
	}
	
}