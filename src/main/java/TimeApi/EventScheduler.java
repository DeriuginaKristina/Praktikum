package TimeApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    public class EventScheduler {
        //Fügen Sie Methoden hinzu,
        // um Ereignisse hinzuzufügen und zu entfernen und eine Liste aller geplanten Ereignisse zu erhalten.
        //Add methods to add, remove events, and get a list of all scheduled events.
        private List<Event> events;
        public EventScheduler() {
            this.events = new ArrayList<>();
        }
        public void addEvent(Event event) {
            events.add(event);
        }
        public void removeEvent(Event event) {
            events.remove(event);
        }
        public List<Event> getAllEvents() {
            return events;
        }
        //Implementieren Sie in EventScheduler eine Methode, die eine Liste der für ein bestimmtes Datum geplanten Ereignisse zurückgibt.
        // In EventScheduler, implement a method that returns a list of events scheduled for a specific date.
        public List<Event> getEventsForDate(LocalDate date) {
            List<Event> eventsForDate = new ArrayList<>();
            for (Event event : events) {
                LocalDateTime startDateTime = event.getStartDateTime();
                if (startDateTime.toLocalDate().isEqual(date)) {
                    eventsForDate.add(event);
                }
            }
            return eventsForDate;
        }
        // Entwickeln Sie eine Methode, die testet, ob sich die Zeiten zweier verschiedener Ereignisse überschneiden.
        //Develop a method that tests whether the times of two different events overlap.
        public boolean doEventsOverlap(Event event1, Event event2) {
            LocalDateTime start1 = event1.getStartDateTime();
            LocalDateTime end1 = event1.getEndDateTime();
            LocalDateTime start2 = event2.getStartDateTime();
            LocalDateTime end2 = event2.getEndDateTime();
            //Suchen Sie nach nicht überlappenden Szenarien
            // Check for non-overlapping scenarios
            return start1.isBefore(end2) && end1.isAfter(start2);
        }}