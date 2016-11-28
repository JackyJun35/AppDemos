
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
  import java.util.Random;
  import java.util.*;
public class MarkovModel extends AbstractMarkovModel {
    
	private int howManyTimes;
	
	public MarkovModel(int howManyTimes) {
		myRandom = new Random();
		this.howManyTimes = howManyTimes;
	}
	
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-howManyTimes);
		String key = myText.substring(index,index+howManyTimes);
		sb.append(key);
		
		for(int k=0; k < numChars- howManyTimes; k++){
		    ArrayList<String> follows = getFollows(key);
			if(follows.size()==0){break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		
		return sb.toString();
	}
	
	
	  }
	
	





