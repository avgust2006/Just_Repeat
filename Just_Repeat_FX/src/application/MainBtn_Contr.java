package application;

 

import java.io.File;
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
	             Verbs_Btn_Pane1;
 
	@FXML
	private Button Main_Button,
	               Repeat,
	               Next,
	               verbs,
	               verbs_1
	               ;	 
	 
	@FXML 
	private Label Word,
	              Translation;
	 
	
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
		   setAllPaneVisible(true);		        
		  }
		 
		   if (Main_Button.getText().equalsIgnoreCase("START")) {
			   setAllPaneVisible(false);
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
	   * This method handles of Verbs button pressing event		
	  */ 
	  public void actionDict_1(ActionEvent e) {
		 //  files.Dict1_Downloads("C:\\Users\\Public\\Just_Repeat\\dictionaries\\verbs");
		  //  files.Dict1_Downloads("dictionaries/verbs");
		   files.dict_Downloads("src/main/resources/dictionaries/verbs");
		   verbs.setOpacity(0);
		   setOnePaneVisible(Verbs_Btn_Pane);
		   Verbs_Btn_Pane.setLayoutY(194);
		   Verbs_Btn_Pane.setLayoutX(265);
		   Main_Button.setText("START");
		   files.fadeChange(Main_Button,verbs, Verbs_Btn_Pane);
		    Main_Button.setVisible(true); 
		    
		    		   		  
	}
	  
	  public void actionVerbs_1(ActionEvent e) {
		  files.dict_Downloads("src/main/resources/dictionaries/verbs_1");
		  verbs_1.setOpacity(0); 
		  setOnePaneVisible(Verbs_Btn_Pane1);
		   Verbs_Btn_Pane1.setLayoutY(194);
		   Verbs_Btn_Pane1.setLayoutX(265);
		   Main_Button.setText("START");
		   files.fadeChange(Main_Button,verbs_1, Verbs_Btn_Pane1);
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
			  setAllPaneVisible(true);
			  
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
	  private void setAllPaneVisible (boolean set) {
		  if (set) {
			  Verbs_Btn_Pane.setVisible(true);
			  Verbs_Btn_Pane1.setVisible(true); 
			  
		  }else {
			  Verbs_Btn_Pane.setVisible(false);
			  Verbs_Btn_Pane1.setVisible(false); 
			  
		  }
	  }
	  
	  private void setOnePaneVisible (Pane pane ) {
		  ArrayList<Pane> panes =new ArrayList<Pane>();
		  panes.add(Verbs_Btn_Pane);
		  panes.add(Verbs_Btn_Pane1);
		  for (Pane p: panes) {
			  if (!(pane.getId().equalsIgnoreCase(p.getId()))){
				  p.setVisible(false);
			  }
			  
		  }	  
	  }
	  

}
