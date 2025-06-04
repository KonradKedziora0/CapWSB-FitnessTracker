package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.Date;
import java.util.List;

/**
 * TrainingController handles HTTP requests related to training operations.
 * It provides endpoints for creating, updating, and retrieving trainings,
 * as well as fetching trainings by activity type.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingService trainingService;
    private final TrainingProvider trainingProvider;
    private final TrainingMapper trainingMapper;
    private final UserMapper userMapper;
    private final UserProvider userProvider;

    /**
     * Creates a new training.
     *
     * @param trainingDto the DTO containing training details
     * @return a TrainingWithUserDto containing the created training details and associated user information
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingWithUserDto addTraining(@RequestBody TrainingDto trainingDto) {

        User user = userProvider.getUser(trainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.getUserId()));
        UserDto userDto = userMapper.toDto(user);
        Training createdTraining = trainingService.createTraining(trainingDto);
        return new TrainingWithUserDto(
                trainingMapper.toDto(createdTraining),
                userDto
        );
    }

    /**
     * Updates a training by its ID.
     *
     * @param trainingId the ID of the training to update
     * @param trainingDto the DTO containing updated training details
     * @return a TrainingWithUserDto containing the updated training details and associated user information
     */
    @PutMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingWithUserDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(trainingId, trainingDto)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
        User user = userProvider.getUser(updatedTraining.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(updatedTraining.getUser().getId()));
        UserDto userDto = userMapper.toDto(user);
        return new TrainingWithUserDto(
                trainingMapper.toDto(updatedTraining),
                userDto
        );
    }

    /**
     * Retrieves trainings for  a specific activity type.
     *
     * @param activityType
     * @return a list of TrainingWithUserDto containing training details and associated user information
     */
    @GetMapping("/activityType")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingWithUserDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        if (activityType == null) {
            throw new IllegalArgumentException("Activity type cannot be null");
        }
        List<Training> trainings = trainingProvider.findTrainingsByActivityType(activityType);
        return trainings.stream()
                .map(training -> {
                    User user = userProvider.getUser(training.getUser().getId())
                            .orElseThrow(() -> new UserNotFoundException(training.getUser().getId()));
                    UserDto userDto = userMapper.toDto(user);
                    return new TrainingWithUserDto(
                            trainingMapper.toDto(training),
                            userDto
                    );
                })
                .toList();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingWithUserDto> getAllTrainings() {
        List<Training> trainings = trainingProvider.findAllTrainings();
        return trainings.stream()
                .map(training -> {
                    User user = userProvider.getUser(training.getUser().getId())
                            .orElseThrow(() -> new UserNotFoundException(training.getUser().getId()));
                    UserDto userDto = userMapper.toDto(user);
                    return new TrainingWithUserDto(trainingMapper.toDto(training), userDto);
                })
                .toList();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingWithUserDto> getTrainingsByUserId(@PathVariable Long userId) {
        List<Training> trainings = trainingProvider.findTrainingsByUserId(userId);
        return trainings.stream()
                .map(training -> {
                    User user = userProvider.getUser(training.getUser().getId())
                            .orElseThrow(() -> new UserNotFoundException(training.getUser().getId()));
                    UserDto userDto = userMapper.toDto(user);
                    return new TrainingWithUserDto(trainingMapper.toDto(training), userDto);
                })
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingWithUserDto> getFinishedTrainingsAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date afterTime) {
        List<Training> trainings = trainingProvider.findFinishedTrainingsAfter(afterTime);
        return trainings.stream()
                .map(training -> {
                    User user = userProvider.getUser(training.getUser().getId())
                            .orElseThrow(() -> new UserNotFoundException(training.getUser().getId()));
                    UserDto userDto = userMapper.toDto(user);
                    return new TrainingWithUserDto(trainingMapper.toDto(training), userDto);
                })
                .toList();
    }

}