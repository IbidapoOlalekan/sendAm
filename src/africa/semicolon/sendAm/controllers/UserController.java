package africa.semicolon.sendAm.controllers;

import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.services.UserService;
import africa.semicolon.sendAm.services.UserServiceImpl;

public class UserController {
    private UserService userService = new UserServiceImpl();
    public RegisterUserResponse registerNewUser(RegisterUserRequest request){
        return userService.register(request);
    }

    public FindUserResponse getUserByEmail(String email){
        return userService.findUserByEmail(email);
    }

}