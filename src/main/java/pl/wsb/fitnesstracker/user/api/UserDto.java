package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Data Transfer Object for User.
 * Represents a user with details such as first name, last name, birthdate, and email.
 */
@Getter
public class UserDto {
    @Nullable
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String email;
    public UserDto(
            @Nullable Long id,
            String firstName,
            String lastName,
            LocalDate birthdate,
            String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

}
