package com.perspicaz.jim.modules.userProfile;


import com.perspicaz.jim.common.enums.UserRole;
import com.perspicaz.jim.common.exceptions.AlreadyExists;
import com.perspicaz.jim.modules.trainer.Trainer;
import com.perspicaz.jim.modules.trainer.TrainerRepository;
import com.perspicaz.jim.modules.user.User;
import com.perspicaz.jim.modules.user.UserRepository;
import com.perspicaz.jim.modules.user.dtos.UserReport;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.user.embeddable.UserWorkout;
import com.perspicaz.jim.modules.userProfile.dtos.LoginRequest;
import com.perspicaz.jim.modules.userProfile.dtos.SummaryMe;
import com.perspicaz.jim.modules.userProfile.dtos.UserProfileRequestDto;
import com.perspicaz.jim.modules.userProfile.dtos.UserProfileUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private  final UserRepository userRepository;
    private  final TrainerRepository trainerRepository;

    public UserProfile getUserProfile(Long id) throws AlreadyExists, Exception {
        UserProfile userProfile =  userProfileRepository.findById(id).get();
        return  userProfile;
    }

    public UserProfile saveUser(UserProfileRequestDto userProfileRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userProfileRequest.getEmail());
        userProfile.setName(userProfileRequest.getName());
        userProfile.setPassword(userProfileRequest.getPassword());
        userProfile.setRole(userProfileRequest.getRole());
        userProfile.setAddedDate(LocalDate.now());

        UserProfile profile =  userProfileRepository.save(userProfile);

        if(userProfileRequest.getRole()==UserRole.USER){
            User user = new User();
            user.setProfile(profile);
            userRepository.save(user);
        }else if(userProfileRequest.getRole()==UserRole.TRAINER){
            Trainer trainer = new Trainer();
            trainer.setProfile(profile);
            trainerRepository.save(trainer);
        }
        return profile;
    }

    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> userProfileList = new ArrayList<UserProfile>();
        userProfileRepository.findAll().forEach(userProfileList::add);
        return userProfileList;
    }


    public UserProfile userLogin(LoginRequest request){
        UserProfile profile = userProfileRepository.findOneByEmailAndPassword(request.getEmail(),request.getPassword()) ;
        return  profile;
    }

    public UserProfile updateUserProfile(UserProfileUpdateDto updateRequest) {
        UserProfile userProfileOri =  userProfileRepository.findById(updateRequest.getId()).get();
        userProfileOri.setName(updateRequest.getName()!=null?updateRequest.getName():userProfileOri.getName());
        userProfileOri.setEmail(updateRequest.getEmail()!=null?updateRequest.getEmail():userProfileOri.getEmail());
        userProfileOri.setPassword(updateRequest.getPassword()!=null?updateRequest.getPassword():userProfileOri.getPassword());
        return userProfileRepository.save(userProfileOri);
    }

    public SummaryMe getSummary() {
        SummaryMe summaryMe = new SummaryMe();
        List<UserProfile> userProfileList = new ArrayList<UserProfile>();
        userProfileRepository.findAll().forEach(userProfileList::add);
        double userCount = userProfileList.stream()
                .filter(userProfile -> UserRole.USER.equals(userProfile.getRole()))
                .count();

        double trainerCount = userProfileList.stream()
                .filter(userProfile -> UserRole.TRAINER.equals(userProfile.getRole()))
                .count();

        summaryMe.setUsers(userCount);
        summaryMe.setTraines(trainerCount);

        return  summaryMe;
    }

    public List<UserProfile> getReport(UserReportRequest reportRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<UserProfile> profileList = (List<UserProfile>) userProfileRepository.findAll();

        LocalDate startDate =LocalDate.parse(reportRequest.getStart(),formatter);
        LocalDate endDate =LocalDate.parse(reportRequest.getEnd(),formatter);


        List<UserProfile> profileListRange = profileList.stream()
                .filter(workout -> workout.getAddedDate().compareTo(startDate) >= 0 &&
                        workout.getAddedDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());


        return profileListRange;

    }
}
