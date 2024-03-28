package TAB2MXL;

import java.util.List;

public class Attributes {
	private List<String> guitarTuning;
	private static int key;
	private int beats, beatType;
	boolean isOnlyTimeSig;
	
	public Attributes(List<String> guitarTuning, int beats, int beatType) {
		this.guitarTuning = guitarTuning;
		this.beats = beats;
		this.beatType = beatType;
		Attributes.key = 0;
		this.isOnlyTimeSig = false;
	}
	
	public Attributes(int beats, int beatType) {
		this.beats = beats;
		this.beatType = beatType;
		this.isOnlyTimeSig = true;
	}

	public void setKey(int key) {
		Attributes.key = key;
	}
	
	@Override
	public String toString() {
		if (isOnlyTimeSig) {
			return "\t<attributes>\n"
					+ "\t\t<time>\n"
					+ "\t\t\t<beats>" + beats + "</beats>\n"
					+ "\t\t\t<beat-type>" + beatType + "</beat-type>\n"
					+ "\t\t</time>\n"
					+ "\t</attributes>";
		}
		
		if (TabReader.instrument.equals("Drumset")) {
			return "\t<attributes>\n"
					+ "\t\t<divisions>8</divisions>\n"
					+ "\t\t<key>\n"
					+ "\t\t\t<fifths>" + key + "</fifths>\n"
					+ "\t\t</key>\n"
					+ "\t\t<time>\n"
					+ "\t\t\t<beats>" + beats + "</beats>\n"
					+ "\t\t\t<beat-type>" + beatType + "</beat-type>\n"
					+ "\t\t</time>\n"
					+ "\t\t<clef>\n"
					+ "\t\t\t<sign>percussion</sign>\n"
					+ "\t\t\t<line>2</line>\n"
					+ "\t\t</clef>\n"
					+ "\t</attributes>";
		}

		String mxl = "\t<attributes>"
				+ "\n\t\t<divisions>8</divisions>"
				+ "\n\t\t<key>"
				+ "\n\t\t\t<fifths>" + key + "</fifths>"
				+ "\n\t\t</key>"
				+ "\n\t\t<time>"
				+ "\n\t\t\t<beats>" + beats + "</beats>"
				+ "\n\t\t\t<beat-type>" + beatType + "</beat-type>"
				+ "\n\t\t</time>"
				+ "\n\t\t<clef>"
				+ "\n\t\t\t<sign>TAB</sign>"
				+ "\n\t\t\t<line>5</line>"
				+ "\n\t\t</clef>"
				+ "\n\t\t<staff-details>";

		mxl += "\n\t\t\t<staff-lines>" + guitarTuning.size() + "</staff-lines>";
		for (int i = 1; i <= guitarTuning.size(); i++) {
			int index = (guitarTuning.size() - i);
			mxl += "\n\t\t\t<staff-tuning line=\"" + i + "\">"
					+ "\n\t\t\t\t<tuning-step>" 
					+ new Pitch(index + 1, guitarTuning.get(index), 0).getStep() 
					+ "</tuning-step>"
					+ "\n\t\t\t\t<tuning-octave>" 
					+ new Pitch(index + 1, guitarTuning.get(index), 0).getOctave() 
					+ "</tuning-octave>"
					+ "\n\t\t\t</staff-tuning>";
		}
		mxl += "\n\t\t</staff-details>"
				+ "\n\t</attributes>";

		return mxl;
	}
}

