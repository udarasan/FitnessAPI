package com.perspicaz.jim.modules.trainer;

import com.perspicaz.jim.common.dtos.ResponseGetDto;
import com.perspicaz.jim.modules.trainer.dtos.TrainerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

 
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    public ResponseGetDto<Trainer> saveTrainer(@RequestBody TrainerRequest trainerRequest) {

        try {
            Trainer savedTrainer = trainerService.saveTrainer(trainerRequest);
            ResponseGetDto<Trainer> responseGetDto = new ResponseGetDto<Trainer>();
            responseGetDto.setData(savedTrainer);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("Trainer Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<Trainer> responseGetDto = new ResponseGetDto<Trainer>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("Trainer not saved");
            return responseGetDto;
        }

    }

    @GetMapping
    public ResponseGetDto<Trainer> getTrainer(Long id) {

        try {
            Trainer savedTrainer = trainerService.getTrainer(id);
            ResponseGetDto<Trainer> responseGetDto = new ResponseGetDto<Trainer>();
            responseGetDto.setData(savedTrainer);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("Trainer Get Success");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<Trainer> responseGetDto = new ResponseGetDto<Trainer>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("Trainer Get Failed");
            return responseGetDto;
        }

    }


}