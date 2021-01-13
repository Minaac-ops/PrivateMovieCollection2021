package sample.be;

import java.util.List;

public class Category {

    /**
     * This class is for getter and setter methods for instances of the Category class.
     */
    private List<Movie> movieList;
    private int id;
    private String name;
    private int catMovId;

    public Category(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getCatMovId() {
        return id;
    }

    public void setCatmovId(int catMovId) {
        this.catMovId = catMovId;
    }
    public int getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
