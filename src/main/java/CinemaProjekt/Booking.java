package CinemaProjekt;
import org.slf4j.LoggerFactory;
public abstract class Booking extends Film {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Booking.class);
    public Booking(long ID, String title, int duration, int rating, String genre) {
        super(ID, title, duration, rating, genre);
    }
    public abstract int getRowNumber();
}

