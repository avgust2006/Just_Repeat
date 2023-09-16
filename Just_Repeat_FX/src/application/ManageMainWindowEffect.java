package application;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


/*
 * The methods of the "ManageMainWindowEffect" class manages visual effects of Main Window of the program
 * 
 * */
public abstract class ManageMainWindowEffect {
	  
	/* The method hides all panels (buttons) of the program except pane in "Pane pane" parameter  */
	
	 public void setOnePaneVisible (Pane pane, ArrayList<Pane> panes ) {
		 
		  for (Pane p: panes) {
			  if (!(pane.getId().equalsIgnoreCase(p.getId()))){
				  p.setVisible(false);
			  }
			  
		  }	  
	  }
	 
	 /*The method set all panes visible or hidden depends from "boolean set parameter"*/
	 
	 public void setAllPaneVisible (boolean set, ArrayList<Pane> panes ) {
		  if (set) {
			  for (Pane p: panes) {
				  p.setVisible(true);
			  }			  
		  }else {
			  for (Pane p: panes) {
				  p.setVisible(false);
			  }			  			  
		  }
	  }
	 
	 
	 
	 
	/* The method delay appearing  at 1 second of pane and buttons that is pointed in the method parameters*/ 
	 
	 public void fadeChange (Button btn,Button btn1, Pane pane) {
	 	   FadeTransition fadeBtn = new FadeTransition(Duration.seconds(1), btn);
	 		     fadeBtn.setFromValue(0);
	 		     fadeBtn.setToValue(1);
	 		     fadeBtn.play();
	 	   FadeTransition fadeBtn1 = new FadeTransition(Duration.seconds(1), btn1);
	 	         fadeBtn1.setFromValue(0);
	 	         fadeBtn1.setToValue(1);
	 	         fadeBtn1.play();
		   FadeTransition fadePane = new FadeTransition(Duration.seconds(1), pane);
			    fadePane.setFromValue(0);
			    fadePane.setToValue(1);
			    fadePane.play();		
	 	}

}
