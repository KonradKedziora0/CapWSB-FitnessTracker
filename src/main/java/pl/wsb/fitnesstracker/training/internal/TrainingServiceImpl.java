package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Training createTraining(final Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return getTraining(trainingId);
    }

    @Override
    public Optional<Training> deleteTraining(final Long trainingId) {
        if (trainingId == null) {
            throw new IllegalArgumentException("Training ID cannot be null");
        }
        //todo
        return Optional.empty();
    }
    @Override
    public void updateTraining(final Long trainingId, final TrainingDto trainingDto) {
        if (trainingId == null || trainingDto == null) {
            throw new IllegalArgumentException("Training ID and DTO cannot be null");
        }

        //todo
    }
    @Override
    public List<Training> findTrainingsByUserId(final Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        //todo

        return List.of();
    }

}
