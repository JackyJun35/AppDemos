
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String phrase;
    private String where;
    public PhraseFilter(String where,String phrase){
        this.phrase = phrase;
        this.where = where;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        int phraseLength = phrase.length();
        int titleLength =title.length();
        boolean flag = false;
        switch(where){
                case "start":
                flag = phrase.equals(title.substring(0,phraseLength));break;
                case "end":
                flag = phrase.equals(title.substring(titleLength-phraseLength,titleLength));break;
                case "any":
                for(int i=0;i<=titleLength-phraseLength;i++){
                if(phrase.equals(title.substring(i,i+phraseLength)))
                {flag = true;break;} 
                }  break;
                default:
                flag = false;break;
        }
        return flag;
    }
  }
