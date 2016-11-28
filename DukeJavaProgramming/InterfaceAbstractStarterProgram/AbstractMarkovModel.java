
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected void setRandom(int seed){  
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
	   ArrayList<String> answer = new ArrayList<String>();
	   int pos = 0;
	   while(myText.indexOf(key,pos)!=-1 ){
	      pos = myText.indexOf(key,pos)+ key.length()-1;
	      if(pos+1>=myText.length())break;
	      answer.add(String.valueOf(myText.charAt(pos+1)));
	      pos++;
	      }
	     return answer;
	  }
}
