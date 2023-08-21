package application;

 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class IS_Sound_Files {
	
	//public HashMap<Map<String,InputStream>, Map<String,InputStream>> finish_table;
	public HashMap<Map<String,byte[]>, Map<String,byte[]>> finish_table;
	public Map<String,byte[]> last_key;
	public Map<String,byte[]> last_value;
	
	public IS_Sound_Files() {
		finish_table = new HashMap<Map<String,byte[]>, Map<String,byte[]>>();
		last_key=new HashMap<> ();
		last_value=new HashMap<> ();
	}

	 // this function download "finish table" map with bytes of inputstream of sounds files
	
public void dict_Downloads_Stream (String directoryPath) {
		
		Map<String, byte[]> WavFiles= loadImputStreamByBytesToMap(directoryPath);
		Map<String, byte[]> WavFiles_copy= WavFiles;
		 
		 for (String s: WavFiles.keySet()) {
			  
			  for(String s1:WavFiles_copy.keySet()) {
				  if (s1.contains(s) && (s1.indexOf(s)!=0)) {
		    HashMap <String,byte[]> Finish_map=new  HashMap <String,byte[]>();
		    HashMap <String,byte[]> Finish_map_1=new  HashMap <String,byte[]>(); 
		    String name_2=s1.substring(0,s1.indexOf(s));
		    String name_1="";
		    if(s.startsWith("1")) {
		    	name_1= s.substring(1, s.indexOf(".wav"));
		    } else {
			  name_1=s.substring(0, s.indexOf(".wav"));
		    }
		    
		      Finish_map.put(name_1, WavFiles.get(s));
		 	 Finish_map_1.put(name_2, WavFiles_copy.get(s1));
		 	 finish_table.putIfAbsent(Finish_map, Finish_map_1);
		 	 finish_table.putIfAbsent(Finish_map_1, Finish_map );	
		 /*  
		   //if (finish_table.get(Finish_map).hashCode()==Finish_map_1.hashCode()) {}   
		    if(finish_table.isEmpty()) {
		    	 Finish_map.put(name_1, WavFiles.get(s));
				 Finish_map_1.put(name_2, WavFiles_copy.get(s1));
				 finish_table.putIfAbsent(Finish_map, Finish_map_1);
				 finish_table.putIfAbsent(Finish_map_1, Finish_map );	
		    }else {
		    	
		    	   for (Map<String, byte[]> SF:finish_table.keySet()) {
		            	 if (   (!SF.keySet().contains(name_2)&&!SF.keySet().contains(name_1))) {
		            		 Finish_map.put(name_1, WavFiles.get(s));
							 Finish_map_1.put(name_2, WavFiles_copy.get(s1));
							 finish_table.putIfAbsent(Finish_map, Finish_map_1);
							 finish_table.putIfAbsent(Finish_map_1, Finish_map );	
							 break;
		            	 } 	 		              				   
			           }		    	
		            }
		     */
				  }  	
			  }
		 }
	}

/////////////                                       ///////
	 public Map<String, byte[]> loadImputStreamByBytesToMap(String directoryPath) {
	        Map<String, byte[]> InputStreamMap = new HashMap<>();		         
	    
	         InputStream is = this.getClass().getResourceAsStream(directoryPath+"words.txt");
	         
	         try (BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("CP1251") ))) {
				   
				    	String URLline;
				    	
				    	while ((URLline = reader.readLine()) != null) {
				    		System.out.println(URLline);
				    		is=this.getClass().getResourceAsStream(directoryPath+URLline);
				    		 // Convert InputStream to byte array
					        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					        byte[] buffer = new byte[4096];
					        int bytesRead;
					        while ((bytesRead = is.read(buffer)) != -1) {
					            byteArrayOutputStream.write(buffer, 0, bytesRead);
					        }
					        byte[] audioData = byteArrayOutputStream.toByteArray();
	
				    	 	InputStreamMap.put(URLline, audioData);
				    	}
				    } catch (IOException e) {
				        // Handle the IOException if necessary
				    }
	         
	 
			  
	        return InputStreamMap;
	    }
	 
	 
	 public void playbyteAudioFile(byte[] audioData) {
		    try {
		       

		        // Create a new ByteArrayInputStream from the byte array
		        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);

		        // Get the AudioInputStream from the ByteArrayInputStream
		        AudioInputStream audioStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

		       
		        AudioFormat format = audioStream.getFormat();
		        DataLine.Info info = new DataLine.Info(Clip.class, format);
		        Clip audioClip = (Clip) AudioSystem.getLine(info);
		        audioClip.open(audioStream);
		        audioClip.start();
		    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
		        e.printStackTrace();
		    }
		}
 
	 	 
	 
	  
		public void saveLastKey(Map<String,byte[]> key) {
	 		if (!last_key.isEmpty()) {
	 			last_key.clear();
	 		}
	 		if (!last_value.isEmpty()) {
	 			 last_value.clear();
	 		}
	 		last_key.putAll(key);
	 		last_value.putAll(finish_table.get(key));
	 	}
		
		 public void setOnePaneVisible (Pane pane, ArrayList<Pane> panes ) {
			 
			  for (Pane p: panes) {
				  if (!(pane.getId().equalsIgnoreCase(p.getId()))){
					  p.setVisible(false);
				  }
				  
			  }	  
		  }
		 
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
