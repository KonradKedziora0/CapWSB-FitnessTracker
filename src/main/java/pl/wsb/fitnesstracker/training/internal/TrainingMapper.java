package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.*;

/**
 * Mapper for converting between {@link Training} and its DTO representations.
 */
@Component
class TrainingMapper {

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the training entity to convert
     * @return the converted TrainingDto
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     *
     * @param trainingDto the training DTO to convert
     * @param userProvider the user provider to fetch the user entity
     * @return the converted Training entity
     */
    Training toEntity(TrainingDto trainingDto, UserProvider userProvider) {
        User user = userProvider.getUser(trainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.getUserId()));
        return new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }

}
