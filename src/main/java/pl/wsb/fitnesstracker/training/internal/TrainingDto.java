package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.util.Date;
import lombok.Getter;

/**
 * Data Transfer Object for Training.
 * Represents a training session with details such as start time, end time, activity type, distance, and average speed.
 */
@Getter
public class TrainingDto {
        @Nullable
        private final Long id;
        private final Long userId;
        private final Date startTime;
        private final Date endTime;
        private final ActivityType activityType;
        private final double distance;
        private final double averageSpeed;
        public TrainingDto(
                @Nullable Long id,
                Long userId,
                Date startTime,
                Date endTime,
                ActivityType activityType,
                double distance,
                double averageSpeed) {
                this.id = id;
                this.userId = userId;
                this.startTime = startTime;
                this.endTime = endTime;
                this.activityType = activityType;
                this.distance = distance;
                this.averageSpeed = averageSpeed;
        }
}