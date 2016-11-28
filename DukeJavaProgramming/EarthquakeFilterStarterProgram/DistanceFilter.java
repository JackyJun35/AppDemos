
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
     private Location loc;
    private double maxDist;
    public DistanceFilter(Location loc,double max){
        this.loc=loc;
        maxDist = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(loc) < maxDist);
    }
}
