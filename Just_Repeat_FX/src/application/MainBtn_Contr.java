package application;

 

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

 
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
 

/*
 * This code executes main window of my program. User can choose dictionary and start
 * learning
 */


public class MainBtn_Contr {
	@FXML  //this abbreviation is necessary for declaration elements in start window
	private Pane Main_Pane,
	             Verbs_Btn_Pane,
	             Verbs_Btn_Pane1,
	             Travel_Btn_Pane;
 
	@FXML
	private Button Main_Button,
	               Repeat,
	               Next,
	               verbs,
	               verbs_1,
	               Repeat_2,
	               travel
	               ;	 
	 
	@FXML 
	private Label Word,
	              Translation;
	 
	
	//private Map <String,File> instance_map;
	private Map <String,byte[]> instance_map;
	private boolean translate;
	private int counter;
	private Random randomize;
	private double X_Initlayout,
	               Y_Initlayout;
	private String currentPane;
	private ArrayList<Pane> panes;
	
 	Sound_Files files;
	URLSoundFiles urlstream;
	IS_Sound_Files inputstream;
	
	 
	
	public MainBtn_Contr () {
		 counter=0;
		 translate=false;
 		 files = new Sound_Files ();
		// instance_map = new HashMap<String,File>();
 		instance_map = new HashMap<String,byte[]>();
		 randomize=new Random();
		 currentPane="";
		 X_Initlayout=0;
		 Y_Initlayout=0;
		 panes= new ArrayList<Pane>();
		 urlstream=new URLSoundFiles();
		 inputstream=new IS_Sound_Files();
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
		   fillDictPanes();
		   files.setAllPaneVisible(true,panes);	
		  }
		 
