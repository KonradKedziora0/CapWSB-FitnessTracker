package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingDto;

import java.util.List;
import java.util.Optional;


/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    Training createTraining(Training training);
    Optional<Training> deleteTraining(Long trainingId);
    void updateTraining(Long trainingId, TrainingDto trainingDto);
    List<Training> findTrainingsByUserId(Long userId);
}
