package application;

 

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

 
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

/*
 * This code executes main window of my program. User can choose dictionary and start
 * learning
 */


public class MainBtn_Contr {
	@FXML  //this abbreviation is necessary for declaration elements in start window
	
	private Pane Main_Pane,   //main window of program
	             Verbs_Btn_Pane,  // pane for activate "VERBS" button dictionary
	             Verbs_Btn_Pane1,  // pane for activate "VERBS+" button dictionary
	             Travel_Btn_Pane,  // pane for activate "TRAVEL" button dictionary
	             House_Btn_Pane,  //  pane for activate "TRAVEL" button dictionary
	             Clothes_Btn_Pane, //  pane for activate "CLOTHES" button dictionary
	             Food_Btn_Pane,   //  pane for activate "FOOD" button dictionary
	             Nature_Btn_Pane,   //  pane for activate "NATURE" button dictionary
	             Transport_Btn_Pane, //  pane for activate "TRANSPORT" button dictionary
	             Body_Btn_Pane, //  pane for activate "BODY" button dictionary
	             Imp_Btn_Pane,  //  pane for activate "IMPORTANT" button dictionary
	             Education_Btn_Pane,  //  pane for activate "EDUCATION" button dictionary
	             Sport_Btn_Pane,  //  pane for activate "SPORT" button dictionary
	             Family_Btn_Pane,  //  pane for activate "FAMILY" button dictionary
	             Health_Btn_Pane,  //  pane for activate "HEALTH" button dictionary
	             Date_Btn_Pane,   //  pane for activate "DATE and NUMBERS" button dictionary
	             Money_Btn_Pane,  //  pane for activate "MONEY" button dictionary
	             Pronoun_Btn_Pane, //  pane for activate "PRONOUNS" button dictionary
	             Preposition_Btn_Pane, //  pane for activate "PREPOSITION" button dictionary 
	             Adjective_Btn_Pane,  //  pane for activate "ADJECTIVES" button dictionary
	             Common_Btn_Pane, //  pane for activate "COMMON" button dictionary 
                 Job_Btn_Pane ;  //  pane for activate "JOB" button dictionary
 
	@FXML
	private Button Main_Button,  // "FINISH", "DICTIONARIES", "START" buttons
	               Repeat,     //the button to repeat sound   in left panel
	               Next,      // "TRANSLATE","NEXT"
	               Return,    // "DICTIONARIES" but that return beginning window of the program
	               verbs,     // "VERBS"
	               verbs_1,   //  "VERBS1"
	               Repeat_2,  //the button to repeat sound   in right panel
	               travel,     // "TRAVEL"
	               house,      // "HOUSE"
	               clothes,    // "CLOTHES"
	               food,       // "FOOD"
	               nature,     // "NATURE"
	               transport,  // "TRANSPORT"
	               body,      // "BODY"
	               important, // "IMPORTANT"
	               education, // "EDUCATION"
	               sport,     // "SPORT"
	               job,       // "JOB"
	               family,    // "FAMILY"
	               health,    // "HEALTH"
	               money,     // "MONEY"
	               pronoun,   // "PRONOUNS"
	               preposition,// "PREPOSITION"
	               common,     // "COMMON PHRASES"
	               adjective,  // "ADJECTIVES"
	               date,      // "DATE and NUMBERS"
	               close,      // "CROSS" button on the right top angle of main window to close application
	               minimize ;  // "MINIMIZE" button on the right top angle of main window to close application
	               	 
	 
	@FXML 
	private Label Word,        // left panel
	              Translation;  //right panel 
	
	@FXML 
	private Text Explanation; //Explanation text for starting of work;
	 
	
	 
	private Map <String,byte[]> instance_map;
	private boolean translate;
	private int counter;
	private Random randomize;
	private double X_Initlayout,
	               Y_Initlayout,
	               xOffset,
	               yOffset;
	IS_Sound_Files inputstream;

