package todo.example.willis.myweather;

/**
 * Created by willis on 7/14/17.
 * Item model for HorizontalRecyclerViewAdapter
 */

public class WeatherListModel {
    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmTemp() {
        return mTemp;
    }

    public void setmTemp(String mTemp) {
        this.mTemp = mTemp;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    private String mTime, mTemp;
    private int mIcon;

    public WeatherListModel(String mTime, String mTemp, int mIcon) {
        this.mTime = mTime;
        this.mTemp = mTemp;
        this.mIcon = mIcon;
    }

}
