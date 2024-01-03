package com.perspicaz.jim.modules.trainer;

import com.perspicaz.jim.common.enums.UserRole;
import com.perspicaz.jim.modules.trainer.dtos.TrainerRequest;
import com.perspicaz.jim.modules.user.User;
import com.perspicaz.jim.modules.user.UserRepository;
import com.perspicaz.jim.modules.user.dtos.UserRequest;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import com.perspicaz.jim.modules.userProfile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserProfileRepository userProfileRepository;

    public Trainer saveTrainer(TrainerRequest userRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userRequest.getEmail());
        userProfile.setName(userRequest.getName());
        userProfile.setPassword(userRequest.getPassword());
        userProfile.setRole(UserRole.USER);

        UserProfile profile= userProfileRepository.save(userProfile);

        Trainer trainer = new Trainer();
        trainer.setProfile(profile);
        return  trainerRepository.save(trainer);

    }

    public Trainer getTrainer(Long id)   {
        Trainer trainer = trainerRepository.findById(id).get();
        return trainer;
    }


}
