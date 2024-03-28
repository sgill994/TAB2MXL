package org.testfx;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.MenuItemMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import TAB2MXL.TabReader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import application.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class ApplicationTest {
	private Controller Controller;
	private TabReader reader;
	
	/*
	 * Loads the primary stage 
	 */
	@Start
     private void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/PrimaryStage.fxml"));
		primaryStage.setTitle("TAB2XML");
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			File file = new File("Resources/badMeasure.txt");
		//	Controller.readFile(file);  
		//	reader.convertTabs();
//			primaryStage.show();
			primaryStage.show();
 		}
    	catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Checks whether the inputBox is empty when it loads 
	 */
	@Test
		public void emptyTextArea(FxRobot robot) {
			robot.clickOn("#startButton");
			FxAssert.verifyThat("#inputBox", TextInputControlMatchers.hasText(""));		
	}
	
	/*
	 *  Checks the convert button label
	 */
	
	@Test
	void should_contain_button_with_convert(FxRobot robot) {
		FxAssert.verifyThat("#convert", LabeledMatchers.hasText("Convert"));
	}
	
	/*
	 *  Checks the start button label
	 */
	
    @Test
	void should_contain_button_with_start(FxRobot robot) {
	    FxAssert.verifyThat("#startButton", LabeledMatchers.hasText("Start"));
    }
    
    /*
	 *  Checks the feature button label works and other buttons are enabled 
	 */
    @Test
	public void clickFeatureResult(FxRobot robot) {
    	robot.clickOn("#startButton");
		robot.clickOn("#featureButton");
		FxAssert.verifyThat("#featureButton", LabeledMatchers.hasText("Show More Features"));
	}
    
    /*
	 *  Checks the help button label
	 */

    @Test
 	void should_contain_button_with_help(FxRobot robot) {
   	    FxAssert.verifyThat("#helpButton", LabeledMatchers.hasText("Help"));
    }
    
    /*
	 *  Checks the save button label loaded at first 
	 */
    @Test 
    void should_contain_button_with_save(FxRobot robot) {
    	 FxAssert.verifyThat("#save", LabeledMatchers.hasText("Save MusicXML File"));
    }
    
    /*
	 *  Checks the select button label
	 */

    @Test 
    void should_contain_button_with_select(FxRobot robot) {
    	 FxAssert.verifyThat("#select", LabeledMatchers.hasText("Select File"));
    }
    
    /*
	 *  Checks the feature button label
	 */
    @Test 
    void should_contain_button_with_feature(FxRobot robot) {
    	 FxAssert.verifyThat("#featureButton", LabeledMatchers.hasText("Show More Features"));
    }

    /*
	 *  Checks the timeSigButton label loaded
	 */

    @Test 
    void should_contain_button_with_timeSigButton(FxRobot robot) {
    	 FxAssert.verifyThat("#timeSigButton", LabeledMatchers.hasText("Select Time Signiture"));
    }
    
    /*
	 *  Checks the key button label
	 */

    @Test 
    void should_contain_button_with_keyButton(FxRobot robot) {
    	 FxAssert.verifyThat("#keyButton", LabeledMatchers.hasText("Select Key"));
    }
   
    /*
	 *  Checks the title button label
	 */
    @Test 
    void should_contain_button_with_titleButton(FxRobot robot) {
    	 FxAssert.verifyThat("#titleButton", LabeledMatchers.hasText("Specify Title "));
    }
    /*
	 *  Checks the composer button label
	 */
    @Test 
    void should_contain_button_with_composerButton(FxRobot robot) {
    	 FxAssert.verifyThat("#composerButton", LabeledMatchers.hasText("Specify Composer"));
    }
    
    /*
	 *  Checks that program can handle error from user text gracefully 
	 */
  
    
    @Test 
    public void showsErrorInput(FxRobot robot) throws InterruptedException {
    	robot.clickOn("#startButton");
    	robot.clickOn("#inputBox");
    	robot.write("hi");
    	robot.clickOn("#convert");
    	robot.clickOn(850, 350);
        FxAssert.verifyThat("#outputBox",TextInputControlMatchers.hasText(""));
    }
    
    /*
	 *  Checks that program updates the instrument detection after an input 
	 */
//    @Test 
//    public void properInputTest(FxRobot robot) throws AssertionError{
//    	robot.clickOn("#startButton");
//    	robot.clickOn("#inputBox");
//    	robot.write("e|----15p7-----5p7-|-8-----8-2-----2-|-0---------0-----|\r\n");
//    	robot.clickOn("#convert");
//    	
//    	FxAssert.verifyThat("#insturmentID", LabeledMatchers.hasText("No Intrument Detected"));
//    } 
    
    /*
	 *  Checks that program updates the title detection after an input 
	 */
//    
//    @Test
//   public void properSelection(FxRobot robot) {    
//    	robot.clickOn("#startButton");
//    	robot.clickOn("#select");
//    	robot.clickOn(250, 220);
//    	robot.sleep(700);
//    	robot.doubleClickOn(300, 220);
//    	 FxAssert.verifyThat("#select", LabeledMatchers.hasText("Select File"));
//   }
    
    /*
   	 *  Checks that program successful can save a translated file without errors 
   	 */
    
//      @Test 
//    public void verifySaving(FxRobot robot) throws InterruptedException {
//    	robot.clickOn("#startButton");
//    	robot.clickOn("#inputBox");
//    	robot.write("e|----15p7-----5p7-|-8-----8-2-----2-|-0---------0-----|\r\n");
//    	robot.clickOn("#convert");
//    	//robot.clickOn(850, 350);
//    	robot.clickOn("#save");
//     	robot.doubleClickOn(350,420);
//    	robot.clickOn(650,490);
//    	robot.write("save");
//    	robot.clickOn(850, 345);
//      	FxAssert.verifyThat("#save", LabeledMatchers.hasText("Save MusicXML File"));
//      //  FxAssert.verifyThat("#outputBox",TextInputControlMatchers.hasText(""));
//    }
     
     /*
  	 *  Checks that program saves any changes in the input box as a text file 
  	 */
      
//    @Test
//    public void testSaveTextFile(FxRobot robot) {
//    	robot.clickOn("#startButton");
//    	robot.clickOn("#inputBox");
//    	robot.write("e|----15p7-----5p7-|-8-----8-2-----2-|-0---------0-----|\r\n");
//    	robot.clickOn("#saveInputChanges");
//    	robot.doubleClickOn(350,420);
//    	robot.clickOn(650,490);
//    	robot.write("save");
//    	//robot.clickOn(850, 345);
//    	FxAssert.verifyThat("#startButton", LabeledMatchers.hasText("Start"));
//    
//    }
    }

