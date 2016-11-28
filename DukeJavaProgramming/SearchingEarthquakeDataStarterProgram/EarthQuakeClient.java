import java.util.*;
import edu.duke.*;

public class EarthQuakeClient{

    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData){
            if (qe.getMagnitude()>magMin){
                answer.add(qe);
             }
        }
            return answer;
    }
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int k=0;k<quakeData.size();k++){
         QuakeEntry quake = quakeData.get(k);
         Location loc = quake.getLocation();
          if(loc.distanceTo(from)<distMax){
             answer.add(quake);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth,
    double maxDepth){
        ArrayList <QuakeEntry> answer = new ArrayList<QuakeEntry>();
          for(int k=0;k<quakeData.size();k++){
            QuakeEntry quake = quakeData.get(k);
            double depth =quake.getDepth();
            if(depth>minDepth && depth<maxDepth){
                answer.add(quake);
            }
            }
            return answer;
    }
    
    
    public ArrayList<QuakeEntry> findTheLargest(ArrayList<QuakeEntry> quakeData,int howMany)
     {
        ArrayList <QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList <QuakeEntry> copy = quakeData;

          for(int i=0;i<howMany;i++){
              int maxIndex=0;
              QuakeEntry biggest = quakeData.get(0);
          for(int j=0;j<copy.size();j++){
              QuakeEntry qe = copy.get(j);
              if (qe.getMagnitude() >= biggest.getMagnitude()){
                  biggest = qe;
                  maxIndex=j;
                } 
             } 
             answer.add(copy.remove(maxIndex));
        }
            return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,
    String phrase){
        ArrayList <QuakeEntry> answer = new ArrayList<QuakeEntry>();
          for(int k=0;k<quakeData.size();k++){
            QuakeEntry quake = quakeData.get(k);
            String title = quake.getInfo();
            int phraseLength = phrase.length();
            int titleLength = title.length();
            switch(where){
                case "start":
                if(phrase.equals(title.substring(0,phraseLength))){answer.add(quake);}break;
                case "end":
                if(phrase.equals(title.substring(titleLength-phraseLength,titleLength))){answer.add(quake)
                    ;}break;
                case "any":
                for(int i=1;i<=titleLength-phraseLength;i++){
                if(phrase.equals(title.substring(i,i+phraseLength))){answer.add(quake);}
                }  break;
                default:break;
    
                
            }
            
            }
            return answer;
    }
    
     public QuakeEntry getSmallestElement (ArrayList<QuakeEntry> quakeData){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        QuakeEntry min= quakeData.get(0);
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude() < min.getMagnitude()){
                 min = qe;
            }
    }
      return min;
   }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry>in){
        //an empty out list
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        //as long as empty
        while(!in.isEmpty()){
            QuakeEntry minElement = getSmallestElement(in);
            in.remove(minElement);
            out.add(minElement);
        }
        
         return out;
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
    public void biggestQuake(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> listBiggest = findTheLargest(list,20);
        for(QuakeEntry qe: listBiggest){
            System.out.println(qe);
        }
         System.out.println("has "+listBiggest.size()+ " quakes matched");
    
    }
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
 
        ArrayList<QuakeEntry> listBig = sortByMagnitude(list);
        for(QuakeEntry qe : listBig){
              System.out.println(qe);
            }  
            System.out.println("has "+listBig.size()+ "quakes valid");
     }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
         Location city =  new Location(38.17, -118.82);
         
        ArrayList<QuakeEntry> listClose =  filterByDistanceFrom(list,1000000,city);
        for(QuakeEntry qe:listClose){
            System.out.println(qe);
        }
           System.out.println("has "+listClose.size()+" matched");
        // This location is Bridgeport, CA
         

        // TODO
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

         
        ArrayList<QuakeEntry> listDepth =  filterByDepth(list,-4000,-2000);
        for(QuakeEntry qe: listDepth){
            System.out.println(qe);
        }
           System.out.println("has "+listDepth.size()+" matched");
        
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

         
        ArrayList<QuakeEntry> listPhrase =  filterByPhrase(list,"any","Can");
        for(QuakeEntry qe: listPhrase){
            System.out.println(qe);
        }
           System.out.println("has "+listPhrase.size()+" matched");
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
        
    }
    
}
