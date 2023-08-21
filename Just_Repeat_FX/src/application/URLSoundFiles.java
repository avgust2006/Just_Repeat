package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class URLSoundFiles {
	
	
	 
	 
		 
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
		 
		/* The function downloads .wav files from necessary directory to an ArrayList */
		 
		public   List<InputStream> loadFilesToList(String directoryPath) {
	        List<InputStream> fileList = new ArrayList<>();
	        
	        try {
                // Open an InputStream for the resource directory
                InputStream directoryStream = getClass().getResourceAsStream(directoryPath);

                // Create a BufferedReader to read the filenames from the directory
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(directoryStream))) {
                    String filename;
                    while ((filename = reader.readLine()) != null) {
                        // Use getResourceAsStream() to load the individual .wav files
                        InputStream inputStream = getClass().getResourceAsStream(directoryPath + filename);
                        if (inputStream != null) {
                        	fileList.add(inputStream);
                        }
                    }
                }
            } catch (IOException e) {
                // Handle the IOException if necessary
            }
  

	        return fileList;
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
 

	     
		
		 public  void playAudioFile(InputStream is) {
		        try {
		        	BufferedInputStream bufferedStream = new BufferedInputStream(is,is.available()); 
		            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
		            AudioFormat format = audioStream.getFormat();
		            DataLine.Info info = new DataLine.Info(Clip.class, format);
		            Clip audioClip = (Clip) AudioSystem.getLine(info);
		            audioClip.open(audioStream);
		            audioClip.start();
		            
		            
		        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		            e.printStackTrace();
		        }
		        
		        
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

		*/  
		 public Map<String, URL> loadURLToMap(String directoryPath) {
			    Map<String, URL> URLMap = new HashMap<>();
			//    ClassLoader classLoader = getClass().getClassLoader();
			     URL url = this.getClass().getResource(directoryPath+"tt.txt");
			//    playSoundFromURL(url);
			// InputStream is = classLoader.getResourceAsStream("main/resources/dictionaries/travel/");
			     
			  //   System.out.println(is.toString());
			    try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),Charset.forName("CP1251") ))) {
			  //   	try (BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("CP1251")))) {
			    	String URLline;
			    	
			    	while ((URLline = reader.readLine()) != null) {
			    		System.out.println(URLline);
			    		url=this.getClass().getResource(directoryPath+URLline);
			    	 	System.out.println("URL: " + url.toString()); 
			    		URLMap.put(URLline, url);
			    	}
			    } catch (IOException e) {
			        // Handle the IOException if necessary
			    }
			    /*
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
*/
			    return URLMap;
			}
	  
		 
		 
		 public Map<String, InputStream> loadImputStreamToMap(String directoryPath) {
		        Map<String, InputStream> InputStreamMap = new HashMap<>();		         
		     //    ClassLoader classLoader = getClass().getClassLoader();
		     //    URL url = this.getClass().getResource(directoryPath+"travel.txt");// + "flight.wav"
		         InputStream is = this.getClass().getResourceAsStream(directoryPath+"tt.txt");
		         
		         try (BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("CP1251") ))) {
					    //	try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
					    	String URLline;
					    	
					    	while ((URLline = reader.readLine()) != null) {
					    		System.out.println(URLline);
					    		is=this.getClass().getResourceAsStream(directoryPath+URLline);
					    	 	System.out.println("URL: " + is.toString()); 
					    	 	InputStreamMap.put(URLline, is);
					    	}
					    } catch (IOException e) {
					        // Handle the IOException if necessary
					    }
		         
		 /*           try {
		            	
		            	 //    URL url=classLoader.getResource(directoryPath);
		            	//    if (url != null) {
		            	//         System.out.println("URL: " + url.toString());}
		            	//    else {System.out.println(directoryPath);}
		            	    if (is != null) {
		            	         System.out.println("InputStream: " + is.toString());}
		            	    else {System.out.println(directoryPath);}
 		            	  //  File file = new File (classLoader.getResource(directoryPath).;
		            	    BufferedInputStream bufferedStream = new BufferedInputStream(is);
		            	//   playAudioFile(bufferedStream);
		              	//     playSoundFromInputStream(url);
 		            	//     playSoundFromInputStream(is);
		           // 	    AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
		            	  // String name =url.getContent().;
		            	//   playAudioFile(url);
		            	 //   playSoundFromInputStream(url);
		                // Open an InputStream for the resource directory
		            	// FileInputStream inputS=(FileInputStream) classLoader.getResourceAsStream(directoryPath);
		             //	InputStream directoryStream = classLoader.getResourceAsStream(directoryPath);
		            // 	if (directoryStream != null) {
		  		    //       	        System.out.println("InputStream: " + directoryStream.toString());}  
		             	FileInputStream inputS = new FileInputStream ("src/main/resources/dictionaries/travel/flight.wav");
		               if (inputS != null) {
            	        System.out.println("inputS: " + inputS.toString());}
		               // System.out.println(directoryStream);
		                // Create a BufferedReader to read the filenames from the directory
		              ////////////  
		             //    playSoundFromInputStream(inputS);
		              //   playAudioFile(inputS);
		           //    BufferedInputStream bufferedStrea = new BufferedInputStream(directoryStream);
		              // playAudioFile(bufferedStrea);
		                 // Wrap the FileInputStream with a BufferedInputStream
				//		 BufferedInputStream bufferedStream = new BufferedInputStream(inputS);
						// playAudioFile(bufferedStream);
						 // Get the AudioInputStream from the buffered stream

		                try (BufferedReader reader = new BufferedReader(new InputStreamReader(directoryStream))) {
		                    String filename;
		                    
		                    while ((filename = reader.readLine()) != null) {
		                    	 
		                        // Use getResourceAsStream() to load the individual .wav files
		                       //    FileInputStream is=(FileInputStream) classLoader.getResourceAsStream(directoryPath);
		                        InputStream inputStream = classLoader.getResourceAsStream(directoryPath );
		                        if (inputStream != null) {
		                            fileMap.put(filename, directoryStream);
		                        }
		                    }
		                    
		                }
		             /////////   
		            } catch (IOException e) {
		                // Handle the IOException if necessary
		            }
		         
*/
		        return InputStreamMap;
		    }
		/*
		 public Map<String, InputStream> loadFilesToMap(String directoryPath) {
		        Map<String, InputStream> fileMap = new HashMap<>();
		        
		        ClassLoader classLoader = getClass().getClassLoader();
		       InputStream is = classLoader.getResourceAsStream(directoryPath);
		//        FileInputStream inputS=(FileInputStream) classLoader.getResourceAsStream(directoryPath);
		        // Use the class loader to get the URL of the resource directory
		        java.net.URL resourceUrl = classLoader.getResource(directoryPath);

		        if (resourceUrl != null) {
		            // Get the File object for the resource directory
		            File directory;
		            try {
		                directory = new File(resourceUrl.toURI());
		            } catch (URISyntaxException e) {
		                // Handle the URISyntaxException if necessary
		                return fileMap;
		            }

		            // Use a FilenameFilter to get only .wav files
		            File[] wavFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith

		(".wav"));

		            if (wavFiles != null) {
		                for (File wavFile : wavFiles) {
		                    // Use getResourceAsStream() to load the individual .wav files
		             //   	String fff=directoryPath+"/"+wavFile.getName();
		                    try (FileInputStream inputStream=new  FileInputStream(wavFile)) {
		                    	//	=classLoader.getResourceAsStream("/" + wavFile.getName())){

		//wavFile.getName())) {
		                        if (inputStream != null) {
		                             fileMap.put(wavFile.getName(), inputStream);
		                        }
		                    } catch (IOException e) {
		                        // Handle the IOException if necessary
		                    }
		                }
		            }
		        } else {
		            // Handle the case when the resource URL is null
		        }

		        return fileMap;
		    }
		*/ 
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


 
