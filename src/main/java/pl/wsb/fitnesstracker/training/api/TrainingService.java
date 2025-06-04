package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.training.internal.TrainingDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {
    /**
     * Creates a new training.
     * If the training with the given ID already exists, then an {@link IllegalArgumentException} will be thrown.
     * @param trainingDto The DTO containing the training data to be created
     */
    Training createTraining(TrainingDto trainingDto);

    /**
     * Updates an existing training.
     * If the training with the given ID is not found, then an {@link TrainingNotFoundException} will be thrown.
     * @param trainingId The ID of the training to be updated
     * @param trainingDto The DTO containing the updated training data
     */
    Optional<Training> updateTraining(Long trainingId, TrainingDto trainingDto);

    List<Training> findAllTrainings();

    List<Training> findFinishedTrainingsAfter(Date afterTime);


}
