package juanmon.technologynews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageFromUrl extends AsyncTask<String,Void,Bitmap> {
    Bitmap bitmap;
    String id;
    getImageInterface imgInterface;

    public GetImageFromUrl(getImageInterface imgInterface) {
        this.imgInterface = imgInterface;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url= null;
        try {
            url = new URL(params[0]);
            id=params[1];
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            bitmap= BitmapFactory.decodeStream(con.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    interface getImageInterface{
        void setImg(Bitmap bitmap,String id );
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
       //super.onPostExecute(bitmap);
        imgInterface.setImg(bitmap,id);
    }
}
