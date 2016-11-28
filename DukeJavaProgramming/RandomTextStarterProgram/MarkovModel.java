
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
  import java.util.Random;
  import java.util.*;
public class MarkovModel {
    private String myText;
	private Random myRandom;
	
	
	public MarkovModel() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars,int howManyTimes){
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
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> answer = new ArrayList<String>();
	    int pos = 0;
	    while(myText.indexOf(key,pos)!=-1 ){
	      pos = myText.indexOf(key,pos)+key.length()-1;
	      if(pos+1>=myText.length()){break;}
	      answer.add(String.valueOf(myText.charAt(pos+1)));
	      pos++;
	      }
	    return answer;
	       }
	  }
	
	





