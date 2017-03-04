package juanmon.technologynews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class DateComparator implements Comparator<StoryFeeed> {
    SimpleDateFormat inputFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
    //SimpleDateFormat outPutFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM a");
    public int compare(StoryFeeed s1,StoryFeeed s2){
        try {
            return inputFormat.parse(s1.getDate()).compareTo(inputFormat.parse(s2.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}



