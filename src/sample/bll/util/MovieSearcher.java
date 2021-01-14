package sample.bll.util;

import sample.be.Category;
import sample.be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {

    /**
     * Search function.
     */

    public List<Movie> search(List<Movie> searchfilter, String query) {
        List<Movie> searchResult = new ArrayList<>();
        for (Movie movie: searchfilter) {
            if (compareToMovieName(query, movie) || compareToRating(query, movie)) {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    /**
     * Search function for specific Movie name.
     */
    private boolean compareToMovieName(String query, Movie movie) {
        return movie.getName().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Search function for specific Movie category.
     */
    private boolean compareToRating(String query, Movie movie) {
        return movie.getRating().toLowerCase().contains(query.toLowerCase());
    }
}