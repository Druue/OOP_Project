package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.models.TimeSlots;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
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
     *
     * @return a list with all timeslots
     */
    public List<TimeSlots> getAllTimeSlots() {
        List<TimeSlots> timeSlots = new ArrayList<TimeSlots>();
        timeSlotsRepository.findAll().forEach(timeSlots::add);
        return timeSlots;
    }

    /**
     *
     * @param id linked to a list of timeSlots
     * @return a list of timeSlots or null if it does not exist
     */
    public Optional<TimeSlots> getTimeSlots(Long id) {
        return timeSlotsRepository.findById(id);
    }

    /**
     *
     * @param timeSlots to be added to the list of timeSlots lists
     */
    public void addTimeSlots(TimeSlots timeSlots) {
        timeSlotsRepository.save(timeSlots);
    }

    /**
     *
     * @param id linked to a list of timeSlots
     * @param timeSlots to be updated
     */
    public void updateTimeSlots(Long id, TimeSlots timeSlots) {
        timeSlotsRepository.save(timeSlots);
    }

    /**
     *
     * @param id linked to a list of timeSlots will be deleted
     */
    public void deleteTimeSlots(Long id) {
        timeSlotsRepository.deleteById(id);
    }
}
