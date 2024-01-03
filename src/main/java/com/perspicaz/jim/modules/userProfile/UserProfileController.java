package com.perspicaz.jim.modules.userProfile;



import com.perspicaz.jim.common.dtos.ResponseGetDto;
import com.perspicaz.jim.modules.user.dtos.UserReport;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.userProfile.dtos.LoginRequest;
import com.perspicaz.jim.modules.userProfile.dtos.SummaryMe;
import com.perspicaz.jim.modules.userProfile.dtos.UserProfileRequestDto;
import com.perspicaz.jim.modules.userProfile.dtos.UserProfileUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/userProfile")
public class UserProfileController {

    private final  UserProfileService userProfileService;


    @GetMapping
    public ResponseGetDto<List<UserProfile>> getAllUserProfile() {

        try {
            List<UserProfile> savedUserProfile = userProfileService.getAllUserProfiles();
            ResponseGetDto<List<UserProfile>> responseGetDto = new ResponseGetDto<List<UserProfile>>();
            responseGetDto.setData(savedUserProfile);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("UserProfile fetch success");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<List<UserProfile>> responseGetDto = new ResponseGetDto<List<UserProfile>>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("UserProfile fetch not success");
            return responseGetDto;
        }

    }

    @PostMapping
    public ResponseGetDto<UserProfile> saveUserProfile(@RequestBody UserProfileRequestDto userProfileRequest) {

        try {
            UserProfile savedUserProfile = userProfileService.saveUser(userProfileRequest);
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(savedUserProfile);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("UserProfile Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("UserProfile not saved");
            return responseGetDto;
        }

    }

    @PostMapping("/login")
    public ResponseGetDto<UserProfile> loginUser(@RequestBody LoginRequest loginRequest) {

        try {
            UserProfile savedUserProfile = userProfileService.userLogin(loginRequest);
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(savedUserProfile);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("User login success");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("User login failed");
            return responseGetDto;
        }

    }

    @PatchMapping
    public ResponseGetDto<UserProfile> updateUserProfile(@RequestBody UserProfileUpdateDto updateRequest) {

        try {

            UserProfile savedUserProfile = userProfileService.updateUserProfile(updateRequest);
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(savedUserProfile);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("UserProfile updated");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<UserProfile> responseGetDto = new ResponseGetDto<UserProfile>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("No UserProfile exists");
            return responseGetDto;
        }

    }

    @GetMapping("/summary")
    public ResponseGetDto<SummaryMe> getSummary( ) {
        SummaryMe PurchaseList = userProfileService.getSummary();
        ResponseGetDto<SummaryMe> responseGetDto = new ResponseGetDto<SummaryMe>();
        responseGetDto.setData(PurchaseList);
        responseGetDto.setStatus(200);
        responseGetDto.setMessage("summary List");
        return responseGetDto;
    }

    @PostMapping("/report")
    public ResponseGetDto<List<UserProfile>> getUserReport(@RequestBody UserReportRequest reportRequest) {
        List<UserProfile> PurchaseList = userProfileService.getReport(reportRequest);
        ResponseGetDto<List<UserProfile>> responseGetDto = new ResponseGetDto<List<UserProfile>>();
        responseGetDto.setData(PurchaseList);
        responseGetDto.setStatus(200);
        responseGetDto.setMessage("report List");
        return responseGetDto;
    }
}