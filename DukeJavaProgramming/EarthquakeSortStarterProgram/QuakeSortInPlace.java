
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth (ArrayList<QuakeEntry> quakes, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
      
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry>in){
          boolean flag = true;
        for (int i=0; i< in.size()-1; i++) {
            QuakeEntry qe = in.get(i);
            QuakeEntry qe2 = in.get(i+1);
            if(qe.getMagnitude()>qe2.getMagnitude()){flag = false;}
             //如何也break掉for语句?
        }
        
        return flag;
    }
    
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
           int pass = 0;
       for (int i=0; i< in.size(); i++) {
           
           if(checkInSortedOrder(in))break;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
             pass+=1;
             
           
           
       }    
        System.out.println("Printing quakes after pass "+pass);
    }
    
    public void sortByMagnitudeWithBubbleSortCheck(ArrayList<QuakeEntry> in){
          int pass = 0;
        for(int i=0;i<in.size()-1;i++){
             if(checkInSortedOrder(in))break;
             onePassBubbleSort(in,i);
             //System.out.println(in);
              pass+=1;
            }
            System.out.println("Printing quakes after pass "+pass);
    }
      
     public void sortByDepth(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< 70; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
        
     }
     
     public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData , int numSorted){
         //System.out.println("Printing quakes after pass "+numSorted);
         
         for(int i= 0 ;i<quakeData.size()-numSorted-1;i++){
             QuakeEntry qe = quakeData.get(i);
             QuakeEntry adj = quakeData.get(i+1);
             QuakeEntry swap = quakeData.get(i);
             if(qe.getMagnitude() > adj.getMagnitude()){
                 quakeData.set(i+1,qe);
                 quakeData.set(i,adj);
                }
            }
            
        }
        
     public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
         for(int i=0;i<in.size()-1;i++){
             onePassBubbleSort(in,i);
             System.out.println(in);
            }
            
        }
        
     

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");   
        
        //sortByDepth(list);
       // sortByMagnitudeWithBubbleSort(list);
        //System.out.println("Earthquake in order:");
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
