package application;

 

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 * This code executes main window of my program. User can choose dictionary and start
 * learning
 */


public class MainBtn_Contr {
	@FXML  //this abbreviation is necessary for declaration elements in start window
	private Pane Main_Pane;
	@FXML
	private Button Main_Button;
	@FXML
	private Button Dict_1;
	@FXML
	private Button Dict_2;
	@FXML
	private Button Dict_3;
	@FXML
	private Button Dict_4;
	@FXML
	private Button Dict_5;
	@FXML
	private Button Repeat;
	@FXML
	private Button Next;
	@FXML 
	private Label Word;
	@FXML 
	private Label Translation;
	
	private Map <String,File> instance_map;
	private boolean translate;
	private int counter;
	private Random randomize;
	
	
	Sound_Files files;
	
	 
	
	public MainBtn_Contr () {
		 counter=0;
		 translate=false;
		 files = new Sound_Files ();
		 instance_map = new HashMap<String,File>();
		 randomize=new Random();
	}
	
	/*
	 * This method handles events of main button actions(start learning, next..)
	 */
	
	  public void actionPerformed(ActionEvent e) {
		  if (Main_Button.getText().equalsIgnoreCase("FINISH")) {
			  Platform.exit();
		  }
		  
		 if ( Main_Button.getText().equalsIgnoreCase("DICTIONARIES")) {
		   Main_Button.setVisible(false);  		   
		   Dict_1.setVisible(true);   
		   Dict_2.setVisible(true);   
		   Dict_3.setVisible(true); 
		   Dict_4.setVisible(true);   
		   Dict_5.setVisible(true);
		  }
		 
		   if (Main_Button.getText().equalsIgnoreCase("START")) {
	    	 Dict_1.setVisible(false); 
	    	 Main_Button.setText("FINISH"); 
	    	 Repeat.setVisible(true);
	    	 Next.setVisible(true);
	    	 counter=0;
	    	 int rand = randomize.nextInt(files.finish_table.size()) ;
	    	 for (Map<String, File> SF:files.finish_table.keySet()) {
	    		 if (rand==counter) {
	    		  for (String s: SF.keySet()) {
	    			  if (translate==false) {	
	    				  Word.setVisible(true);
	    				  Word.setText(s); 
	    				  files.playAudioFile(SF.get(s));    				 
	    				  translate=true;
	    				  files.saveLastKey(SF);
	    				  Next.setText("TRANSLATE");
	    			  }
	    		  }
	    	   }
	    		 counter++; 
	    	 }	    	 	    	 
	     } 
	  }
	  
	    
	  /*
	   * This method handles events of first dictionary button actions(choice dictionary)		
	  */ 
	  public void actionDict_1(ActionEvent e) {
		 //  files.Dict1_Downloads("C:\\Users\\Public\\Just_Repeat\\dictionaries\\verbs");
		  //  files.Dict1_Downloads("dictionaries/verbs");
		   files.Dict1_Downloads("src/main/resources/dictionaries/verbs");
		   Dict_1.setOpacity(0);
		   Dict_2.setVisible(false);
		   Dict_3.setVisible(false);
		   Dict_4.setVisible(false);
		   Dict_5.setVisible(false);
		   Dict_1.setLayoutY(Main_Pane.getHeight()/2);
		   Dict_1.setLayoutX(Main_Pane.getWidth()/2-13);
		   Main_Button.setText("START");
		   FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), Dict_1);
		    fadeIn.setFromValue(0);
		    fadeIn.setToValue(1);
		    fadeIn.play();	
		    FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), Main_Button);
		    fadeIn1.setFromValue(0);
		    fadeIn1.setToValue(1);
		    fadeIn1.play();	
		    Main_Button.setVisible(true); 
		    
		    		   		  
	}
	  
	  public void actionNext(ActionEvent e) {
		  	  
		  if (Next.getText().equalsIgnoreCase("TRANSLATE")
				  && files.finish_table.containsKey(files.last_key)) {
			  instance_map.clear();
			  instance_map.putAll(files.finish_table.get(files.last_key));
			  for(String s:instance_map.keySet()) {
				  Translation.setVisible(true);
				   Translation.setText(s);
				   files.playAudioFile(instance_map.get(s));
				   Next.setText("NEXT");
				   files.finish_table.remove(files.last_key);
				   translate=false;
				    
			  }
			  
		  } else if (files.finish_table.size()==0){
			  
			  Next.setVisible(false); 
			  Translation.setText(" ");
			  Translation.setVisible(false);
			  Word.setText(" ");
			  Word.setVisible(false);
			  Repeat.setVisible(false);
			  Dict_1.setVisible(true);
			  Dict_2.setVisible(true);
			  Dict_3.setVisible(true);
			  Dict_4.setVisible(true);
			  Dict_5.setVisible(true);
			  
		  } else  {
			 
		   counter=0;	
		   int rand_1 = randomize.nextInt(files.finish_table.size()) ;
           for (Map<String, File> SF:files.finish_table.keySet()) {
        	   
        	   if (rand_1==counter) { 
	    		  
	    		  for (String s: SF.keySet()) {
	    			    if (translate==false) {
	    				  Word.setText(s);
	    				  Translation.setText(" ");
	    				  files.playAudioFile(SF.get(s));    				 				  
	    				  files.saveLastKey(SF);
	    				  Next.setText("TRANSLATE");
	    				  translate=true;
	    			    }
	    			  
	    		  }
                }
        	   counter++;
	    	 }
		  
		  }
		 
	  }
	  
	  public void actionRepeat(ActionEvent e) {
		  		  
			  for (String s: files.last_key.keySet()) {
				  Word.setText(s); 
				  files.playAudioFile(files.last_key.get(s));  
			  }
	  }

}
