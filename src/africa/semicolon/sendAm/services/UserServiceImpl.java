package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.data.repositories.UserRepository;
import africa.semicolon.sendAm.data.repositories.UserRepositoryImpl;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository = new UserRepositoryImpl();
    @Override
    public RegisterUserResponse register(RegisterUserRequest requestForm) {
        requestForm.setEmailAddress(requestForm.getEmailAddress().toLowerCase());
        if(emailExists(requestForm.getEmailAddress())) throw  new RegisterFailureException("Email is not used");
        User user = new User();
        user.setEmail(requestForm.getEmailAddress());
        user.setAddress(requestForm.getAddress());
        user.setFullName(requestForm.getFirstName() + " " + requestForm.getLastName());
        user.setPhoneNumber(requestForm.getPhoneNumber());

        User saveUser = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(saveUser.getEmail());
        response.setFullName(saveUser.getFullName());

        return response;
    }

    private boolean emailExists(String emailAddress) {
        User user = userRepository.findByEmail(emailAddress);
        return user != null;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }
}
