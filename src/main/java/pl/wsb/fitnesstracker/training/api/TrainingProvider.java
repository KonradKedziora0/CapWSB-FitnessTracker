package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings of specific user.
     *
     * @param userId The ID of the user whose trainings are to be retrieved
     * @return A list of all trainings
     */
    List<Training> findTrainingsByUserId(Long userId);

    /**
     * Retrieves all trainings by activity type.
     *
     * @param activityType The activity type to filter trainings
     * @return A list of trainings matching the specified activity type
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves all trainings.
     *
     * @return A list of all trainings
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves all trainings that are finished after the specified time.
     *
     * @param afterTime The time after which the trainings should be finished
     * @return A list of trainings that are finished after the specified time
     */
    List<Training> findFinishedTrainingsAfter(Date afterTime);

}
