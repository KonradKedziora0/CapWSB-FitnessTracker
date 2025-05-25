package pl.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.*;


@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    public UserSimpleDto toDtoSimple(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

}
