package wolox.training.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    /**
     * Method to get the users
     *
     * @return
     */
    @GetMapping
    public Iterable findAll() {
        return userRepository.findAll();
    }

    /**
     * Method to find by username
     *
     * @param username: String
     * @return
     */
    @GetMapping("/username/{username}")
    public List findByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Method to find by id
     *
     * @param id: Long
     * @return findById
     * @throws BookNotFoundException
     */
   @GetMapping("/users/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Method to create a User
     *
     * @param user: User
     * @return save
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Method to delete by id
     *
     * @param id: Long
     * @throws UserNotFoundException
     */
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    /**
     * Method to update a user
     *
     * @param user: User
     * @param id:   Long
     * @return save
     * @throws UserIdMismatchException
     * @throws UserNotFoundException
     */
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new UserIdMismatchException();
        }
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return userRepository.save(user);
    }

    
}
