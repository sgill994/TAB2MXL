package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.transform.stream.StreamResult;

import TAB2MXL.Measure;
import TAB2MXL.TabError;
import TAB2MXL.TabReader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.Window;
import javafx.scene.control.TextField;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller{

	/*
	 * Translated File + Other Important Imports 
	 */
	private Window stage;
	BufferedReader input;
	StreamResult output;
	static int count = 0;


	/* 
	 * All FXML attributes from PrimaryStage below. 
	 */

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private File file; 

	@FXML
	private TextArea inputBox, outputBox, measureBox;

	@FXML
	public Button select;
	@FXML
	public  Button convert;
	@FXML
	private Button save;
	@FXML
	public Button featureButton;
	@FXML
	private Button startButton;

	@FXML
	private Button helpButton, timeSigButton, keyButton, titleButton, composerButton, editMeasureButton,addTimeSig;

	//@FXML 
	//private Label UploadFileLabel;
	
	@FXML 
	private Label insturmentID, titleID;
	
	@FXML 
	private Button saveInputChanges;
	

	/*
	 * All FXML attributes from HelpWindow called below. 
	 */

	@FXML
	private Button closeButton;

	@FXML 
	private TextArea helpTextArea;

	/*
	 * All FXML attributes from TimeSigniture called below.  
	 */

	@FXML
	private Button cancelButton, SaveTimeSig;

	@FXML 
	private MenuButton beatOption, beatTimeOption;

	@FXML 
	private RadioMenuItem beat1,beat2,beat3,beat4,beat5,beat6;

	@FXML
	private RadioMenuItem beatTime1, beatTime2,beatTime3,beatTime4,beatTime5;

	int beat = 0;

	int beatTime = 0;
	/*
	 *  All FXML attributes from Title called below. 
	 */

	@FXML
	private Button cancelButtonTitle, SaveTitle;

	@FXML
	private TextField titleTextField;

	String title = "";

	/*
	 *  All FXML attributes from Composer + Attributes related called below. 
	 */

	@FXML
	private Button cancelButtonComposer, SaveComposer;

	@FXML 
	private TextField composerText;

	String ComposerName = "";

	/*
	 *  All FXML attributes from Key + Attributes related called below. 
	 */

	@FXML
	private Button cancelKey, saveKey;

	@FXML 
	private MenuButton keyOption;

	@FXML 
	private RadioMenuItem key1,key2,key3,key4,key5,key6,key7,key8,key9,key10,key11,key12,key13,key14,key15; 

	int keyFifths = 0;

	String keySelected = "";
	/*
	 *  All FXML attributes from AlertFileUpload related called below. 
	 */
	@FXML
	private Button continueButton;

	/*
	 *  All FXML attributes for convert success. 
	 */

	@FXML
	private Button continueButtonConvert;	

	/*
	 * Tab parser
	 */
	private static TabReader reader = new TabReader();
	


	
	
	/*
	 * Please see the edit Measure Window elements and methods below 
	 * 
	 */
	@FXML
	void editMeasureButton(){ //this is primary stage 
		Parent root;
		try {
			// used the same style for all popup windows 
			root = FXMLLoader.load(getClass().getResource("EditMeasureWindow.fxml"));
		    Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Choose Measure to Edit");
			popup.setScene(new Scene(root));
			popup.show();
			count ++;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//now its edit measure window elements 
	
	  @FXML
	    private Button cancelMeasure;

	    @FXML
	    private Button saveMeasureEdit;

	    @FXML
	    private TextField startMeasureID;

	    @FXML
	    private TextField endMeasureID;

	    @FXML
	    private TextArea measureEditBox;

	    @FXML
	    private Button showMeasures;


	    @FXML
	    void cancelMeasureEdit() {
	    	cancelMeasure.getScene().getWindow().hide();
	    }

	    @FXML
	    void saveMeasureEdit(ActionEvent event) {
	    	try {
		    	int start = Integer.parseInt(startMeasureID.getText());
		    	int end = Integer.parseInt(endMeasureID.getText());
		    	final int INPUTBOX_ID = 10; // might need to change this later
		    	
				Parent p = Window.getWindows().get(0).getScene().getRoot();
				TextArea input = (TextArea) p.getChildrenUnmodifiable().get(INPUTBOX_ID);
//				System.out.println("INPUT BOX:\n" + input.getText());
				input.setText(reader.editMeasure(start - 1, end - 1, measureEditBox.getText()));
				saveMeasureEdit.getScene().getWindow().hide();
			} catch (Exception e) {e.printStackTrace();}
	    }

	    @FXML
	    void showMeasures() {
	    	int start = Integer.parseInt(startMeasureID.getText());
	    	int end = Integer.parseInt(endMeasureID.getText());
	    	String output = reader.getMeasures(start - 1, end - 1);
	    	System.out.println(start);
	    	System.out.println(output);
	         measureEditBox.setText(output);
	    }


	//This is for editing the timeSig in primary stage
	@FXML
	void addTimeSig() {
		Parent root;
		try {
			// used the same style for all popup windows 
			root = FXMLLoader.load(getClass().getResource("AddTimeSigWindow.fxml"));
			Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Add Time Signature");
			popup.setScene(new Scene(root));
			popup.show();
			count ++;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//NOW ALL ELEMENTS RELATED TO EDIT_TIME_SIG_WINDOW 
	
	//PLEASE NOTE, WE WILL BE USING SAME METHODS FOR BEAT/BEAT TIME MENU as it was already declared. 
	
	@FXML 
	private Button cancelEditTSButton, EditSaveTimeSig;
	
	@FXML 
	private TextField startTimeSigField, endTimeSigField; 
	
	@FXML 
	private void cancelTimeSigTS() {
		cancelEditTSButton.getScene().getWindow().hide();
	}
	
	@FXML 
	private void saveTimeSigClickedEdit() {
		int[] timeSig = new int[2];
		timeSig[0] = 4;
     	timeSig[1] = 4;
		try {
			timeSig[0] = Integer.parseInt(beatOption.getText());
			timeSig[1] = Integer.parseInt(beatTimeOption.getText());
		} catch(Exception e) {}
     	
     	try {
			int startMeasure = Integer.parseInt(startTimeSigField.getText());
			int endMeasure =  Integer.parseInt(endTimeSigField.getText());
			
			reader.setTimeSignatures(timeSig, startMeasure - 1, endMeasure - 1);
		} catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error allert window 
			errorAlert.setHeaderText("Entered measures were not a number or was out of bounds"); 
			errorAlert.showAndWait();
		}

		EditSaveTimeSig.getScene().getWindow().hide();
	}
	
	/*
	 * 
	 *  PrimaryStage Methods related to the FXML documents.  	
	 *
	 */
	
	@FXML
	void saveInputFile(){
		
		try { 
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("textfiles files (*.txt)","*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setInitialFileName("Music676");
			File file = fileChooser.showSaveDialog(stage);
			if(file != null){
				FileWriter myWriter = new FileWriter(file);
				myWriter.write(inputBox.getText());
				myWriter.close();
				//	SaveFile(textInput.getText(), file);
				Alert errorAlert = new Alert(AlertType.INFORMATION); //creates a displayable error allert window 
				errorAlert.setHeaderText("Text file is saved!"); 
				errorAlert.showAndWait();
			}
		}
		catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	 }
		
	Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error allert window 
	 Button errorAl = (Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK);
	
	@FXML
	void ConvertClicked() {
		outputBox.setDisable(false);
		save.setDisable(false);
		editMeasureButton.setDisable(false);
		addTimeSig.setDisable(false);
		save.setVisible(true);
		//if(convert.getText().equals("Convert") && checkTrue(file) == true) {	
		try {
			reader.setInput(inputBox.getText());
				reader.convertTabs();
			outputBox.setText(reader.toMXL());
			displaySuccessConvert();
			displaygetIntrument();
			displaygetTitle();
			displaygetMeasure();
			System.out.println(reader.getInstrument());
			save.setVisible(true);
		}
		catch (ArithmeticException e) {
			Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error allert window 
			errorAlert.setHeaderText("File Input Error");
			errorAlert.setContentText("You have an empty file or the file is unreadable by our program. Please try again.");
			errorAlert.showAndWait();
		}
	}

	@FXML
	void SaveClicked() {
		try { 
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("musicXML files (*.musicxml)","*.musicxml");
				fileChooser.getExtensionFilters().add(extFilter);
				fileChooser.setInitialFileName("Music667");
				File file = fileChooser.showSaveDialog(stage);
				if(file != null){
					FileWriter myWriter = new FileWriter(file);
					myWriter.write(outputBox.getText());
					myWriter.close();
					//	SaveFile(textInput.getText(), file);
					Alert errorAlert = new Alert(AlertType.INFORMATION); //creates a displayable error allert window 
					errorAlert.setHeaderText("File is saved. Happy making music!"); 
					errorAlert.showAndWait();
				}
			}
			catch (IOException ex) {
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
			}}
	

	int size =1;
	@FXML
	void dragFile() {
		inputBox.setOnDragOver(e -> { //e -> dictates action needed 
			Dragboard dragBoard = e.getDragboard(); 
			if (dragBoard.hasFiles() && dragBoard.getFiles().size() == 1) {

				try {
					Path path = FileSystems.getDefault().getPath(dragBoard.getFiles().get(0).getPath());
					if (!Files.probeContentType(path).isEmpty() && Files.probeContentType(path).equals("text/plain")) {
						e.acceptTransferModes(TransferMode.COPY);
					}

				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			} else {
				e.consume();
			}
		});
		inputBox.setOnDragDropped(e->{
			Dragboard db = e.getDragboard();
			boolean success = false;
			if(db.hasFiles()) {
				success = true;

				for(File f : db.getFiles()) {
					file = f;
					outputBox.clear();
					inputBox.setText(readFile(file));
					checkTrue(file);
				    featureButton.setDisable(false);

				}
			}
			e.setDropCompleted(success);
			e.consume();
		});

	}

	public String readFile(File file) {
		String fileName = file.getAbsolutePath();

		StringBuilder stringBuffer = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				stringBuffer.append(text);
				stringBuffer.append("\n"); //makes the input on next line 
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {

		} finally {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
			}
		}

		return stringBuffer.toString();
	}

	@FXML
	public void select() {
		FileChooser fileChooser = new FileChooser(); 
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"); //chooses only txt file 
		fileChooser.setInitialFileName("myfile.txt"); // sets the file name to download 
		fileChooser.getExtensionFilters().add(extFilter);
		try {
		file = fileChooser.showOpenDialog(select.getScene().getWindow()); 	
		//String fileName = file.getAbsolutePath();

		if(!(file.length() == 0)) {
			outputBox.clear();
			inputBox.clear();
			inputBox.setText(readFile(file));

			if (checkTrue(file)) {
				reader = new TabReader();
				reader.setFile(file);
			}
			//step3Label.setVisible(true);
			convert.setDisable(false); 
			featureButton.setDisable(false);
		}
		else {
			ErrorOutput(file);
		}
		}
		catch(Exception e) {
			ErrorOutput(file);
		}
	}

	private boolean checkTrue(File file) {

		if(!(file.length() == 0)) {
			showOtherButtons();
			if (count == 0) {	
				displayErrorPage();
				showOtherButtons();
				count++;
			}
			return true;
		}
		return false;
	}
	private boolean showOtherButtons() {
		if (count>0) {
			convert.setVisible(true);
			featureButton.setVisible(true);
			return true;
		}
		return false;
	}

	private void displayErrorPage(){
		Parent root;
		try {
			// used the same style for all popup windows 
			root = FXMLLoader.load(getClass().getResource("AlertFileUploadSuccess.fxml"));
			Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Error");
			popup.setScene(new Scene(root, 334, 226));
			popup.show();
			count ++;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displaySuccessConvert() {
		Parent root;
		TabError tError = reader.convertTabs();
		String a = tError == null ? "" : tError.getErrorMsg();
		if (!a.equals("MAJOR ERROR")) {
		try {
			root = FXMLLoader.load(getClass().getResource("ConvertSuccess.fxml"));
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			//popup.setTitle("Error");
			popup.setTitle("Success");
			popup.setScene(new Scene(root, 334, 226));
			popup.show();

		} catch (IOException e) {
			e.printStackTrace();
		}}
		else {
			Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error alert window 
			errorAlert.setHeaderText("ERROR: Could not convert properly."); 
			errorAlert.setContentText("Current Software does not recognize certain part(s) to your text"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
			errorAlert.showAndWait();
		}
	}


	private void ErrorOutput(File file) {
		Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error alert window 
		errorAlert.setHeaderText("Input file is not valid. Please ensure your input file is not empty"); 
		errorAlert.setContentText("Provide text file"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
		errorAlert.showAndWait();
	}

	private void displaygetIntrument(){
		insturmentID.setText(reader.getInstrument());
	}
	private void displaygetTitle(){
		if (reader.getTitle()!=null) {
		titleID.setText(reader.getTitle());
	    }
	}
	
	private void displaygetMeasure() {
		TabError tError = reader.convertTabs();
		String a = tError == null ? "" : tError.getErrorMsg();
		measureBox.setDisable(false);
		measureBox.setEditable(false);
		measureBox.setWrapText(true);
		measureBox.setText(a);
		
		
	}

	@FXML 
	void startClick() {
		timeSigButton.setVisible(false);
		keyButton.setVisible(false);
		titleButton.setVisible(false);
		composerButton.setVisible(false);
		//titleButton.setDisable(false);
		//convert.setVisible(false);
		convert.setDisable(false);
		saveInputChanges.setDisable(false);
		save.setVisible(false);
		featureButton.setDisable(false);
		//UploadFileLabel.setText("No File Uploaded");
		inputBox.clear();
		inputBox.setDisable(false);
		outputBox.clear();
		outputBox.setDisable(true);
		select.setDisable(false);
	
	}


	/*
	 * All methods are for help page 
	 */

	@FXML
	void helpclick() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("HelpWindow.fxml"));
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Help");
			popup.setScene(new Scene(root, 600, 340));
			popup.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void closeHelp() {
		closeButton.getScene().getWindow().hide();;
	}


	/*
	 * General Main Page 
	 */
	@FXML
	void selectComposer() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Composer.fxml"));
			final Stage popup = new Stage();
			popup.setTitle("Composer");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setScene(new Scene(root, 334, 226));
			popup.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectKey() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Keys.fxml"));
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Key");
			popup.setScene(new Scene(root, 334, 226));
			popup.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectTimeSig() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("TimeSigniture.fxml"));
			final Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Time Signature");
			popup.setScene(new Scene(root, 334, 226));
			popup.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectTitle() {
		if (select.isVisible() == true) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("Title.fxml"));
				final Stage popup = new Stage();
				popup.initModality(Modality.APPLICATION_MODAL);
				popup.setTitle("Title");
				popup.setScene(new Scene(root, 334, 226));
				popup.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("skipped homie");
		}
	}

	/*
	 * All methods below are for the time signature page 
	 */
	@FXML
	void cancelTimeSig() {
		cancelButton.getScene().getWindow().hide();
	}

	@FXML
	void saveTimeSigClicked() {
		int[] timeSig = new int[2];
		timeSig[0] = 4;
		timeSig[1] = 4;
		
		try {
			timeSig[0] = Integer.parseInt(beatOption.getText());
			timeSig[1] = Integer.parseInt(beatTimeOption.getText());
		} catch(Exception e) {}
		
		reader.setTimeSignature(timeSig);
		SaveTimeSig.getScene().getWindow().hide();
		
//		System.out.println("beat: " + timeSig[0]);
//		System.out.println("beatTime: " + timeSig[1]);
	}

	@FXML
	void beat1Select(ActionEvent event) {
		beat = 1;
		beat1.setSelected(true);
		beatOption.setText(beat1.getText());
		beat2.setSelected(false);
		beat3.setSelected(false);
		beat4.setSelected(false);
		beat5.setSelected(false);
		beat6.setSelected(false);
	}
	
	@FXML
	void beat2Select(ActionEvent event) {
		beat1.setSelected(false);
		beat2.setSelected(true);
		beatOption.setText(beat2.getText());
		beat3.setSelected(false);
		beat4.setSelected(false);
		beat5.setSelected(false);
		beat6.setSelected(false);
	}

	@FXML
	void beat3Select(ActionEvent event) {
		beat =3;
		beat1.setSelected(false);
		beat2.setSelected(false);
		beat3.setSelected(true);
		beatOption.setText(beat3.getText());
		beat4.setSelected(false);
		beat5.setSelected(false);
		beat6.setSelected(false);
	}

	@FXML
	void beat4Select(ActionEvent event) {
		beat =4;
		beat1.setSelected(false);
		beat2.setSelected(false);
		beat3.setSelected(false);
		beat4.setSelected(true);
		beatOption.setText(beat4.getText());
		beat5.setSelected(false);
		beat6.setSelected(false);
	}

	@FXML
	void beat5Select(ActionEvent event) {
		beat = 5;
		beat1.setSelected(false);
		beat2.setSelected(false);
		beat3.setSelected(false);
		beat4.setSelected(false);
		beat5.setSelected(true);
		beatOption.setText(beat5.getText());
		beat6.setSelected(false);
	}

	@FXML
	void beat6Select(ActionEvent event) {
		beat = 6;
		beat1.setSelected(false);
		beat2.setSelected(false);
		beat3.setSelected(false);
		beat4.setSelected(false);
		beat5.setSelected(false);
		beat6.setSelected(true);
		beatOption.setText(beat6.getText());
	}

	@FXML
	void beatTime1(ActionEvent event) {
		beatTime = 1;
		beatTime1.setSelected(true);
		beatTimeOption.setText(beatTime1.getText());
		beatTime2.setSelected(false);
		beatTime3.setSelected(false);
		beatTime4.setSelected(false);
		beatTime5.setSelected(false);
	}

	@FXML
	void beatTime2(ActionEvent event) {
		beatTime =2;
		beatTime1.setSelected(false);
		beatTime2.setSelected(true);
		beatTimeOption.setText(beatTime2.getText());
		beatTime3.setSelected(false);
		beatTime4.setSelected(false);
		beatTime5.setSelected(false);
	}

	@FXML
	void beatTime3(ActionEvent event) {
		beatTime =3;
		beatTime1.setSelected(false);
		beatTime2.setSelected(false);
		beatTime3.setSelected(true);
		beatTimeOption.setText(beatTime3.getText());
		beatTime4.setSelected(false);
		beatTime5.setSelected(false);
	}

	@FXML
	void beatTime4(ActionEvent event) {
		beatTime =4;
		beatTime1.setSelected(false);
		beatTime2.setSelected(false);
		beatTime3.setSelected(false);
		beatTime4.setSelected(true);
		beatTimeOption.setText(beatTime4.getText());
		beatTime5.setSelected(false);
	}

	@FXML
	void beatTime5(ActionEvent event) {
		beatTime = 5;
		beatTime1.setSelected(false);
		beatTime2.setSelected(false);
		beatTime3.setSelected(false);
		beatTime4.setSelected(false);
		beatTime5.setSelected(true);
		beatTimeOption.setText(beatTime5.getText());
	}

	/*
	 * All methods below for title page 
	 */

	@FXML
	void cancelTitle() {
		cancelButtonTitle.getScene().getWindow().hide();;
	}

	@FXML
	void saveTitleClick() {
		SaveTitle.setOnAction( e -> setTitleName(titleTextField.getText()));
		title = titleTextField.getText();
//		System.out.println(title);
		reader.setTitle(title);
		SaveTitle.getScene().getWindow().hide();
		
	}

	private void setTitleName(String title) {
		title = titleTextField.getText();
		this.title = title;
	}
	
	public void returnTitle() {
		System.out.println(title);
	}

	public String getTitle() {
		return title;
	}


	/*
	 * All methods below for composer
	 */

	@FXML
	void cancelComposer() {
		cancelButtonComposer.getScene().getWindow().hide();;
	}

	@FXML
	void saveComposer() {
		ComposerName = composerText.getText();
//		System.out.println(ComposerName);
		reader.setComposer(ComposerName);
		SaveComposer.getScene().getWindow().hide();
	}


	public String getComposerName() {
		return ComposerName;
	}


	/*
	 * All methods for keys 
	 */

	@FXML
	void cancelKey() {
		cancelKey.getScene().getWindow().hide();

	}

	@FXML
	void saveKey() {
		reader.setKey(keyFifths);
		setKey(keySelected);
		saveKey.getScene().getWindow().hide();
		System.out.println(keySelected);
	}

	public String getKey(String key) {
		return keySelected;
	}

	public void setKey(String key) {
		if (key == null) {
			keySelected = "C Minor";
		}
		keySelected = key;
	}

	@FXML
	void chosenKey10() {
		try {
		this.keyFifths = -6;
		setKey(key10.getText());
		key10.setSelected(true); // need to add to all. 
		keyOption.setText(key10.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);
		}
		catch (Exception e) {
		    System.out.println("wow");
		}
	}

	@FXML
	void chosenKey11() {
		this.keyFifths = -5;
		key11.setSelected(true);
		keyOption.setText(key11.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey12() {
		this.keyFifths = -4;
		key12.setSelected(true);
		keyOption.setText(key12.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey13() {
		this.keyFifths = -3;
		key13.setSelected(true);
		keyOption.setText(key13.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey14() {
		this.keyFifths = -2;
		key14.setSelected(true);
		keyOption.setText(key14.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey2() {
		this.keyFifths = 1;
		key2.setSelected(true);
		keyOption.setText(key2.getText());
		key1.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey3() {
		this.keyFifths = 2;
		key3.setSelected(true);
		keyOption.setText(key3.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey4() {
		this.keyFifths = 3;
		key4.setSelected(true);
		keyOption.setText(key4.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey5() {
		this.keyFifths = 4;
		key5.setSelected(true);
		keyOption.setText(key5.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey6() {
		this.keyFifths = 5;
		key6.setSelected(true);
		keyOption.setText(key6.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);
	}

	@FXML
	void chosenKey7() {
		this.keyFifths = 6;
		key7.setSelected(true);
		keyOption.setText(key7.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey8() {
		this.keyFifths = 7;
		key8.setSelected(true);
		keyOption.setText(key8.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenKey9() {
		this.keyFifths = -7;
		key9.setSelected(true);
		keyOption.setText(key9.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);

	}

	@FXML
	void chosenkey1() {
		this.keyFifths = 0;
		key1.setSelected(true);
		keyOption.setText(key1.getText());
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
		key15.setSelected(false);
	}
	
	@FXML
	void chosenKey15() {
		this.keyFifths = -1;
		key15.setSelected(true);
		keyOption.setText(key15.getText());
		key1.setSelected(false);
		key2.setSelected(false);
		key3.setSelected(false);
		key4.setSelected(false);
		key5.setSelected(false);
		key6.setSelected(false);
		key7.setSelected(false);
		key8.setSelected(false);
		key9.setSelected(false);
		key10.setSelected(false);
		key11.setSelected(false);
		key12.setSelected(false);
		key13.setSelected(false);
		key14.setSelected(false);
	}


	/*
	 * Upload File Success Page 
	 */
	
	@FXML
	void continuePage() {
		continueButton.getScene().getWindow().hide();
	}


	@FXML
	private void showFeature() {
		timeSigButton.setDisable(false);
		timeSigButton.setVisible(true);
		keyButton.setDisable(false);
		keyButton.setVisible(true);
		titleButton.setDisable(false);
		titleButton.setVisible(true);
		composerButton.setDisable(false);
		composerButton.setVisible(true);


	}

	/*
	 * Converted File Success
	 */
	@FXML
	void ContinueToSave() {
		continueButtonConvert.getScene().getWindow().hide();
	}

	public void initialize() {
		
	//	assert inputBox != null : "fx:id=\"textOutputAreaXML\" was not injected: check your FXML file 'Untitled'.";
		if (inputBox != null) {
			inputBox.setDisable(true);
		}		

	//	assert outputBox != null : "fx:id=\"textInputFileArea\" was not injected: check your FXML file 'Untitled'.";
		if (outputBox != null) {
			outputBox.setDisable(true);
		}	
		
	//	assert measureBox != null : "fx:id=\"measureBox\" was not injected: check your FXML file 'Untitled'.";
		if (measureBox != null) {
			measureBox.setDisable(true);
		}	

		if (select != null) { 
			select.setDisable(true);
		}	
		assert select != null : "fx:id=\"submit\" was not injected: check your FXML file 'Untitled'.";
		assert convert != null : "fx:id=\"convert\" was not injected: check your FXML file 'Untitled'.";
		if (convert != null) { 
			convert.setDisable(true);
		}


		assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'Untitled'.";
		if (save != null) { 
			save.setDisable(true);
		}

		assert helpButton != null : "fx:id=\"helpButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";

		assert timeSigButton != null : "fx:id=\"timeSigButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		if (timeSigButton != null) {
			timeSigButton.setDisable(true);
		}

		assert keyButton != null : "fx:id=\"keyButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		if (keyButton != null) {
			keyButton.setDisable(true);
		}	

		assert titleButton != null : "fx:id=\"titleButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		if (titleButton != null) {
			titleButton.setDisable(true);
		}
		assert composerButton != null : "fx:id=\"composerButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		if (composerButton != null) {
			composerButton.setDisable(true);
		}

		assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		assert featureButton != null : "fx:id=\"featureButton\" was not injected: check your FXML file 'PrimaryStage.fxml'.";

		if (featureButton != null) {
			featureButton.setDisable(true);
		}
		
		assert saveInputChanges != null : "fx:id=\"saveInputChanges\" was not injected: check your FXML file 'PrimaryStage.fxml'.";
		if (saveInputChanges != null) {
			saveInputChanges.setDisable(true);
		}
		 assert outputBox != null : "fx:id=\"outputBox\" was not injected: check your FXML file 'fxml.fxml'.";
	     //   assert step3Label != null : "fx:id=\"step3Label\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert select != null : "fx:id=\"select\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert convert != null : "fx:id=\"convert\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert helpButton != null : "fx:id=\"helpButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'fxml.fxml'.";
	       // assert step4Label != null : "fx:id=\"step4Label\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert inputBox != null : "fx:id=\"inputBox\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert timeSigButton != null : "fx:id=\"timeSigButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert keyButton != null : "fx:id=\"keyButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert titleButton != null : "fx:id=\"titleButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert composerButton != null : "fx:id=\"composerButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert featureButton != null : "fx:id=\"featureButton\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert insturmentID != null : "fx:id=\"insturmentID\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert titleID != null : "fx:id=\"titleID\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert measureBox != null : "fx:id=\"measureBox\" was not injected: check your FXML file 'fxml.fxml'.";
	        assert saveInputChanges != null : "fx:id=\"saveInputChanges\" was not injected: check your FXML file 'fxml.fxml'.";
	      assert editMeasureButton != null : "fx:id=\"editMeasureButton\" was not injected: check your FXML file 'fxml.fxml'.";
	      if (editMeasureButton != null) {
				editMeasureButton.setDisable(true);
			}
	       assert addTimeSig != null : "fx:id=\"addTimeSig\" was not injected: check your FXML file 'fxml.fxml'.";
	       if (addTimeSig != null) {
				addTimeSig.setDisable(true);
			}
	}



}

