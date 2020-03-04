package nl.tudelft.oopp.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    /**
     *
     * @return a list of all timeslots
     */
    public List<TimeSlot> getAllTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        timeSlotRepository.findAll().forEach(timeSlots::add);
        return timeSlots;
    }

    /**
     *
     * @param id linked to a timeSlot
     * @return a timeSlot with that id
     */
    public Optional<TimeSlot> getTimeSlot(Long id) {
        return timeSlotRepository.findById(id);
    }

    /**
     *
     * @param timeSlot to be added to the list of timeSlots
     */
    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

    /**
     *
     * @param id linked to a timeSlot
     * @param timeSlot to be updated
     */
    public void updateTimeSlot(Long id, TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

    /**
     *
     * @param id linked to a timeslot will be deleted
     */
    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}
