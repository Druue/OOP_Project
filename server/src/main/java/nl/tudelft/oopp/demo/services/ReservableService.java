package nl.tudelft.oopp.demo.services;
import nl.tudelft.oopp.demo.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public class ReservableService {

    @Autowired
    private ReservableRepository reservableRepository;

   public List<Reservable> getAllReservables() {
       List<Reservable> reservables = new ArrayList<>();
       reservableRepository.findAll()
               .forEach(reservables::add);
       return reservables;

   }

    public Reservable getReservable(String id) {
        return reservableRepository.findOne();
    }

    public void addTopic(Reservable reservable) {
        return reservableRepository.save(reservable);
    }

    public void updateTopic(String id, Reservable reservable) {
        return reservableRepository.save(topic);
    }

    public void deleteTopic(String id) {
        return reservableRepository.delete(id);
    }


}
