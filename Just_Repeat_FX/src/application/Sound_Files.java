/**
 * 
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
 
/**
 * @author Admin
 *
 */
public class Sound_Files {
	 
	public HashMap<Map<String,File>, Map<String,File>> finish_table;
	public Map<String,File> last_key;
	public Map<String,File> last_value;
	
	public Sound_Files() {
		finish_table = new HashMap<Map<String,File>, Map<String,File>>();
		last_key=new HashMap<> ();
		last_value=new HashMap<> ();
	}
	
	public void dict_Downloads (String directoryPath) {
			
		Map<String, File> WavFiles= loadFilesToMap(directoryPath);
		Map<String, File> WavFiles_copy= WavFiles;
		 
		 for (String s: WavFiles.keySet()) {
			  
			  for(String s1:WavFiles_copy.keySet()) {
				  if (s1.contains(s) && (s1.indexOf(s)!=0)) {
		    HashMap <String,File> Finish_map=new  HashMap <String,File>();
		    HashMap <String,File> Finish_map_1=new  HashMap <String,File>(); 
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
	 
	/* The function downloads .wav files from necessary directory to an ArrayList */
	 
	public   List<File> loadFilesToList(String directoryPath) {
        List<File> fileList = new ArrayList<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".wav")) {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }
     
	
	 public  void playAudioFile(File wav) {
	        try {
	            
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(wav);
	            AudioFormat format = audioStream.getFormat();
	            DataLine.Info info = new DataLine.Info(Clip.class, format);
	            Clip audioClip = (Clip) AudioSystem.getLine(info);
	            audioClip.open(audioStream);
	            audioClip.start();
	            
	            
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
 
 	public Map<String, File> loadFilesToMap(String directoryPath) {
        Map<String, File> fileMap = new HashMap<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".wav")) {
                    fileMap.put(file.getName(), file);
                }
            }
        }

        return fileMap;
    }
  	/*
 	public Map<String, File> loadFilesToMap(String directoryPath) {
 	    Map<String, File> fileMap = new HashMap<>();
 	    
 	    try {
 	        // Get the class loader for the current class
 	    	 ClassLoader classLoader = getClass().getClassLoader();
 	        
 	        // Use the class loader to get the resources from the JAR file
 	         File directory = new File(classLoader.getResource(directoryPath).toURI());
 	    	  
 	        if (directory.exists() && directory.isDirectory()) {
 	            File[] files = directory.listFiles();
 	            if (files != null) {
 	                for (File file : files) {
 	                    if (file.isFile() && file.getName().toLowerCase().endsWith(".wav")) {
 	                        fileMap.put(file.getName(), file);
 	                    }
 	                }
 	            }
 	        }
 	    } catch (URISyntaxException e) {
 	    // Handle the exception
 	        String errorMessage = "An error occurred while accessing the resources: " + e.getMessage();
 	        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
 	        // You can also log the error to the console or a log file
 	        e.printStackTrace();
 	    }

 	    return fileMap;
 	}

	  
	 public Map<String, File> loadFilesToMap(String directoryPath) {
		    Map<String, File> fileMap = new HashMap<>();
		    
		    // Get the class loader for the current class
		    ClassLoader classLoader = getClass().getClassLoader();
		    
		    // Use the class loader to get the resources as a stream
		    try (InputStream inputStream = classLoader.getResourceAsStream(directoryPath)) {
		        if (inputStream != null) {
		            // Create a temporary directory to extract the files from the stream
		            File tempDir = Files.createTempDirectory("temp").toFile();
		            
		            // Copy the files from the stream to the temporary directory
		            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
		                String line;
		                while ((line = reader.readLine()) != null) {
		                    File file = new File(line);
		                    if (file.isFile() && file.getName().toLowerCase().endsWith(".wav")) {
		                        fileMap.put(file.getName(), file);
		                    }
		                }
		            } catch (IOException e) {
		                // Handle the IOException if necessary
		            }
		        }
		    } catch (IOException e) {
		        // Handle the IOException if necessary
		    }

		    return fileMap;
		}
  
	 public Map<String, InputStream> loadFilesToMap(String directoryPath) {
	        Map<String, InputStream> fileMap = new HashMap<>();

	        // Use the class loader to get the URL of the resource directory
	        java.net.URL resourceUrl = getClass().getResource(directoryPath);

	        if (resourceUrl != null) {
	            try {
	                // Open an InputStream for the resource directory
	                InputStream directoryStream = resourceUrl.openStream();

	                // Create a BufferedReader to read the filenames from the directory
	                try (BufferedReader reader = new BufferedReader(new InputStreamReader(directoryStream))) {
	                    String filename;
	                    while ((filename = reader.readLine()) != null) {
	                        // Use getResourceAsStream() to load the individual .wav files
	                        InputStream inputStream = getClass().getResourceAsStream(directoryPath + filename);
	                        if (inputStream != null) {
	                            fileMap.put(filename, inputStream);
	                        }
	                    }
	                }
	            } catch (IOException e) {
	                // Handle the IOException if necessary
	            }
	        } else {
	            // Handle the case when the resource URL is null
	        }

	        return fileMap;
	    }
	 */
 	public void saveLastKey(Map<String,File> key) {
 		if (!last_key.isEmpty()) {
 			last_key.clear();
 		}
 		if (!last_value.isEmpty()) {
 			 last_value.clear();
 		}
 		last_key.putAll(key);
 		last_value.putAll(finish_table.get(key));
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
 	 
 	 public void setOnePaneVisible (Pane pane, ArrayList<Pane> panes ) {
		 
		  for (Pane p: panes) {
			  if (!(pane.getId().equalsIgnoreCase(p.getId()))){
				  p.setVisible(false);
			  }
			  
		  }	  
	  }
 	 	
 	 
}
