package nl.tudelft.oopp.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    UserData userData = UserData.getInstance();


    /**
     * @return the users
     */
    @GetMapping("/Users")
    public Collection<Users> index() {
        return userData.fetchUsers();
    }

    /**
     * @param Id
     * @return the user id
     */
    @GetMapping("/Users/{id}")
    public Users show(@PathVariable int Id) {
        int userId = Integer.parseInt(id);
        return userdata.getUserByid(userId);
    }

    /**
     * @param body
     * @return the string of users according to the search term.
     */
    @PostMapping("/Users/search")
    public Collection<Users> create(@RequestBody Map<String, String> body) {
        String searchUser = body.get("thingy");
        return userData.searchUsers(searchUser);
    }

    /**
     * @param body
     * @return creates user from the sent information to the server
     */
    @PostMapping("/Users")
    public Collection<Users> create(@RequestBody Map<String, String> body) {
        int id = Integer.parseInt(body.get("id"));
        String name = body.get("name");
        String userName = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        String type = body.get("type");
        return userData.createUser(id, name, userName, email, password, type);
    }

    /**
     * @param id
     * @param body
     * @return updates an existing user inside of the database
     */
    @PutMapping("/Users/{id}")
    public Users update(@PathVariable int id, @RequestBody Map<String, String> body) {
        int id = Integer.parseInt(body.get("id"));
        String name = body.get("name");
        String userName = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        String type = body.get("type");
        return userData.updateUser(id, name, userName, email, password, type);
    }

    /**
     * @param id
     * @return the deleted user accoridng to their user id.
     */
    @DeleteMapping("/Users/{id}")
    public boolean delete(@PathVariable int id) {
        int id = Integer.parseInt(body.get("id"));

        return userData.deleteUser(id);
    }



}
