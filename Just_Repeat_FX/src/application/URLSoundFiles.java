package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

/**
 * The methods of the "URLSoundFiles" class is the same like "IS_Sound_Files" class. It works fine for filling 
 * "finish_table" if I start the program from
 * Eclipse IDE, but ONCE!! stop working when I start it from a .JAR file. Reason is in way of access to resource file
 * on computer and in .JAR file
 * 
 * @author Valerii Demidov
 *
 */

public class URLSoundFiles extends ManageMainWindowEffect{
	
	
	 
	 
		 
		public HashMap<Map<String,URL>, Map<String,URL>> finish_table;
		public Map<String,URL> last_key;
		public Map<String,URL> last_value;
		
		public URLSoundFiles() {
			finish_table = new HashMap<Map<String,URL>, Map<String,URL>>();
			last_key=new HashMap<> ();
			last_value=new HashMap<> ();
		}
		
		public void dict_Downloads_Stream (String directoryPath) {
				
			Map<String, URL> WavFiles= loadURLToMap(directoryPath);
			Map<String, URL> WavFiles_copy= WavFiles;
			 
			 for (String s: WavFiles.keySet()) {
				  
				  for(String s1:WavFiles_copy.keySet()) {
					  if (s1.contains(s) && (s1.indexOf(s)!=0)) {
			    HashMap <String,URL> Finish_map=new  HashMap <String,URL>();
			    HashMap <String,URL> Finish_map_1=new  HashMap <String,URL>(); 
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
		 
		 
 
    public void playSoundFromURL(URL url) {
    	AudioInputStream audioInputStream;
        try {
        	 
            audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            // Handle exceptions if necessary
            e.printStackTrace();
        }
    }
 
	 	 
	 	 
		 public Map<String, URL> loadURLToMap(String directoryPath) {
			    Map<String, URL> URLMap = new HashMap<>();
			 
			     URL url = this.getClass().getResource(directoryPath+"tt.txt");
			 
			    try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),Charset.forName("CP1251") ))) {
			   
			    	String URLline;
			    	
			    	while ((URLline = reader.readLine()) != null) {
			    		if (URLline=="") {
			    			break;
			    		}
			    		System.out.println(URLline);
			    		url=this.getClass().getResource(directoryPath+URLline);
			    	 	System.out.println("URL: " + url.toString()); 
			    		URLMap.put(URLline, url);
			    	}
			    } catch (IOException e) {
			        // Handle the IOException if necessary
			    }
			    
			    return URLMap;
			}
	  
		 
	 	public void saveLastKey(Map<String,URL> key) {
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


 
