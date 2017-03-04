package juanmon.technologynews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GetAsynkNewsFeed.LinkData,GetImageFromUrl.getImageInterface{
    ProgressDialog progressDialog;
    ArrayList<StoryFeeed> s;
    ScrollView sv;
    LinearLayout MainLL;
    ProgressDialog progress;
    ImageView imgView;
    public final static String news="NEWSFEED";
    StoryFeeed sObj;
    String lID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=new ArrayList<StoryFeeed>();
        progress=new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setTitle("Loading News..");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        new GetAsynkNewsFeed(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
        sv = (ScrollView)findViewById(R.id.scrollView_id);
        MainLL= (LinearLayout) findViewById(R.id.linear_layout_id);




    }
    public void printListItems(ArrayList<StoryFeeed> newsList) {
        s.addAll(newsList);
        Log.d("size of list in main",s.size()+"..");
        progress.dismiss();
        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setOrientation(LinearLayout.VERTICAL);
        int sdk = android.os.Build.VERSION.SDK_INT;

        for (int i = 0; i < s.size() - 1; i++) {
            sObj=s.get(i);
            LinearLayout childLL = new LinearLayout(this);
            childLL.setOrientation(LinearLayout.HORIZONTAL);
            childLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            childLL.setGravity(Gravity.LEFT);
            childLL.setPadding(40,40,40,40);
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                childLL.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_shape));
            }
            else{
                //childLL.setBackground(getResources().getDrawable(R.drawable.list_shape));
            }
            TextView text = new TextView(this);
            ImageView imgView=new ImageView(this);
            TextView tv=new TextView(this);
            imgView.setId(i*80);
            imgView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
            imgView.setPadding(0,0,40,0);
            String sId=""+(i*80);
            new GetImageFromUrl(MainActivity.this).execute(s.get(i).getImage(),sId);
            childLL.addView(imgView);
            text.setTextSize(16);
            text.setText(s.get(i).getTitle());
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            childLL.addView(text);
            childLL.setId(i+1);
            lID=i+1+"";
            innerLayout.addView(childLL);
            childLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=v.getId()-1;
                    Intent i = new Intent(MainActivity.this, News_Details.class);
                    i.putExtra(news,s.get(index));
                    i.putExtra("ID",lID);
                    startActivity(i);
                }
            });
        }
        sv.addView(innerLayout);
    }


    @Override
    public void setImg(Bitmap bitmap, String viewID) {
       // int iD=Integer.parseInt(viewID);
        imgView =(ImageView)findViewById(Integer.parseInt(viewID));
       imgView.setImageBitmap(bitmap);
       // Log.d("Image URI",bitmap+"MAN"+".."+viewID+">>>"+imgView.getId());
    }


}
