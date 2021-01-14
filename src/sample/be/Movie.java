package sample.be;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Movie {

    /**
     * This class is for getter and setter methods for instances of the Movie class.
     */

    private static final String PROP_FILE = "data/config.properties";

    private int id;
    private String name;
    private int year;
    private String path;
    private int duration;
    private String rating;
    private String lastView;
    private int catMovId;

    public Movie(int id, String name, int year, String path, int duration, String rating, String lastView) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.path = path;
        this.duration = duration;
        this.rating = rating;
        this.lastView = lastView;
    }

    public int getID()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setCatMovId(int catMovId) {
        this.catMovId = catMovId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getPath() throws IOException {
        String url;
        Properties pathProperties = new Properties();
        FileInputStream ip = new FileInputStream(PROP_FILE);
        pathProperties.load(ip);
        url = pathProperties.getProperty("url");
        return url + path;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }
}