	private String currentPane;
	private ArrayList<Pane> panes; 
	public MainBtn_Contr () {
		 counter=0;
		 translate=false;	 
 		 instance_map = new HashMap<String,byte[]>();
		 randomize=new Random();
		 currentPane="";
		 X_Initlayout=0;
		 Y_Initlayout=0;
		 xOffset = 0;
		 yOffset = 0;
		 panes= new ArrayList<Pane>();	 
		 inputstream=new IS_Sound_Files();
	}
	
	   @FXML
	   private void onMousePressed(MouseEvent event) {
	    	
	        xOffset = event.getSceneX();
	        yOffset = event.getSceneY();
	    }
	 
	   @FXML
	     
	    private void onMouseDragged(MouseEvent event) {
		   Main_Pane.getScene().getWindow().setX(event.getScreenX() - xOffset);
		   Main_Pane.getScene().getWindow().setY(event.getScreenY() - yOffset);
	    }
	
	/*
	 * This method handles events of main button actions("FINISH", "DICTIONARIES", "START")
	 * ActionEvent "e" - event on pressing button
	 */
	
	  public void actionPerformed(ActionEvent e) {
		  if (Main_Button.getText().equalsIgnoreCase("FINISH")) {
			  Platform.exit();
		  }
		  
		 if ( Main_Button.getText().equalsIgnoreCase("DICTIONARIES")) {
		   Main_Button.setVisible(false);  
		   Explanation.setVisible(false);
		   fillDictPanes();
		   inputstream.setAllPaneVisible(true,panes);	
		  }
		 
		   if (Main_Button.getText().equalsIgnoreCase("START")) {
			 inputstream.setAllPaneVisible(false,panes);
	    	 Main_Button.setText("FINISH"); 
	    	 Repeat.setVisible(true);
	    	 Next.setVisible(true);
	    	 Return.setVisible(true);
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
	     } 
	  }
	  
	    
	  /*
	   * This method handles of VERBS button pressing event		
	  */ 
	  public void actionDict_1(ActionEvent e) {
		  
		 //  files.Dict1_Downloads("C:\\Users\\Public\\Just_Repeat\\dictionaries\\verbs");
		 //     files.dict_Downloads(getClass().getResource("src/main/resources/dictionaries/verbs").toString());
		  inputstream.dict_Downloads_Stream ("/main/resources/dictionaries/verbs/"); 
		   verbs.setOpacity(0);
		   inputstream.setOnePaneVisible(Verbs_Btn_Pane,panes);
		   takePaneInitPosition(Verbs_Btn_Pane); 
		   setPaneInCentral(Verbs_Btn_Pane);
		   Main_Button.setText("START");
		   inputstream.fadeChange(Main_Button,verbs, Verbs_Btn_Pane);
		    Main_Button.setVisible(true); 
		    		   		  
	}
	  
	  /*
	   * This method handles of VERBS_1 button pressing event		
	  */ 
	  
