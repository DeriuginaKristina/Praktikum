package TimeApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class EventSchedulerTest {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventSchedulerTest.class);
    @Test
    public void testAddEvent() {
        // Create an instance of EventScheduler-Erstellen Sie eine Instanz von EventScheduler
        EventScheduler eventScheduler = new EventScheduler();

        // Create an event-Erstellen Sie eine Veranstaltung
        Event event = new Event("Test Event", LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        // Add the event to the scheduler-Fügen Sie das Ereignis zum Planer hinzu
        eventScheduler.addEvent(event);

        // Retrieve the list of all events-Rufen Sie die Liste aller Ereignisse ab
        List<Event> allEvents = eventScheduler.getAllEvents();

        // Check that the list contains the added event-Überprüfen Sie, ob die Liste das hinzugefügte Ereignis enthält
        assertTrue(allEvents.contains(event));

        // Check that the list has a size of 1 after adding the event-Überprüfen Sie, ob die Liste nach dem Hinzufügen des Ereignisses eine Größe von 1 hat
        assertEquals(1, allEvents.size());
        LOGGER.info("testAddEvent executed successfully");
    }
    @Test
    public void testRemoveEvent() {
        // Create an instance of EventScheduler-Erstellen Sie eine Instanz von EventScheduler
        EventScheduler eventScheduler = new EventScheduler();

        // Create an event-Erstellen Sie eine Veranstaltung
        Event event = new Event("Test Event", LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        // Add the event to the scheduler-Fügen Sie das Ereignis zum Planer hinzu
        eventScheduler.addEvent(event);

        // Check that the list has a size of 1 before removal-Überprüfen Sie vor dem Entfernen, ob die Liste die Größe 1 hat
        assertEquals(1, eventScheduler.getAllEvents().size());

        // Remove the event from the scheduler-Entfernen Sie das Ereignis aus dem Planer
        eventScheduler.removeEvent(event);

        // Check that the list is empty after removal-Überprüfen Sie, ob die Liste nach dem Entfernen leer ist
        assertTrue(eventScheduler.getAllEvents().isEmpty());
        LOGGER.info("testRemoveEvent executed successfully");
    }
    @Test
    public void testDoEventsOverlap() {
        // Create an instance of EventScheduler-Erstellen Sie eine Instanz von EventScheduler
        EventScheduler eventScheduler = new EventScheduler();

        // Get the current date and time-Erhalten Sie das aktuelle Datum und die aktuelle Uhrzeit
        LocalDateTime now = LocalDateTime.now();

        // Create two events with overlapping time ranges-Erstellen Sie zwei Ereignisse mit überlappenden Zeitbereichen
        Event event1 = new Event("Event 1", now, now.plusHours(2));
        Event event2 = new Event("Event 2", now.plusHours(1), now.plusHours(3));

        // Check that the events overlap-Überprüfen Sie, ob sich die Ereignisse überschneiden
        assertTrue(eventScheduler.doEventsOverlap(event1, event2));
        LOGGER.info("testDoEventsOverlap executed successfully");

        // Create a date for additional testing (optional)-Legen Sie einen Termin für zusätzliche Tests fest (optional)
        LocalDate testDate = LocalDate.of(2023, 12, 5);
    }
    @Test
    public void testGetEventsForDate() {
        // Create an instance of EventScheduler-Erstellen Sie eine Instanz von EventScheduler
        EventScheduler eventScheduler = new EventScheduler();

        // Create events with different dates-Erstellen Sie Veranstaltungen mit unterschiedlichen Terminen
        LocalDate testDate = LocalDate.of(2023, 12, 5);
        Event event1 = new Event("Event 1", LocalDateTime.of(testDate, LocalTime.of(10, 0)), LocalDateTime.of(testDate, LocalTime.of(12, 0)));
        Event event2 = new Event("Event 2", LocalDateTime.of(testDate, LocalTime.of(14, 0)), LocalDateTime.of(testDate, LocalTime.of(16, 0)));

        LocalDate otherDate = LocalDate.of(2023, 12, 6);
        Event event3 = new Event("Event 3", LocalDateTime.of(otherDate, LocalTime.of(10, 0)), LocalDateTime.of(otherDate, LocalTime.of(12, 0)));

        // Add events to the scheduler-Fügen Sie Ereignisse zum Planer hinzu
        eventScheduler.addEvent(event1);
        eventScheduler.addEvent(event2);
        eventScheduler.addEvent(event3);

        // Retrieve events for the specified date-Rufen Sie Ereignisse für das angegebene Datum ab
        List<Event> eventsForTestDate = eventScheduler.getEventsForDate(testDate);

        // Check that the correct events are returned for the specified date-Überprüfen Sie, ob für das angegebene Datum die richtigen Ereignisse zurückgegeben werden
        assertEquals(2, eventsForTestDate.size());
        assertTrue(eventsForTestDate.contains(event1));
        assertTrue(eventsForTestDate.contains(event2));
        LOGGER.info("testGetEventsForDate executed successfully");

        // Check that events for a different date are not included-Stellen Sie sicher, dass keine Ereignisse für ein anderes Datum enthalten sind
        assertFalse(eventsForTestDate.contains(event3));

    }}



