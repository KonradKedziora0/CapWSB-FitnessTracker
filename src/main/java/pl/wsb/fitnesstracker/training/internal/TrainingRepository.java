package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Query searching all trainings of specific user.
     *
     * @param userId The ID of the user whose trainings are to be retrieved
     * @return A list of all trainings
     */
    default List<Training> findTrainingsByUserId(Long userId) {
        // todo implement this method
        return List.of();
    }

    /**
     * Query searching all trainings by activity type.
     *
     * @param activityType The activity type to filter trainings
     * @return A list of trainings matching the specified activity type
     */
    default List<Training> findTrainingsByActivityType(ActivityType activityType){
        return findAll().stream()
                .filter(training -> training.getActivityType() == activityType)
                .toList();
    }

}
