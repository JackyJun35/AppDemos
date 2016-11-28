
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
      MarkovOne m1 = new MarkovOne();
      String trainingText = "this is a test yes this is a test.";
      m1.setTraining(trainingText);
      ArrayList<String> test  = m1.getFollows("t");
      System.out.println(trainingText);
      System.out.println(test);
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne m1 = new MarkovOne();
		m1.setTraining(st);
		
		ArrayList<String> test  = m1.getFollows("t");
        System.out.println(st);
        System.out.println(test);
        System.out.println(test.size());
    }
    
    
}
