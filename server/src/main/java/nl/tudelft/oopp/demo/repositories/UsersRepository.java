package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * @param name
     * @return the name of a user from the Users table
     */
    public Collection<Users> findUserByName(String name);

    /**
     * @param Id
     * @return the Id of a user from the Users table
     */
    public Users findUsersById(int Id);




}
