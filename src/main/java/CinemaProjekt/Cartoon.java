package CinemaProjekt;
import org.slf4j.LoggerFactory;
    public class Cartoon extends Film {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Cartoon.class);
    private String animationType;
    public Cartoon(long ID, String title, int duration, int rating, String genre, String animationType) {
        super(ID, title, duration, rating, genre);
        this.animationType = animationType;
    }
    public String getAnimationType() {
        return animationType;
    }

    @Override
    public void getBooking() {
    }
    @Override
    void showTrailer() {
        System.out.println("Cartoon{" +
                "animationType='" + animationType + '\'' +
                "} " + super.toString());
    }
        @Override
        public void buyTicket(int selectedRow, int selectedSeat) throws Exception {
            try {
                // Implementieren Sie die Ticketkauflogik für Cartoon (Implement buy ticket logic for Cartoon)
                if (isAvailableTickets()) {
                    LOGGER.info("Buying ticket for Cartoon at Row: " + selectedRow + ", Seat: " + selectedSeat);
                    reserveSeat(selectedRow, selectedSeat);
                    decreaseAvailableTickets();
                } else {
                    throw new Exception("No available tickets for Cartoon");
                }
            } catch (Exception e) {
                LOGGER.warn("Error buying ticket: " + e.getMessage());
                throw new Exception("Error buying ticket");
            }}}