	  public void actionVerbs_1(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/verbs_1/");
		  verbs_1.setOpacity(0); 
		  inputstream.setOnePaneVisible(Verbs_Btn_Pane1,panes);
		  takePaneInitPosition(Verbs_Btn_Pane1); 
		  setPaneInCentral(Verbs_Btn_Pane1);
		   Main_Button.setText("START");
		   inputstream.fadeChange(Main_Button,verbs_1, Verbs_Btn_Pane1);
		    Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of TRAVEL button pressing event		
	  */ 
	  
	  public void actionTravel(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/travel/");
		  travel.setOpacity(0);
		  inputstream.setOnePaneVisible(Travel_Btn_Pane, panes);
		  takePaneInitPosition(Travel_Btn_Pane); 
		  setPaneInCentral(Travel_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, travel, Travel_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of HOUSE button pressing event		
	  */ 
	  
	  public void actionHouse(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/house/");
		  house.setOpacity(0);
		  inputstream.setOnePaneVisible(House_Btn_Pane, panes);
		  takePaneInitPosition(House_Btn_Pane); 
		  setPaneInCentral(House_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, house, House_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of CLOTHES button pressing event		
	  */ 
	  
	  public void actionClothes(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/clothes/");
		  clothes.setOpacity(0);
		  inputstream.setOnePaneVisible(Clothes_Btn_Pane, panes);
		  takePaneInitPosition(Clothes_Btn_Pane); 
		  setPaneInCentral(Clothes_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, clothes, Clothes_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of FOOD button pressing event		
	  */ 
	  
	  public void actionFood(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/food/");
		  food.setOpacity(0);
		  inputstream.setOnePaneVisible(Food_Btn_Pane, panes);
		  takePaneInitPosition(Food_Btn_Pane); 
		  setPaneInCentral(Food_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, food, Food_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of NUTURE button pressing event		
	  */ 
	  
	  public void actionNature(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/nature/");
		  nature.setOpacity(0);
		  inputstream.setOnePaneVisible(Nature_Btn_Pane, panes);
		  takePaneInitPosition(Nature_Btn_Pane); 
		  setPaneInCentral(Nature_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, nature, Nature_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of TRANSPORT button pressing event		
	  */ 
	  
	  public void actionTransport(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/transport/");
		  transport.setOpacity(0);
		  inputstream.setOnePaneVisible(Transport_Btn_Pane, panes);
		  takePaneInitPosition(Transport_Btn_Pane); 
		  setPaneInCentral(Transport_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, transport, Transport_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of BODY button pressing event		
	  */ 
	  
	  public void actionBody(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/body/");
		  body.setOpacity(0);
		  inputstream.setOnePaneVisible(Body_Btn_Pane, panes);
		  takePaneInitPosition(Body_Btn_Pane); 
		  setPaneInCentral(Body_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, body, Body_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of IMPORTANT button pressing event		
	  */ 
	  
	  public void actionImportant(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/important/");
		  important.setOpacity(0);
		  inputstream.setOnePaneVisible(Imp_Btn_Pane, panes);
		  takePaneInitPosition(Imp_Btn_Pane); 
		  setPaneInCentral(Imp_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, important, Imp_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of EDUCATION button pressing event		
	  */ 
	  
	  public void actionEducation(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/education/");
		  education.setOpacity(0);
		  inputstream.setOnePaneVisible(Education_Btn_Pane, panes);
		  takePaneInitPosition(Education_Btn_Pane); 
		  setPaneInCentral(Education_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, education, Education_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	   
	  	  
	  /*
	   * This method handles of JOB button pressing event		
	  */ 
	  
	  public void actionJob(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/job/");
		  job.setOpacity(0);
		  inputstream.setOnePaneVisible(Job_Btn_Pane, panes);
		  takePaneInitPosition(Job_Btn_Pane); 
		  setPaneInCentral(Job_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, job, Job_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of FAMILY button pressing event		
	  */ 
	  
	  public void actionFamily(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/family/");
		  family.setOpacity(0);
		  inputstream.setOnePaneVisible(Family_Btn_Pane, panes);
		  takePaneInitPosition(Family_Btn_Pane); 
		  setPaneInCentral(Family_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, family, Family_Btn_Pane) ;
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  
	  /*
	   * This method handles of SPORT button pressing event		
	  */ 
	  
	  public void actionSport(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/sport/");
		  sport.setOpacity(0);
		  inputstream.setOnePaneVisible(Sport_Btn_Pane, panes);
		  takePaneInitPosition(Sport_Btn_Pane); 
		  setPaneInCentral(Sport_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, sport, Sport_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of HEALTH button pressing event		
	  */ 
	  
	  public void actionHealth(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/health/");
		  health.setOpacity(0);
		  inputstream.setOnePaneVisible(Health_Btn_Pane, panes);
		  takePaneInitPosition(Health_Btn_Pane); 
		  setPaneInCentral(Health_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, health, Health_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  
	  /*
	   * This method handles of DATE and NUMBERS button pressing event		
	  */ 
	  
	  public void actionDate(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/numbers/");
		  date.setOpacity(0);
		  inputstream.setOnePaneVisible(Date_Btn_Pane, panes);
		  takePaneInitPosition(Date_Btn_Pane); 
		  setPaneInCentral(Date_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, date, Date_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  
	  /*
	   * This method handles of MONEY button pressing event		
	  */ 
	  
	  public void actionMoney(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/money/");
		  money.setOpacity(0);
		  inputstream.setOnePaneVisible(Money_Btn_Pane, panes);
		  takePaneInitPosition(Money_Btn_Pane); 
		  setPaneInCentral(Money_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, money, Money_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of PRONOUNS button pressing event		
	  */ 
	  
	  public void actionPronoun(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/pronoun/");
		  pronoun.setOpacity(0);
		  inputstream.setOnePaneVisible(Pronoun_Btn_Pane, panes);
		  takePaneInitPosition(Pronoun_Btn_Pane); 
		  setPaneInCentral(Pronoun_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, pronoun, Pronoun_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * This method handles of PREPOSITION button pressing event		
	  */ 
	  
	  public void actionPreposition(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/preposition/");
		  preposition.setOpacity(0);
		  inputstream.setOnePaneVisible(Preposition_Btn_Pane, panes);
		  takePaneInitPosition(Preposition_Btn_Pane); 
		  setPaneInCentral(Preposition_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, preposition, Preposition_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  
	  /*
	   * This method handles of COMMON PHRASES button pressing event		
	  */ 
	  
	  public void actionCommon(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/common/");
		  common.setOpacity(0);
		  inputstream.setOnePaneVisible(Common_Btn_Pane, panes);
		  takePaneInitPosition(Common_Btn_Pane); 
		  setPaneInCentral(Common_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, common, Common_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  
	  /*
	   * This method handles of ADJECTIVES button pressing event		
	  */ 
	  
	  public void actionAdjective(ActionEvent e) {
		  
		  inputstream.dict_Downloads_Stream("/main/resources/dictionaries/adjective/");
		  adjective.setOpacity(0);
		  inputstream.setOnePaneVisible(Adjective_Btn_Pane, panes);
		  takePaneInitPosition(Adjective_Btn_Pane); 
		  setPaneInCentral(Adjective_Btn_Pane);
		  Main_Button.setText("START");
		  inputstream.fadeChange(Main_Button, adjective, Adjective_Btn_Pane);
		  Main_Button.setVisible(true); 
		  
	  }
	  
	  /*
	   * handling  NEXT/TRANSLATION button (located in the right bottom angle)		
	  */
	  
	  public void actionNext(ActionEvent e) {  
		  
		  
	  	  
		  if (Next.getText().equalsIgnoreCase("TRANSLATE")
				  && inputstream.finish_table.containsKey(inputstream.last_key)) {
			  instance_map.clear();
			  instance_map.putAll(inputstream.finish_table.get(inputstream.last_key));
			  for(String s:instance_map.keySet()) {
				    Repeat_2.setDisable(false);
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
			  Return.setVisible(false);
			  Main_Button.setVisible(false);
			  setPaneInitPosition(currentPane);
			  inputstream.setAllPaneVisible(true,panes);
			  
		  } else  {
			 
		   counter=0;	
		   int rand_1 = randomize.nextInt(inputstream.finish_table.size()) ;
           for (Map<String, byte[]> SF:inputstream.finish_table.keySet()) {
        	   
        	   if (rand_1==counter) { 
	    		  
	    		  for (String s: SF.keySet()) {
	    			    if (translate==false) {
	    			       Repeat_2.setDisable(true);
	    			//     Repeat_2.setVisible(false);
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
		  
		 
	  }
	  
	  
	  /*
	   * handling   DICTIONARIES /Return button (located in the right bottom angle)		
	  */
	  
	  public void actionReturn(ActionEvent e) {
		  
		  setPaneInitPosition(currentPane);
		  inputstream.setAllPaneVisible(true,panes);
	 	  Main_Button.setVisible(false); 
	    	 Repeat.setVisible(false);
	    	 Repeat_2.setVisible(false);
	    	 Next.setVisible(false);
	    	 Return.setVisible(false);
	    	  Translation.setVisible(false);
			  Word.setVisible(false);
			  counter=0;
				 translate=false;	
		    
		  
	  }
	  
	  /*
	   * handling  Repeat icon button (located in the right bottom angle)		
	  */
	  
	  public void actionRepeat(ActionEvent e) {
		  
		  for (String s: inputstream.last_key.keySet()) {
			  inputstream.playAudioFile(inputstream.last_key.get(s)); 
		  }
		 
	  }
	  
	  /*
	   * handling  Repeat icon button (located in the left bottom angle)		
	  */
	  
	  public void actionRepeat_1(ActionEvent e) {
		  
		  for (String s: inputstream.last_value.keySet()) {
			  inputstream.playAudioFile(inputstream.last_value.get(s));  
		  }
  		  
		  
  }
	  
	  /*
	   * action at "CROSS" button on the right top angle of main window to close application		
	  */
	  
	  public void actionCross (ActionEvent e) {
		  
		  Platform.exit();
		  
		  
	  }
	  
	  
	  /*
	   * action at "MINIMIZE" button on the right top angle of main window to close application		
	  */
	  
	  public void actionMinimize (ActionEvent e) {
		  
		  Stage s=(Stage)((Button)e.getSource()).getScene().getWindow();
		  s.setIconified(true);
		  
		  
	  }
	 
	  /*
	   *  This method will be called when need to remember all panels initial position coordinates (buttons VERBs, VERBs_1, TRAVEL and so on)		
	  */ 
	  
	  private void takePaneInitPosition(Pane pane) {
		  if(X_Initlayout==0 && Y_Initlayout==0) {
		   currentPane=pane.getId();
		   X_Initlayout=pane.getLayoutX();
		   Y_Initlayout=pane.getLayoutY();  
		  }
	  }
	  
	  /*
	   *  This method will be called when need to return all panels (buttons VERBs, VERBs_1, TRAVEL and so on) in initial position		
	  */
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
	
	  /*
	   *  This method  fills Arraylist panes, that will be used next in the program		
	  */
	  
	  private void fillDictPanes() {
		  panes.add(Verbs_Btn_Pane);
		  panes.add(Verbs_Btn_Pane1);
		  panes.add(Travel_Btn_Pane);
		  panes.add(House_Btn_Pane);
		  panes.add(Clothes_Btn_Pane);
		  panes.add(Food_Btn_Pane);
		  panes.add(Nature_Btn_Pane);
		  panes.add(Transport_Btn_Pane);
		  panes.add(Body_Btn_Pane);
		  panes.add(Imp_Btn_Pane);
		  panes.add(Education_Btn_Pane);
		  panes.add(Job_Btn_Pane);
		  panes.add(Sport_Btn_Pane);
		  panes.add(Family_Btn_Pane);
		  panes.add(Health_Btn_Pane); 
		  panes.add(Date_Btn_Pane);
		  panes.add(Money_Btn_Pane);
		  panes.add(Pronoun_Btn_Pane);
		  panes.add(Preposition_Btn_Pane);
		  panes.add(Common_Btn_Pane);
		  panes.add(Adjective_Btn_Pane);
		  
	  }
	  
	  /*
	   *  This method  set pane in central position of main pane
	   *  	
	  */
	  
	  private void setPaneInCentral(Pane pane) {
		  
		  for (Pane p: panes) {
			  if ((p.getId().equalsIgnoreCase(pane.getId()))){
				  p.setLayoutX(Main_Pane.getPrefWidth()/2-p.getPrefWidth()/2);
				  p.setLayoutY(Main_Pane.getPrefHeight()/2-p.getPrefHeight()/2);
				  
			  }
		  
		  
	  }
	  
	  }
}
