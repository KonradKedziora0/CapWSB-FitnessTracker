package pl.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link User} and its DTO representations.
 */
@Component
public class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to convert
     * @return the converted UserDto
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a {@link User} entity to a simple {@link UserSimpleDto}.
     *
     * @param user the user entity to convert
     * @return the converted UserSimpleDto
     */
    public UserSimpleDto toDtoSimple(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     *
     * @param userDto the user DTO to convert
     * @return the converted User entity
     */
    public User toEntity(UserDto userDto) {
        return new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthdate(),
                userDto.getEmail());
    }

}
