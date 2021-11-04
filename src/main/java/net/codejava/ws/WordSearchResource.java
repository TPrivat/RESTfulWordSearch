package net.codejava.ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/wordsearch/")
public class WordSearchResource {
	//specify where to find index file
	// MAY NEED TO CHANGE THIS DEPENDING ON PROJECT STRUCTURE
	public static String indexFileLocation = "../../../../../eclipse-workspace/RestfulWordSearch/index";
    public static String indexFileName = "Index.txt";
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Hello() {
    	File dir = new File(indexFileLocation);
    	try {
    		File[] files = dir.listFiles();
    		// For-each loop for iteration
            for (File file : files) {
 
                // Checking of file inside directory
                if (file.isDirectory()) {
 
                    // Display directories inside directory
                    System.out.println(
                        "directory:"
                        + file.getCanonicalPath());
                }
 
              // Simply get the path
                else {
                    System.out.println(
                        "     file:"
                        + file.getCanonicalPath());
                }
            }
        }
 
        // if any exceptions occurs printStackTrace
        catch (IOException e) {
            e.printStackTrace();
        }
    	return "This is a test";
    }
	
	@Path("{term}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String searchFiles(@PathParam("term") String term) {
		String search_term = term;
		File file = new File(indexFileLocation + File.separator + indexFileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String s;
			while ((s = br.readLine()) != null) {
				String [] splitWords = s.split("\\|.\\|");
				if(splitWords[0].equals(search_term)) {
					System.out.println("Match Find on Index File!");
					System.out.println("Sending result to client side:");
					System.out.println(s);
					return s;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}

