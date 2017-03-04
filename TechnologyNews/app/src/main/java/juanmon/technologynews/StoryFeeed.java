package juanmon.technologynews;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class StoryFeeed implements Parcelable {

    String Title;
    String Date;
    String Description;
    String Link;
    String Image;

    public StoryFeeed() {
    }

    protected StoryFeeed(Parcel in) {
        Title = in.readString();
        Date = in.readString();
        Description = in.readString();
        Link = in.readString();
        Image = in.readString();
    }

    public static final Creator<StoryFeeed> CREATOR = new Creator<StoryFeeed>() {
        @Override
        public StoryFeeed createFromParcel(Parcel in) {
            return new StoryFeeed(in);
        }

        @Override
        public StoryFeeed[] newArray(int size) {
            return new StoryFeeed[size];
        }
    };

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @Override
    public String toString() {
        return "StoryFeeed{" +
                "Date='" + Date + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Image=" + Image +
                '}';
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Date);
        dest.writeString(Description);
        dest.writeString(Link);
        dest.writeString(Image);
    }
}
