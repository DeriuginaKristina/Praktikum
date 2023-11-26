package CinemaProjekt;
import org.slf4j.LoggerFactory;
public abstract class Film {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Film.class);

    private long ID;
    private String title;
    private int duration;
    private int rating;
    private String genre;
    private boolean isAvailableTickets;
    private int numberplace;
    private Booking booking;

    public Film(long ID, String title, int duration, int rating, String genre) {
        this.ID = ID;
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        this.isAvailableTickets = true; // Standardmäßig auf „true“ initialisieren (Initialize to true by default)
        this.numberplace = -1; // Auf einen ungültigen Wert initialisieren (Initialize to an invalid value)
    }

    public long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailableTickets() {
        return isAvailableTickets;
    }

    public int getNumberplace() {
        return numberplace;
    }

    public void setNumberplace(int numberplace) {
        this.numberplace = numberplace;
    }

    public void setAvailableTickets(boolean availableTickets) {
        isAvailableTickets = availableTickets;
    }

    public abstract void getBooking();

    public void setRowNumber(int rowNumber) {
    }

    public void setSeatNumber(int seatNumber) {
    }

    public boolean reserveSeat(int selectedRow, int selectedSeat) {
        try {
            if (isAvailableTickets() && !isSeatReserved(selectedRow, selectedSeat)) {
                setRowNumber(selectedRow);
                setSeatNumber(selectedSeat);
                setNumberplace(selectedSeat);
                return true;
            }
        } catch (Exception e) {
            LOGGER.warn("Error reserving seat: " + e.getMessage());
        }
        return false;
    }

    abstract void showTrailer();

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                '}';
    }

    protected void decreaseAvailableTickets() {
        try {
            LOGGER.info("Reducing available tickets");
        } catch (Exception e) {
            LOGGER.error("Error decreasing available tickets: " + e.getMessage());
        }
    }

    public abstract void buyTicket(int selectedRow, int selectedSeat) throws Exception;

    private boolean isSeatReserved(int selectedRow, int selectedSeat) throws Exception {
        throw new Exception("Error checking if the seat is reserved");
    }

    public boolean unreserveSeat() {
        try {
            return true;
        } catch (Exception e) {
            LOGGER.warn("Error unreserving seat: " + e.getMessage());
            return false;
        }
    }
}




