package sample.be;

import java.util.List;

public class Category {

    /**
     * This class is for getter and setter methods for instances of the Category class.
     */

    private List<Movie> movieList; //List of movies in a specific category.
    private int id;
    private String name;

    public Category(int id, String name)
    {
        this.id = id;
        this.name = name;
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
