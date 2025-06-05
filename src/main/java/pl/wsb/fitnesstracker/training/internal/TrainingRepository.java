package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Query searching all trainings of specific user.
     *
     * @param userId The ID of the user whose trainings are to be retrieved
     * @return A list of all trainings
     */
    default List<Training> findTrainingsByUserId(Long userId) {
        if (userId != null) {
            return findAll().stream()
                    .filter(training -> training.getUser() != null && training.getUser().getId().equals(userId))
                    .toList();
        }
        throw new IllegalArgumentException("User ID cannot be null");
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

    /**
     * Query searching all trainings finished after the specified time.
     *
     * @param afterTime The time after which the trainings should be finished
     * @return A list of trainings that are finished after the specified time
     */
    default List<Training> findAllByEndTimeAfter(Date afterTime) {
        if (afterTime != null) {
            return findAll().stream()
                    .filter(training -> training.getEndTime() != null && training.getEndTime().after(afterTime))
                    .toList();
        }
        throw new IllegalArgumentException("Date cannot be null");
    };

}
