
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 *
 * @author Adam Brown
 */
public class WordExtractionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        
        //Create a set that orders stop words alphabetically
        Set<String> stopWord = new TreeSet<>();
        
        
        //read in the set
        Scanner scan = new Scanner(new File("src/stopWords.txt"));
        
        
        //while loop will add in words to the set.
        while(scan.hasNext())
        {
            String stop = clean(scan.next()); // see clean method below main 
            
            stopWord.add(stop);
        }
        
        
        //create a tree map that stores all words from the book
        Map<String, Integer> occurences = new TreeMap<>();
        
        
        //read the words
        Scanner in = new Scanner(new File("src/book.txt"));
        
        
        //this loop cleans the word and checks to see if it has counted. If not the if statement adds one and continues to add one every time it appears
        while(in.hasNext())
        {
            String word = clean(in.next());
            
            Integer count = occurences.get(word);
            
            if(count == null)
            {
                count = 1;
            }
            else
            {
                count = count + 1;
            }
            
            
            //if the words aren't in the stop word set add it to the hashmap with the word and its occurance
            if(!stopWord.contains(word))
            {
                
                occurences.put(word, count);
                
            }
            
        }

    
        // for loop that runs through the key aka the words and its occurance and prints out the word and its value of the word (the occurance)
        for(String key : occurences.keySet())
        {
            
            System.out.printf("%-20s%10s%n", key, occurences.get(key));
            
        }
        
    }    

    
    //clean method takes a blank string and as long as the parameter (which will be the file) goes it will only include
    //letters and apostrophes as part of the word then if each character is either a letter or apostrophe it adds it to the string. 
    //finally the method returns all values to lowercase to make sure they can be recongized comparatively between the stop words and book words
    private static String clean(String next) {
        
        String a = "";
        
        for(int i = 0; i < next.length(); i++)
        {
           
            char b = next.charAt(i);
            if(Character.isLetter(b) || b == '\'')
            {
                a = a + b;
            }   
        }
        
        return a.toLowerCase();
    }
    
}
