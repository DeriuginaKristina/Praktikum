package CinemaProjekt;
import org.slf4j.LoggerFactory;
import java.util.List;
public class FeatureFilm extends Film {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FeatureFilm.class);
    private String director;
    private List<String> mainActors;

    public FeatureFilm(long ID, String title, int duration, int rating, String genre, String director, List<String> mainActors) {
        super(ID, title, duration, rating, genre);
        this.director = director;
        this.mainActors = mainActors;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public List<String> getMainActors() {
        return mainActors;
    }
    public void setMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
    }

    @Override
    public void getBooking() {

    }

    @Override
    void showTrailer() {
        System.out.println("FeatureFilm{" +
                "director='" + director + '\'' +
                ", mainActors=" + mainActors +
                "} " + super.toString());
    }
    @Override
    public String toString() {
        return "FeatureFilm{" +
                "director='" + director + '\'' +
                ", mainActors=" + mainActors +
                "} " + super.toString();
    }
    @Override
    public void buyTicket(int selectedRow, int selectedSeat) {
        try {
            LOGGER.info("Buying ticket for FeatureFilm at Row: " + selectedRow + ", Seat: " + selectedSeat);
        } catch (Exception e) {
            LOGGER.warn("Error buying ticket: " + e.getMessage());
        }
    }
}
