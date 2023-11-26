package CinemaProjekt;
public interface TicketOperations {
    void buyTicket(Film film, int selectedRow, int selectedSeat);
    void returnTicket(Film film);
    void getAvailableTickets(Film film);
}

