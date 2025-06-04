package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

    /**
     * Creates a new training record.
     *
     * @param trainingDto the DTO containing training details
     * @return the created Training entity
     */
    @Override
    public Training createTraining(final TrainingDto trainingDto) {
        log.info("Creating Training {}", trainingDto);
        if (trainingDto == null) {
            throw new IllegalArgumentException("Training cannot be null");
        }
        return trainingRepository.save(trainingMapper.toEntity(trainingDto, userProvider));
    }

    /**
     * Retrieves a training record by its ID.
     *
     * @param trainingId the ID of the training to retrieve
     * @return an Optional containing the Training entity if found, or empty if not found
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return getTraining(trainingId);
    }

    /**
     * Updates an existing training record.
     *
     * @param trainingId the ID of the training to update
     * @param trainingDto the DTO containing updated training details
     * @return an Optional containing the updated Training entity if found, or empty if not found
     */
    @Override
    public Optional<Training> updateTraining(final Long trainingId, final TrainingDto trainingDto) {
        if (trainingId == null || trainingDto == null) {
            throw new IllegalArgumentException("Training ID and DTO cannot be null");
        }
        Optional<Training> existingTraining = trainingRepository.findById(trainingId);
        if (existingTraining.isPresent()) {
            Training training = existingTraining.get();
            training.setActivityType(trainingDto.getActivityType());;
            training.setStartTime(trainingDto.getStartTime());
            training.setEndTime(trainingDto.getEndTime());

            // Update other fields as necessary
            trainingRepository.save(training);
            return Optional.of(training);
        } else {
            throw new TrainingNotFoundException(trainingId);
        }
    }

    /**
     * Finds all trainings associated with a specific user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of Training entities associated with the user
     */
    @Override
    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findAllByUser_Id(userId);
    }

    /**
     * Finds all trainings associated with a specific activity type.
     *
     * @param activityType the activity type to filter trainings
     * @return a list of Training entities matching the specified activity type
     */
    @Override
    public List<Training> findTrainingsByActivityType(final ActivityType activityType) {
        if (activityType == null) {
            throw new IllegalArgumentException("Activity type cannot be null");
        }
        return trainingRepository.findTrainingsByActivityType(activityType);
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findFinishedTrainingsAfter(Date afterTime) {
        if (afterTime == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return trainingRepository.findAllByEndTimeAfter(afterTime);
    }


}
