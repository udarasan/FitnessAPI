package com.perspicaz.jim.modules.user;

import com.perspicaz.jim.common.dtos.ResponseGetDto;
import com.perspicaz.jim.modules.user.dtos.UserReport;
import com.perspicaz.jim.modules.user.dtos.UserReportRequest;
import com.perspicaz.jim.modules.user.dtos.UserRequest;
import com.perspicaz.jim.modules.user.dtos.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("${spring.base-path}/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseGetDto<User> saveUser(@RequestBody UserRequest userRequest) {

        try {
            User savedUser = userService.saveUser(userRequest);
            ResponseGetDto<User> responseGetDto = new ResponseGetDto<User>();
            responseGetDto.setData(savedUser);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("User Saved");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<User> responseGetDto = new ResponseGetDto<User>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("User not saved");
            return responseGetDto;
        }

    }

    @GetMapping
    public ResponseGetDto< List<User>> getUser( ) {

        try {
            List<User> savedUser = userService.getAllUser();
            ResponseGetDto< List<User>> responseGetDto = new ResponseGetDto< List<User>>();
            responseGetDto.setData(savedUser);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("User get");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto< List<User>> responseGetDto = new ResponseGetDto< List<User>>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("User not get");
            return responseGetDto;
        }

    }

    @PatchMapping
    public ResponseGetDto<User> updateUser(@RequestBody UserUpdateDto updateRequest) {

        try {

            User savedUser = userService.updateUser(updateRequest);
            ResponseGetDto<User> responseGetDto = new ResponseGetDto<User>();
            responseGetDto.setData(savedUser);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("User updated");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<User> responseGetDto = new ResponseGetDto<User>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage("No User exists");
            return responseGetDto;
        }

    }

    @GetMapping("/id/{profileId}")
    public ResponseGetDto<  List<User> > getUserOfTrainer(@PathVariable String profileId)
    {
        try {

            List<User> users = userService.getAllUserOfTrainer(profileId);
            ResponseGetDto<  List<User> > responseGetDto = new ResponseGetDto<  List<User> >();
            responseGetDto.setData(users);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("Ipa retrieval successful");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<  List<User> > responseGetDto = new ResponseGetDto<  List<User> >();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage(e.getMessage());
            return responseGetDto;
        }

    }



    @GetMapping("/{profileId}")
    public ResponseGetDto< User > getSingleUser(@PathVariable String profileId)
    {
        try {

            User users = userService.getSingleUser(profileId);
            ResponseGetDto< User > responseGetDto = new ResponseGetDto< User>();
            responseGetDto.setData(users);
            responseGetDto.setStatus(200);
            responseGetDto.setMessage("User retrieval successful");
            return responseGetDto;

        } catch (Exception e) {
            ResponseGetDto<User > responseGetDto = new ResponseGetDto< User>();
            responseGetDto.setData(null);
            responseGetDto.setStatus(404);
            responseGetDto.setMessage(e.getMessage());
            return responseGetDto;
        }

    }

    @PostMapping("/report")
    public ResponseGetDto<List<UserReport>> getUserReport(@RequestBody UserReportRequest reportRequest) {
        List<UserReport> PurchaseList = userService.getReport(reportRequest);
        ResponseGetDto<List<UserReport>> responseGetDto = new ResponseGetDto<List<UserReport>>();
        responseGetDto.setData(PurchaseList);
        responseGetDto.setStatus(200);
        responseGetDto.setMessage("report List");
        return responseGetDto;
    }
}