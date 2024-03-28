package TAB2MXL;

public class TabError {
	private int measureNumber;
	private String measure, errorMsg;

	public TabError(int measureNumber, String measure, String errorMsg) {
		this.errorMsg = errorMsg;
		this.measureNumber = measureNumber;
		this.measure = measure;
	}

	public int getMeasureNumber() {
		return measureNumber;
	}

	public String getMeasure() {
		return measure;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
}