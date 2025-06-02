package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.List;

/**
 * UserController handles HTTP requests related to user operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users,
 * as well as fetching users by email and birthdate.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;

    private final UserMapper userMapper;

    /**
     * Retrieves all users in the system.
     *
     * @return a list of UserDto representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all users in a simplified format.
     *
     * @return a list of UserSimpleDto representing all users
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userProvider.findAllUsers()
                .stream()
                .map(userMapper::toDtoSimple)
                .toList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return UserDto representing the user with the specified ID
     * @throws UserNotFoundException if no user with the specified ID is found
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userProvider.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * Adds a new user to the system.
     *
     * @param userDto the UserDto containing user details
     * @return UserDto representing the created user
     * @throws InterruptedException if the thread is interrupted during processing
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);

        return  userMapper.toDto(createdUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     * @return UserDto representing the deleted user
     * @throws UserNotFoundException if no user with the specified ID is found
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return null;
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email of the user to retrieve
     * @return a list containing UserDto representing the user with the specified email, or an empty list if not found
     */
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userProvider.getUserByEmail(email)
                .map(user -> List.of(userMapper.toDto(user)))
                .orElse(List.of());
    }

    /**
     * Updates an existing user with new details.
     *
     * @param userId  the ID of the user to update
     * @param userDto the UserDto containing updated user details
     * @return ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves users who are older than a specified date.
     *
     * @param date the date to compare against
     * @return a list of UserDto representing users born before the specified date
     */
    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate date) {
        return userProvider.findUsersBornBefore(date).stream()
                .map(userMapper::toDto)
                .toList();
    }
}