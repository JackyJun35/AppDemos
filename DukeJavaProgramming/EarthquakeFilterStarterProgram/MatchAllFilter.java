
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter  {
    private  ArrayList<Filter> matchAll = new ArrayList<>();;
    
    public MatchAllFilter(){
      ArrayList<Filter> matchAll = new ArrayList<>();
    }
    
    public  void addFilter(Filter f){
      matchAll.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        boolean flag = true;
        QuakeEntry copy = new QuakeEntry(0,0,0,"0",0);
        copy = qe;
      for (Filter f : matchAll){
          if(!f.satisfies(copy)) {flag=false;}
          }
        
        return flag;
    }
}