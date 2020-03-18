package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.TimeSlots;
import nl.tudelft.oopp.server.repositories.TimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<TimeSlots> timeSlots = new ArrayList<>();
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
     * @param openingTimes to be added to the list of timeSlots lists
     */
    public void addTimeSlots(TimeSlots openingTimes) {
        timeSlotsRepository.save(openingTimes);
    }

    /**
     * Updates Timeslots.
     *
     * @param id           linked to a list of timeSlots
     * @param openingTimes to be updated
     */
    public void updateTimeSlots(Long id, TimeSlots openingTimes) {
        timeSlotsRepository.save(openingTimes);
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
