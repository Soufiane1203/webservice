@WebService
public class MovieServiceEndpoint {
    private MovieService movieService = new MovieService();

    @WebMethod
    public Movie getMovieById(int id) {
        return movieService.getMovieById(id);
    }

    @WebMethod
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