		   if (Main_Button.getText().equalsIgnoreCase("START")) {
			//   setAllPaneVisible(false);
			 files.setAllPaneVisible(false,panes);
	    	 Main_Button.setText("FINISH"); 
	    	 Repeat.setVisible(true);
	    	 Next.setVisible(true);
	    	 counter=0;
	    	 int rand = randomize.nextInt(inputstream.finish_table.size()) ;
	    	 for (Map<String, byte[]> SF:inputstream.finish_table.keySet()) {
	    		 if (rand==counter) {
	    		  for (String s: SF.keySet()) {
	    			  if (translate==false) {	
	    				  Word.setVisible(true);
	    				  Word.setText(s); 
	    				  inputstream.playAudioFile(SF.get(s));    				 
	    				  translate=true;
	    				  inputstream.saveLastKey(SF);
	    				  Next.setText("TRANSLATE");
	    			  }
	    		  }
	    	   }
	    		 counter++; 
	    	 }	
	    	/* 
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
	    	 */
	     } 
	  }
	  
	    
	  /*
	   * This method handles of VERBS button pressing event		
	  */ 
	  public void actionDict_1(ActionEvent e) {
		  
		 //  files.Dict1_Downloads("C:\\Users\\Public\\Just_Repeat\\dictionaries\\verbs");
		 //     files.dict_Downloads(getClass().getResource("src/main/resources/dictionaries/verbs").toString());
		  inputstream.dict_Downloads_Stream ("/main/resources/dictionaries/verbs/"); 
		 // imputstream.loadFilesToMap ("main/resources/dictionaries/verbs");
		   verbs.setOpacity(0);
		   inputstream.setOnePaneVisible(Verbs_Btn_Pane,panes);
		   takePaneInitPosition(Verbs_Btn_Pane); 
		   Verbs_Btn_Pane.setLayoutY(194);
		   Verbs_Btn_Pane.setLayoutX(265);
		   Main_Button.setText("START");
		   inputstream.fadeChange(Main_Button,verbs, Verbs_Btn_Pane);
		    Main_Button.setVisible(true); 
		    
		/*    
		//  files.Dict1_Downloads("C:\\Users\\Public\\Just_Repeat\\dictionaries\\verbs");
			 //     files.dict_Downloads(getClass().getResource("src/main/resources/dictionaries/verbs").toString());
		 	      files.dict_Downloads_Stream ("src/main/resources/dictionaries/verbs"); 
			 // imputstream.loadFilesToMap ("main/resources/dictionaries/verbs");
			   verbs.setOpacity(0);
			   files.setOnePaneVisible(Verbs_Btn_Pane,panes);
			   takePaneInitPosition(Verbs_Btn_Pane); 
			   Verbs_Btn_Pane.setLayoutY(194);
			   Verbs_Btn_Pane.setLayoutX(265);
			   Main_Button.setText("START");
			   files.fadeChange(Main_Button,verbs, Verbs_Btn_Pane);
			    Main_Button.setVisible(true); 
		    */
		    		   		  
	}
	  
	  public void actionVerbs_1(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/verbs_1/");
		  verbs_1.setOpacity(0); 
		  inputstream.setOnePaneVisible(Verbs_Btn_Pane1,panes);
		  takePaneInitPosition(Verbs_Btn_Pane1); 
		   Verbs_Btn_Pane1.setLayoutY(194);
		   Verbs_Btn_Pane1.setLayoutX(265);
		   Main_Button.setText("START");
		   inputstream.fadeChange(Main_Button,verbs_1, Verbs_Btn_Pane1);
		    Main_Button.setVisible(true); 
		    
		    /*
		     * files.dict_Downloads("src/main/resources/dictionaries/verbs_1");
		  verbs_1.setOpacity(0); 
		  files.setOnePaneVisible(Verbs_Btn_Pane1,panes);
		  takePaneInitPosition(Verbs_Btn_Pane1); 
		   Verbs_Btn_Pane1.setLayoutY(194);
		   Verbs_Btn_Pane1.setLayoutX(265);
		   Main_Button.setText("START");
		   files.fadeChange(Main_Button,verbs_1, Verbs_Btn_Pane1);
		    Main_Button.setVisible(true);  
		     * */
		  
	  }
	  
	  public void actionTravel(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/travel/");
		  travel.setOpacity(0);
		  inputstream.setOnePaneVisible(Travel_Btn_Pane, panes);
		  takePaneInitPosition(Travel_Btn_Pane); 
		  Travel_Btn_Pane.setLayoutY(194);
		  Travel_Btn_Pane.setLayoutX(265);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, travel, Travel_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		 /* 
		   inputstream.dict_Downloads_Stream("/main/resources/dictionaries/travel/");  
		   
		//  urlstream.dict_Downloads_Stream("/main/resources/dictionaries/travel/"); 
		  Translation.setVisible(true);
		  Word.setVisible(true);
		  int i=0;
		  for (Map<String, byte[]> SF:inputstream.finish_table.keySet()) {
			  if (i==0) {
			  for (String s: SF.keySet()) {
				  Word.setText(s); 
			 	  inputstream.playbyteAudioFile(SF.get(s)); 
			 	  break;
			 	  
			  }
			  
			  } 
			  i++;
		  }
		  inputstream.finish_table.clear();
	//	  urlstream.loadURLToMap("/main/resources/dictionaries/travel/flight.wav");
		  */
		  
	  }
	  
	  public void actionNext(ActionEvent e) {  
		  
		  //handling  NEXT/TRANSLATION button (located in the right bottom angle)
	  	  
		  if (Next.getText().equalsIgnoreCase("TRANSLATE")
				  && inputstream.finish_table.containsKey(inputstream.last_key)) {
			  instance_map.clear();
			  instance_map.putAll(inputstream.finish_table.get(inputstream.last_key));
			  for(String s:instance_map.keySet()) {
				   Repeat_2.setVisible(true);
				  Translation.setVisible(true);
				   Translation.setText(s);
				   inputstream.playAudioFile(instance_map.get(s));
				   Next.setText("NEXT");
				   inputstream.finish_table.remove(inputstream.last_key);
				   translate=false;
				    
			  }
			  
		  } else if (inputstream.finish_table.size()==0){           //action, when last word in the dictionary is managed
			  Repeat.setVisible(false);
			  Repeat_2.setVisible(false);
			  Next.setVisible(false); 
			  Translation.setVisible(false);
			  Word.setVisible(false);	
			  setPaneInitPosition(currentPane);
			  //setAllPaneVisible(true);
			  inputstream.setAllPaneVisible(true,panes);
			  
		  } else  {
			 
		   counter=0;	
		   int rand_1 = randomize.nextInt(inputstream.finish_table.size()) ;
           for (Map<String, byte[]> SF:inputstream.finish_table.keySet()) {
        	   
        	   if (rand_1==counter) { 
	    		  
	    		  for (String s: SF.keySet()) {
	    			    if (translate==false) {
	    			      Repeat_2.setVisible(false);
	    				  Word.setText(s);
	    				  Translation.setText(" ");
	    				  inputstream.playAudioFile(SF.get(s));    				 				  
	    				  inputstream.saveLastKey(SF);
	    				  Next.setText("TRANSLATE");
	    				  translate=true;
	    			    }
	    			  
	    		  }
                }
        	   counter++;
	    	 }
		  
		  }
		  
		 /* 
		  //handling  NEXT/TRANSLATION button (located in the right bottom angle)
		  	  
		  if (Next.getText().equalsIgnoreCase("TRANSLATE")
				  && files.finish_table.containsKey(files.last_key)) {
			  instance_map.clear();
			  instance_map.putAll(files.finish_table.get(files.last_key));
			  for(String s:instance_map.keySet()) {
				   Repeat_2.setVisible(true);
				  Translation.setVisible(true);
				   Translation.setText(s);
				   files.playAudioFile(instance_map.get(s));
				   Next.setText("NEXT");
				   files.finish_table.remove(files.last_key);
				   translate=false;
				    
			  }
			  
		  } else if (files.finish_table.size()==0){           //action, when last word in the dictionary is managed
			  Repeat.setVisible(false);
			  Repeat_2.setVisible(false);
			  Next.setVisible(false); 
			  Translation.setVisible(false);
			  Word.setVisible(false);	
			  setPaneInitPosition(currentPane);
			  //setAllPaneVisible(true);
			  files.setAllPaneVisible(true,panes);
			  
		  } else  {
			 
		   counter=0;	
		   int rand_1 = randomize.nextInt(files.finish_table.size()) ;
           for (Map<String, File> SF:files.finish_table.keySet()) {
        	   
        	   if (rand_1==counter) { 
	    		  
	    		  for (String s: SF.keySet()) {
	    			    if (translate==false) {
	    			      Repeat_2.setVisible(false);
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
		 */
	  }
	  
	  public void actionRepeat(ActionEvent e) {
		  
		  for (String s: inputstream.last_key.keySet()) {
			  inputstream.playAudioFile(inputstream.last_key.get(s)); 
		  }
		  /*		  
			  for (String s: files.last_key.keySet()) {
				  files.playAudioFile(files.last_key.get(s));  
			  }
			*/  
	  }
	  
	  public void actionRepeat_1(ActionEvent e) {
		  
		  for (String s: inputstream.last_value.keySet()) {
			  inputstream.playAudioFile(inputstream.last_value.get(s));  
		  }
  		  
		  /*
		  for (String s: files.last_value.keySet()) {
			   files.playAudioFile(files.last_value.get(s));  
		  }
		  */
  }
	 
	  
	  private void takePaneInitPosition(Pane pane) {
		  if(X_Initlayout==0 && Y_Initlayout==0) {
		   currentPane=pane.getId();
		   X_Initlayout=pane.getLayoutX();
		   Y_Initlayout=pane.getLayoutY();  
		  }
	  }
	  private void setPaneInitPosition (String PaneName ) {
		 
		  for (Pane p: panes) {
			  if ((p.getId().equalsIgnoreCase(PaneName))){
				  p.setLayoutX(X_Initlayout);
				  p.setLayoutY(Y_Initlayout);
				  X_Initlayout=0;
				  Y_Initlayout=0;
			  }
			  
		  }	  
	  }
	  
	  private void fillDictPanes() {
		  panes.add(Verbs_Btn_Pane);
		  panes.add(Verbs_Btn_Pane1);
		  panes.add(Travel_Btn_Pane);
	  }
	  
	 
	  

}
