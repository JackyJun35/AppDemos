
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.HashMap;
import java.util.Arrays;
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int howManyTimes;
    
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
	
   public HashMap<String,String> buildMap(String key){
       HashMap<String,String> map = new HashMap();
       int pos = 0;
       if(!map.containsKey(key)){
	     while(myText.indexOf(key,pos)!=-1 ){
	      pos = myText.indexOf(key,pos)+ key.length()-1;
	      if(pos+1>=myText.length())break;
	      map.put(key,String.valueOf(myText.charAt(pos+1)));
	      pos++;
	      }
	      return map;
	   }
    }
    
    public  ArrayList<String> getFollows(String key){
       String[] values = buildMap(key).values().toArray();
       ArrayList<String> follows = Array.asList(values);
    }
}
