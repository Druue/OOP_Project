package nl.tudelft.oopp.server.services;

import nl.tudelft.oopp.server.models.TimeSlots;
import nl.tudelft.oopp.server.repositories.TimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotsService {

    @Autowired
    private TimeSlotsRepository timeSlotsRepository;

    /**
     * Gets a list of all Timeslot collections.
     * 
     * @return a list with all timeslots
     */
    public List<TimeSlots> getAllTimeSlots() {
        List<TimeSlots> timeSlots = new ArrayList<TimeSlots>();
        timeSlotsRepository.findAll().forEach(timeSlots::add);
        return timeSlots;
    }

    /**
     * Gets a list of optional Timeslots.
     * 
     * @param id linked to a list of timeSlots
     * @return a list of timeSlots or null if it does not exist
     */
    public Optional<TimeSlots> getTimeSlots(Long id) {
        return timeSlotsRepository.findById(id);
    }

    /**
     * Adds a collection of Timeslots.
     * 
     * @param timeSlots to be added to the list of timeSlots lists
     */
    public void addTimeSlots(TimeSlots timeSlots) {
        timeSlotsRepository.save(timeSlots);
    }

    /**
     * Updates Timeslots.
     *
     * @param id        linked to a list of timeSlots
     * @param timeSlots to be updated
     */
    public void updateTimeSlots(Long id, TimeSlots timeSlots) {
        timeSlotsRepository.save(timeSlots);
    }

    /**
     * Deletes timeslots by ID.
     * 
     * @param id linked to a list of timeSlots will be deleted
     */
    public void deleteTimeSlots(Long id) {
        timeSlotsRepository.deleteById(id);
    }
}
