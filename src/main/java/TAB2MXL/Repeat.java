package TAB2MXL;

public class Repeat {
	private int measureNumber, numRepeats;
	private boolean isEnd;

	public Repeat(int measureNumber, boolean isEnd, int numRepeats) {
		this.measureNumber = measureNumber;
		this.isEnd = isEnd;
		this.numRepeats = numRepeats;
	}

	public int getMeasureNumber() {
		return measureNumber;
	}
	
	public boolean getIsEnd() {
		return isEnd;
	}
	
	public int getNumRepeats() {
		return numRepeats;
	}
	
	@Override
	public String toString() {
		String mxl = "\t<barline location=\"" + (isEnd ? "right" : "left") + "\">\n"
				+ "\t\t<bar-style>" + (isEnd ? "light-heavy" : "heavy-light") + "</bar-style>\n"
				+ "\t\t<repeat direction=\"" + (isEnd ? "backward" : "forward") + "\"/>\n"
				+ "\t</barline>\n";

		if (isEnd) {
			mxl += "\t<direction placement=\"above\">\n"
					+ "\t\t<direction-type>\n"
					+ "\t\t\t<words>Repeat " + numRepeats + " times</words>\n"
					+ "\t\t</direction-type>\n"
					+ "\t</direction>\n";
		}

		return mxl;
	}
}
