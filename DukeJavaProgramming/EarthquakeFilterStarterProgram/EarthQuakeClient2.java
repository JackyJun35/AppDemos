import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        Filter f1 = new PhraseFilter("end","California"); 
        Filter f2 = new MagnitudeFilter(4.0,5.0);
        ArrayList<QuakeEntry> m1  = filter(list,f1);
        ArrayList<QuakeEntry> m2  = filter(list,f1);
        for (QuakeEntry qe2 : m2){
           System.out.println(qe2);
        }
        
    }
    
    public void testMatchAllFilter2() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        MatchAllFilter maf = new MatchAllFilter();
        Location Japan = new Location(36.1314,-95.9372);
        
        Filter f1 = new DistanceFilter(Japan,10000000); 
        //Filter f1 = new DepthFilter(-100000,-10000);
        Filter f2 = new MagnitudeFilter(0.0,3.0);
        Filter f3 = new PhraseFilter("any","Ca");
         maf.addFilter(f1);
         maf.addFilter(f2);
         maf.addFilter(f3);
         ArrayList<QuakeEntry> listAll = filter(list,maf);
         for(QuakeEntry qe:listAll ){
         
           System.out.println(qe);
         
        }
        System.out.println("has "+listAll.size() + " matched");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
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
