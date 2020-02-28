package nl.tudelft.oopp.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Repository, Integer> {

    // This is a custom query to find a revervation
    List<Repository> findByTitleContainingOrContentContaining(String text, String textAgain);

}
