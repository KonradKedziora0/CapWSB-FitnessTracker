package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserDto;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UserService interface, providing methods to manage users.
 * This service handles user creation, retrieval, deletion, and updates.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user in the system.
     *
     * @param user The user to be created
     * @return The created User entity
     * @throws IllegalArgumentException if the user already has an ID (indicating it is not a new user)
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve
     * @return An Optional containing the User if found, or empty if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete
     * @throws IllegalArgumentException if the userId is null
     */
    @Override
    public void deleteUser(final Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
        }
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email of the user to retrieve
     * @return An Optional containing the User if found, or empty if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return A list of all User entities
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates an existing user with new details.
     *
     * @param userId  The ID of the user to update
     * @param userDto The DTO containing updated user details
     * @throws IllegalArgumentException if the user with the given ID is not found
     */
    @Override
    public void updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthdate(userDto.getBirthdate());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
    }

    /**
     * Finds users born before a specified date.
     *
     * @param date The date to compare against
     * @return A list of users born before the specified date
     */
    @Override
    public List<User> findUsersBornBefore(LocalDate date) {
        return userRepository.findUsersBornBefore(date);
    }
}