package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.SendAmAppException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test void afterRegister_repositoryContainsOneElement(){
        RegisterUserRequest registerForm = createRegisterForm();
        //when
        userService.register(registerForm);
        //assert
        assertEquals(1,userService.getRepository().count());
    }

    private RegisterUserRequest createRegisterForm() {
        RegisterUserRequest registerForm = new RegisterUserRequest();
        registerForm.setFirstName("Lotachi");
        registerForm.setLastName("Senior Dave");
        registerForm.setEmailAddress("seniordevLota@gmail.com");
        registerForm.setAddress("Code Cold Hell");
        registerForm.setPhoneNumber("2MillionDollars");
        return registerForm;
    }
       @Test void duplicateEmail_throwsExceptionTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        //when
        userService.register(lotaForm);
        //assert
        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));

    }

    @Test void duplicateEmailWithDifferentCase_throwsException(){
        RegisterUserRequest lotaForm = createRegisterForm();

        userService.register(lotaForm);
        lotaForm.setEmailAddress("seniordevLota@gmail.com");
        //assert
        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));
        assertThrows(RegisterFailureException.class,()-> userService.register(lotaForm));

    }

    @Test void registrationReturnsCorrectResponseTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        RegisterUserResponse response = userService.register(lotaForm);
        assertEquals("Lotachi Senior Dave",response.getFullName());
        assertEquals("seniordevlota@gmail.com",response.getEmail());
    }

    @Test void findRegisterUserByEmailTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);
       FindUserResponse result =  userService.findUserByEmail(lotaForm.getEmailAddress().toLowerCase());
        assertEquals("Lotachi Senior Dave",result.getFullName());

       assertEquals("seniordevlota@gmail.com",result.getEmail());
    }

    @Test
    public void findingUnregisteredEmail_throwsException(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);
       assertThrows(UserNotFoundException.class,()-> userService.findUserByEmail("omma@gmail.com"));
    }

    @Test
    public void findByUserEmailIsNotCaseSensitive(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);
        //when
        FindUserResponse result =  userService.findUserByEmail("seniorDevLota@gmail.com");
        //assert
        assertEquals("Lotachi Senior Dave",result.getFullName());
        assertEquals("seniordevlota@gmail.com",result.getEmail());
    }


}