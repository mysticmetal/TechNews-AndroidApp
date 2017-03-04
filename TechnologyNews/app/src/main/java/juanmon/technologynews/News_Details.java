package juanmon.technologynews;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class News_Details extends AppCompatActivity implements GetImageFromUrl.getImageInterface {
StoryFeeed newsStory;
    TextView title,date,desc;
    ImageView newsImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        if(getIntent().getExtras()!=null){
            Log.d("XXX",getIntent().getExtras().getParcelable(MainActivity.news).toString());
            newsStory=getIntent().getExtras().getParcelable(MainActivity.news);
        }
        title= (TextView) findViewById(R.id.news_title);
        date= (TextView) findViewById(R.id.date);
        desc= (TextView) findViewById(R.id.description);
        newsImage= (ImageView) findViewById(R.id.feedImage);
        title.setText(newsStory.getTitle());
        Log.d("date", "onCreate: "+convertDate(newsStory.getDate()));
        date.setText(convertDate(newsStory.getDate()));
        desc.setText(newsStory.getDescription());
        new GetImageFromUrl(this).execute(newsStory.getImage(),""+newsImage.getId());
    }



    public String convertDate(String date){
        String output;
        SimpleDateFormat inputFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat outPutFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM a");
        try {
            output = outPutFormat.format(inputFormat.parse(date));
            return output;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void setImg(Bitmap bitmap, String id) {
        //newsImage= (ImageView) findViewById(Integer.parseInt(id));
        Log.d("VERSATILE", "setImg: "+bitmap);
        newsImage.setImageBitmap(bitmap);
    }
}
