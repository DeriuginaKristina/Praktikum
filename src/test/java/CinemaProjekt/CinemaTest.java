package CinemaProjekt;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {
    @Test
    void returnTicket_Success() {
        Cinema cinema = new Cinema();
        Cartoon cartoon = new Cartoon(1, "Cartoon", 90, 5, "Animation", "2D");
        cinema.addFilm(cartoon);
        int selectedRow = 1;
        int selectedSeat = 2;

        // Kaufen Sie zuerst ein Ticket (Buy a ticket first)
        cinema.buyTicket(cartoon, selectedRow, selectedSeat);

        //
        //Geben Sie das Ticket zurück (Return the ticket)
        cinema.returnTicket(cartoon);

        assertTrue(cartoon.isAvailableTickets());
    }
    @Test
    void getAvailableTickets_Success() {
        Cinema cinema = new Cinema();
        Cartoon cartoon = new Cartoon(1, "Cartoon", 90, 5, "Animation", "2D");
        cinema.addFilm(cartoon);
        int selectedRow = 1;
        int selectedSeat = 2;

        // Kaufen Sie zuerst ein Ticket (Buy a ticket first)
        cinema.buyTicket(cartoon, selectedRow, selectedSeat);

        // Überprüfen Sie die verfügbaren Tickets (Check available tickets)
        cinema.getAvailableTickets(cartoon);
    }
    @Test
    void buyTicket_Cartoon_Success() {
        Cinema cinema = new Cinema();
        Cartoon cartoon = new Cartoon(1, "Cartoon", 90, 5, "Animation", "2D");
        cinema.addFilm(cartoon);
        int selectedRow = 1;
        int selectedSeat = 2;
        cinema.buyTicket(cartoon, selectedRow, selectedSeat);
        assertTrue(cartoon.isAvailableTickets());
        assertEquals(1, cinema.getFilmsOnShow().size()); // Überprüfen Sie, ob der Film noch zu sehen ist (Check that the film is still on show)
    }
    @Test
    void buyTicket_FeatureFilm_Success() {
        Cinema cinema = new Cinema();
        FeatureFilm featureFilm = new FeatureFilm(2, "FeatureFilm", 120, 4, "Action", "Director", List.of("Actor1", "Actor2"));
        cinema.addFilm(featureFilm);
        int selectedRow = 1;
        int selectedSeat = 2;
        cinema.buyTicket(featureFilm, selectedRow, selectedSeat);
        assertTrue(featureFilm.isAvailableTickets());
        assertEquals(1, cinema.getFilmsOnShow().size()); // Überprüfen Sie, ob der Film noch zu sehen ist (Check that the film is still on show)
        }
    @Test
    void addFilm_Success() {
        Cinema cinema = new Cinema();
        Film film = new Cartoon(1, "Cartoon", 90, 5, "Animation", "2D");
        cinema.addFilm(film);
        assertTrue(cinema.getFilmsOnShow().contains(film));
    }
    @Test
    void findFilmById_ExistingFilm_Success() {
        Cinema cinema = new Cinema();
        Film film = new Cartoon(1, "Cartoon", 90, 5, "Animation", "2D");
        cinema.addFilm(film);
        Film foundFilm = cinema.findFilmById(1);
        assertNotNull(foundFilm);
        assertEquals(film, foundFilm);
    }
    @Test
    void findFilmById_NonExistingFilm_ReturnsNull() {
        Cinema cinema = new Cinema();
        Film foundFilm = cinema.findFilmById(1);

        assertNull(foundFilm);
    }}