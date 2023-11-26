package CinemaProjekt;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
public class Cinema implements TicketOperations {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Cinema.class);
    private List<Film> films = new ArrayList<>(); // Datenbank der Filme in unserem Kino (Database of films in our cinema)
    public List<Film> getFilmsOnShow() {
        return films;
    }
    public void addFilm(Film film) {
        films.add(film);}
    public Film findFilmById(long id) {
        for (Film film : films) {
            if (film.getID() == id) {
                return film;
            }
        }
        return null;
    }
    public void removeFilmByID(long id) {
        Film filmToRemove = findFilmById(id);
        try {
            if (filmToRemove != null) {
                films.remove(filmToRemove);
            } else {
                throw new Exception("Film with ID " + id + " not found for removal");
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }
    public void showFilmInfo(Film film) {
        try {
            System.out.println("Title: " + film.getTitle());
            System.out.println("Duration: " + film.getDuration() + " minutes");
            //Informationen zum Film (Information about the film)
        } catch (Exception e) {
            LOGGER.warn("Error showing film info: " + e.getMessage());
        }
    }
    @Override
    public void buyTicket(Film film, int selectedRow, int selectedSeat) {
        try {
            film.decreaseAvailableTickets();
            film.buyTicket(selectedRow, selectedSeat);
            LOGGER.info("Ticket bought for Film: " + film.getTitle() + " at Row: " + selectedRow + ", Seat: " + selectedSeat);
        } catch (Exception e) {
            LOGGER.warn("Error buying ticket: " + e.getMessage());
        }
    }
    @Override
    public void returnTicket(Film film) {
        try {
            // Implement logic to return a ticket for the film
            if (film.unreserveSeat()) {
                film.setAvailableTickets(true);
                LOGGER.info("Ticket returned for Film: " + film.getTitle());
            } else {
                throw new Exception("Error returning ticket for Film: " + film.getTitle());
            }
        } catch (Exception e) {
            LOGGER.warn("Error returning ticket: " + e.getMessage());
        }
    }
    @Override
    public void getAvailableTickets(Film film) {
        try {
            if (film.isAvailableTickets()) {
                System.out.println("Available tickets for Film: " + film.getTitle() + " - " + film.getNumberplace());
            } else {
                System.out.println("No available tickets for Film: " + film.getTitle());
            }
        } catch (Exception e) {
            LOGGER.warn("Error getting available tickets: " + e.getMessage());
        }
    }}



