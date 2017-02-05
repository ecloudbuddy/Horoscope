package app.sungi.horoscope;

/**
 * Created by Sungi on 21.01.2017.
 */

public class Zodiac {

    private String name, date, info;
    private int icon;

    public Zodiac(String name, String date, String info, int icon) {
        this.name = name;
        this.date = date;
        this.info = info;
        this.icon = icon;
    }


    public String getSignName() {
        return name;
    }

    public void setSignName(String name) {
        this.name = name;
    }

    public String getSignDate() {
        return date;
    }

    public void setSignDate(String date) {
        this.date = date;
    }

    public String getSignInfo() {
        return info;
    }

    public void setSignInfo(String info) {
        this.info = info;
    }

    public int getSignIcon() {
        return icon;
    }

    public void setSignIcon(int icon) {
        this.icon = icon;
    }
}
