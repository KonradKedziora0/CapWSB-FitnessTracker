package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;


/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);
    Optional<User> deleteUser(Long userId);
    void updateUser(Long userId, UserDto userDto);
    List<User> findUsersBornBefore(LocalDate date);
    Optional<User> getUser(Long userId);
}
