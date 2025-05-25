package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.*;

@RestController
@RequestMapping("/v1/training")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto, @RequestParam Long userId) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Training training = trainingMapper.toEntity(trainingDto, user);
        Training createdTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(createdTraining, userMapper.toDto(user));
    }
}