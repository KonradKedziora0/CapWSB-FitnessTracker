package pl.wsb.fitnesstracker.user.api;


/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     * If the user with the given ID already exists, then an {@link IllegalArgumentException} will be thrown.
     *
     * @param user the user to be created
     * @return the created user
     */
    User createUser(User user);

    /**
     * Deletes a user by their ID.
     * If the user with the given ID is not found, then an {@link UserNotFoundException} will be thrown.
     *
     * @param userId the ID of the user to be deleted
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user.
     * If the user with the given ID is not found, then an {@link UserNotFoundException} will be thrown.
     *
     * @param userId  the ID of the user to be updated
     * @param userDto the DTO containing the updated user data
     */
    void updateUser(Long userId, UserDto userDto);
}
