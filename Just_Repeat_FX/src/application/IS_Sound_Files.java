package application;

 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * The  "IS_Sound_Files"  class allows users downloads sounds files from special directory to Map
 * (finish_table) in format <String,byte[]>, Map<String,byte[]>
 * 
 * @author Valerii Demidov
 * 
 * */

public class IS_Sound_Files extends ManageMainWindowEffect {
	
 /*
  * Declaration of global VARs.
  * "finish_table" - map that will contain audio files table in format: key -- name of audio file without .wav extension
  *                                                                          massive bytes [] of the audio  .wav file
  *                                                                   value -- name of audio file without .wav extension
  *                                                                            massive bytes [] of the audio  .wav file   
  *                                                                            
  *  "last_key" -  map that will contain sound files table in format:key --  name of audio file without .wav extension
  *                                                                  value --massive bytes [] of the audio  .wav file    
  *  This map subsequently will use to remember "key" parameter in  "finish_table" to display and audio .wav file
  *  after repeating of pressure of a button
  *  
  *   "last_value" -  map that will contain sound files table in format:key --  name of audio file without .wav extension
  *                                                                  value --massive bytes [] of the audio  .wav file    
  *  This map subsequently will use to remember "value" parameter in  "finish_table" to display and audio .wav file
  *  after repeating of pressure of a button                                                                                                                                   
  * */
	
	public HashMap<Map<String,byte[]>, Map<String,byte[]>> finish_table;
	public Map<String,byte[]> last_key;
	public Map<String,byte[]> last_value;
	
	/* The constructor of "IS_Sound_Files class" , definition of above VARs*/
	
	public IS_Sound_Files() {
		finish_table = new HashMap<Map<String,byte[]>, Map<String,byte[]>>();
		last_key=new HashMap<> ();
		last_value=new HashMap<> ();
	}
     /*
      * This methods directly downloads "finish table" global VARs
      * 
      * Parameters:
      *           "directoryPath" - is string that points where need to take . wav files
      * 
      *           Example: 
      *           "C:\Users\Admin\git\repository\Just_Repeat_FX\src\main\resources\dictionaries\travel" this 
      *           is the path where my resource is located. In this case "directoryPath" VAR should look like this:
      *          "/main/resources/dictionaries/travel/"
      *  Return: 
      *         void       
      * 
      * */
	 
	
public void dict_Downloads_Stream (String directoryPath) {
	
	    finish_table.clear();
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
		
				  }  	
			  }
		 }
	}

 /*  This method creates local Map with  <String, byte[]> parameters from path pointed in "directoryPath" string
   
    
      *  Parameters:
      *           "directoryPath" - is string that points where need to take . wav files
      * 
      *           Example: 
      *           "C:\Users\Admin\git\repository\Just_Repeat_FX\src\main\resources\dictionaries\travel" this 
      *           is the path where my resource is located. In this case "directoryPath" VAR should look like this:
      *          "/main/resources/dictionaries/travel/"
      *  Return: 
      *          Map<String, byte[]> 
  * */


 
	 public Map<String, byte[]> loadImputStreamByBytesToMap(String directoryPath) {
	        Map<String, byte[]> InputStreamMap = new HashMap<>();		         
	    //create  InputStream. "words.txt" is file, that is located in "directoryPath"
	        // and consist from names of .wav files written line by line
	         InputStream is = this.getClass().getResourceAsStream(directoryPath+"words.txt");
	    //create BufferedReader. Important: in constructor of  InputStreamReader we need use second parameter-
	         // Charset.forName("CP1251"), for correct displaying Russian chars
	         try (BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("CP1251") ))) {
				   
				    	String URLline;
				    	
				    	while ((URLline = reader.readLine()) != null) {
				    		
				    		if (URLline.equalsIgnoreCase("stopread")) {
				    			break;
				    		}

				    		is=this.getClass().getResourceAsStream(directoryPath+URLline);
				    		  
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
	 
	 
	 /*  This method sounds audio file. File is represented in massive bytes
	   
	    
      *  Parameters:
      *           "audioData" - massive of bytes  a . wav file
      *  
      *  Return: 
      *          void
  * */
	 
	 public void playAudioFile(byte[] audioData) {
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
 
	 /*  This method defines (downloads) global VARs "last_key" and "last_value"
	   
	    
      *  Parameters:
      *           "key" - Map<String,byte[]>
      *  
      *  Return: 
      *          void
  * */	 
	 
	  
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
	 
}         


	 
