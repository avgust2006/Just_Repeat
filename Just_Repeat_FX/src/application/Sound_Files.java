/**
 * 
 */
package application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
 
/**
 * @author Admin
 *
 */
public class Sound_Files {
	 
	public HashMap<Map<String,File>, Map<String,File>> finish_table;
	public Map<String,File> last_key;
	
	public Sound_Files() {
		finish_table = new HashMap<Map<String,File>, Map<String,File>>();
		last_key=new HashMap<> ();
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
	/*
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
    */
	
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
 	
 	public void saveLastKey(Map<String,File> key) {
 		if (!last_key.isEmpty()) {
 			last_key.clear();
 		}
 		last_key.putAll(key);
 	}
}
