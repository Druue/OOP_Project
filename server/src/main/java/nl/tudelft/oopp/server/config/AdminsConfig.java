package nl.tudelft.oopp.server.config;

import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserKind;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminsConfig {

    private final UserRepository userRepository;

    public AdminsConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Configuration bean for initial population of the database with information about 3
     *      administrators uses the createAdmin() method to create the 3 administrators and save
     *      them in the database.
     * @return  A {@link CommandLineRunner} bean representing the code for initial population.
     */
    @Bean
    public CommandLineRunner configureAdministrators() {
        return args -> {

            createAdmin(
                "tkoppelaar",
                "T.Koppelaar@admin.tudelft.nl",
                "admin1",
                new Details(
                    "Thomas",
                    "This is the first administrator",
                        null
                    )
            );

            createAdmin(
                "dbarantiev",
                "D.A.Barantiev@admin.tudelft.nl",
                "admin2",
                new Details("Dimitar",
                        "This is the second administrator",
                        null)
            );

            createAdmin(
                "nzhlebinkov",
                "n.a.zhlebinkov@student.tudelft.nl",
                "admin3",
                new Details(
                    "Nikkolay",
                    "This is the third administartor",
                        null
                )
            );
        };
    }

    /** Creates a new User with role Administrator and the provided credentials and saves him in
     *      the database using the {@link UserRepository} bean.
     * @param username  The unique username of the administrator.
     * @param email     The unique email of the administrator.
     * @param password  The password of the new user.
     * @param details   The Details object containing the name, the description and the picture of
     *                  the new user.
     */
    public void createAdmin(String username, String email, String password, Details details) {
        // Create the administrator with the provided data
        User newAdmin = new User();
        newAdmin.userKind = UserKind.Admin;
        newAdmin.username = username;
        newAdmin.email = email;
        newAdmin.password = password;
        newAdmin.details = details;

        // Save the administrator in the database
        userRepository.save(newAdmin);
    }

}
