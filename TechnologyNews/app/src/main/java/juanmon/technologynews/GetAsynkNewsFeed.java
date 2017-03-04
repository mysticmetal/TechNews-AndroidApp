package juanmon.technologynews;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetAsynkNewsFeed extends AsyncTask<String, Void, ArrayList<StoryFeeed>> {
    LinkData activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public GetAsynkNewsFeed(LinkData activity) {
        this.activity = activity;
    }

    @Override

    protected void onPostExecute(ArrayList<StoryFeeed> storyFeeeds) {

        Log.d("demo",storyFeeeds.toString());
        Collections.sort(storyFeeeds,new DateComparator());
                activity.printListItems(storyFeeeds);
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        try {


            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();



            if(status == HttpURLConnection.HTTP_OK){

                InputStream in = con.getInputStream();
                //pullParser.StoryFeedPullParser.parsePersons(in);
                return  pullParser.StoryFeedPullParser.parseStoryFeed(in);

            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;
    }

     public  interface LinkData{
         void printListItems(ArrayList<StoryFeeed> storyFeeds);
    }


}
