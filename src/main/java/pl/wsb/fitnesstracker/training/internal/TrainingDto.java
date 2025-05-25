package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;


public record TrainingDto (
        @Nullable Long id,
        UserDto user,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed) {
}