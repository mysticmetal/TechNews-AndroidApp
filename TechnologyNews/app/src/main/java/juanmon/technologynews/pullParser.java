package juanmon.technologynews;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class pullParser  {
        public static class StoryFeedPullParser{
            ArrayList<StoryFeeed> personsList;
            StoryFeeed storyFeeed;
            static ArrayList<StoryFeeed> parseStoryFeed(InputStream in) throws XmlPullParserException, IOException {
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setInput(in,"UTF-8");
                StoryFeeed storyFeeed = null;
                ArrayList<StoryFeeed> NewsList = new ArrayList<StoryFeeed>();
                int event = parser.getEventType();
                Boolean flag = false;

                while(event!=XmlPullParser.END_DOCUMENT){


                    switch(event){
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                storyFeeed = new StoryFeeed();
                                flag = true;
                            }
                            else if(parser.getName().equals("title") && flag == true) {
                             storyFeeed.setTitle(parser.nextText().trim());
                            }

                            else if(parser.getName().equals("pubDate")&&  flag == true){
                               storyFeeed.setDate(parser.nextText().trim());
                            }
                            else if(parser.getName().equals("description")&& flag == true) {
                                storyFeeed.setDescription(parser.nextText().trim());


                            }
                            else  if(parser.getName().equals("media:content")){
                                if(storyFeeed.getImage()==null)
                                {
                                    if(storyFeeed.getImage()==null){
                                        storyFeeed.setImage(parser.getAttributeValue(null,"url").trim());

                                    }
                                }


                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item")){
                                NewsList.add(storyFeeed);
                                storyFeeed = null;

                                flag = false;
                            }
                            break;
                        default: break;
                    }
                    event = parser.next();
                }
                return NewsList;
            }

        }
}
