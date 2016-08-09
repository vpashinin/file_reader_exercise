package mytest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileData {   // obyavlyaem klacc 
		
	///?????
	 private final List<String> categories; 
	 private final List<String> makes;
	 private final List<String> models;
	 private final List<String> manufacturers;
	 
	 public FileData(List<String> categories, List<String> makes, List<String> models, List<String> manufacturers) {
		 this.categories = categories;
		 this.makes = makes;
		 this.models = models;
		 this.manufacturers = manufacturers;
	 }
     

	// piramida vsey structuri
	private void printSearchText(String line, Map<String, String> exampleMap, BufferedWriter bw) throws IOException{
		int position = line.indexOf("text");
		
 

        String piece = line.substring(position); 
         int positionOfBlank = piece.indexOf(" ");
        String searchText = piece.substring(0, positionOfBlank);

        for (Entry<String, String> mapping : exampleMap.entrySet()) {
       	 	String key = mapping.getKey();
       	 	String value = mapping.getValue();
       	 	searchText = searchText.replaceAll(key, value);    
        }
	      					      
		String searchQuery = searchText.substring(5);
		
		// zdes videlyaem nuzniy  text do nuznogo  znaca  v nashem primere etot znak  "&" i programma vidast text v ctroke do etogo znaka
		 int positionOfAnd = searchQuery.indexOf("&"); 
		 if(positionOfAnd>-1){
                   searchQuery = searchQuery.substring(0, positionOfAnd);
		 }
		 // comanda chtobi ne bilo probelov v text kotoriy v terminale
		    searchQuery = searchQuery.trim();
	   
		 if (!searchQuery.equals("")) {				 
			 String resultLine = computeResultLine(searchQuery);

			 bw.write(resultLine);
			 bw.newLine(); 
		 } 
	     
	}
  
	private String computeResultLine(String searchQuery) {
		 String resultLine = searchQuery;
		 resultLine = resultLine +";";
		 
		 /*
		  * Manufacturer ? file delaet otbor po proizvoditelyam
		  */
		 if (containsManufacturer(searchQuery, manufacturers)){
		         resultLine = resultLine + "1";
	
		 } else { 
		         resultLine = resultLine + "0";
		 }
		 resultLine = resultLine +";";
		   
		 /*
		  * EAN ? file delaet otbor po EAN (ediniy shtrih kod)
		  */
		 if (containsEAN(searchQuery)){
		         resultLine = resultLine + "1";
		 } else { 
		         resultLine = resultLine + "0";
		 }
		         
		        resultLine = resultLine +";";
		 
			        
		        
		 if (containsCategory(searchQuery,categories )){
		         resultLine = resultLine + "1";
		 } else { 
		         resultLine = resultLine + "0";
		 }
		    
		 resultLine = resultLine +";";
		   
        
	
	      if (containsModel(searchQuery,models )){
               resultLine = resultLine + "1";
            } else { 
                 resultLine = resultLine + "0";
            }
 
	             resultLine = resultLine +";";
	      return resultLine;
      }
 
	/////???????
	
//           if (containsBrend(searchQuery, brends )){  
//             resultLine = resultLine + "1";
//           } else { 
//              resultLine = resultLine + "0";
//           }
//              resultLine = resultLine +";";
//         }
	
           

         private static boolean containsManufacturer(String searchQuery, List<String> manufacturers) {		// otbor po proizvoditelyam
            String[] words = searchQuery.split(" ");
              for (String manufacturer : manufacturers) {
                 for (String word : words) {
                 	if( manufacturer.equals(word)){
		              return true;
	                  }
                   }

              }  
                         
               return false;
              }


	      private static boolean containsCategory(String searchQuery,List<String> categorys) {                // otbor vsego file po categorii
		     String[] words = searchQuery.split(" ");   
		       for (String category : categorys) {
			     for (String word : words) {
			    	if( category.equals(word)){
			  		  return true;
				       }
			        }
	
		     }  
		               
                return false;
	          }	  
	 
	       private static boolean containsBrend(String searchQuery,List<String> brends) {                // otbor vsego file po brendu
			  String[] words = searchQuery.split(" ");   
			     for (String brend : brends) {
				   for (String word : words) {
				    	if( brend.equals(word)){
						  return true;
					    }
				     }
		
			 }  
			               
	               
		
			    return false;
		       }	  
	
	  
	  
	         private static boolean containsModel(String searchQuery,List<String> models) {                // otbor vsego file po modeli
			    String[] words = searchQuery.split(" ");   
			      for (String model : models) {
				    for (String word : words) {
					  if( model.equals(word)){
					 	return true;
					    }
				      }
		  
		    	}  
			               
	               return false;
		         }	  
	  
	  
	  
	       private static boolean containsEAN(String searchQuery) {                      // otbor po EAN 
		      String[] words = searchQuery.split(" ");                                   //Split into words  - razdelit` informaciu i slova
		        for(String word:words){
			     int length = word.length();                                              //Is a word contains 13 characters?     
			       if(13==length && containsOnlyDigits(word)) {
				    return  true;
			   
			       }
			
		        }
		
		           return false;                                                         
		 	
	}

	     private static boolean containsOnlyDigits(String word) {
		    char[] characters = word.toCharArray();
		      for (char character : characters) {
			     if(!Character.isDigit(character)){
			  
				  return false;
				  }
              }
		            return true;
	}
	               
	
     public void readLogfile(String fileName, List<String> manufacturers, List<String> categories, List<String> brends, List<String> models, Map<String, String> replacements, BufferedWriter bw) throws IOException {
		ReadFile file = new ReadFile (fileName);         
		List<String> lines = file.OpenFile();               // chitaem file  i mozem cdelet otbor po interesuyshim nas slovam. GET;szukay;bingbot;googlebot.
	
	
		 String searchWord1 = "GET /szukaj";
		 String searchWord2 = "text"; 
		 String searchWord3 = "bingbot";
		 String searchWord4 = "googlebot";
		     
		 for (String line : lines){ 
			 if (line.contains(searchWord1) && line.contains(searchWord2) && !line.contains(searchWord3) && !line.contains(searchWord4)) {
					 printSearchText(line.toLowerCase(), replacements, bw); 
				
		     }
			 
    	 }
		 		 
	}
	   

	

  }



