package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.*;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        User user = userService.getUser(trainingDto.userId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.userId()));
        Training training = trainingMapper.toEntity(trainingDto, user);
        return trainingService.createTraining(training);
    }
}