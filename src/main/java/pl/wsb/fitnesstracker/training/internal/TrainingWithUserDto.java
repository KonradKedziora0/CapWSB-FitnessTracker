package pl.wsb.fitnesstracker.training.internal;

import jakarta.annotation.Nullable;
import lombok.Getter;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * Data Transfer Object for Training with associated User.
 * Represents a training session with details such as start time, end time, activity type, distance, average speed,
 * and the user who performed the training.
 */
@Getter
public class TrainingWithUserDto extends TrainingDto {
    private final UserDto user;

    public TrainingWithUserDto(
            @Nullable Long id,
            @Nullable Long userId,
            Date startTime,
            Date endTime,
            ActivityType activityType,
            double distance,
            double averageSpeed,
            UserDto user) {
        super(id, userId, startTime, endTime, activityType, distance, averageSpeed);
        this.user = user;
    }
    public TrainingWithUserDto(
            TrainingDto trainingDto,
            UserDto user) {
        this(trainingDto.getId(),
             trainingDto.getUserId(),
             trainingDto.getStartTime(),
             trainingDto.getEndTime(),
             trainingDto.getActivityType(),
             trainingDto.getDistance(),
             trainingDto.getAverageSpeed(),
             user);
    }
}
