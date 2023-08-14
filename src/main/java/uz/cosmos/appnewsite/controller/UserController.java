package uz.cosmos.appnewsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cosmos.appnewsite.payload.ApiResponse;
import uz.cosmos.appnewsite.payload.RegisterDto;
import uz.cosmos.appnewsite.payload.UserDto;
import uz.cosmos.appnewsite.service.AuthService;
import uz.cosmos.appnewsite.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
