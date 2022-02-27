package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User repository Can")
class UserRepositoryImplTest {
    private UserRepository userRepository;
    @BeforeEach
    void setUp(){userRepository = new UserRepositoryImpl();}

    @DisplayName("Save User details in A List")
    @Test void userRepositorySaveTest() {
        User newUser = new User();
        String email = "ibidapoazeez@gmail.com";
        newUser.setEmail(email);
        User savedUser = userRepository.save(newUser);
        assertEquals(email,savedUser.getEmail());
        assertEquals(1,userRepository.count());
    }

    @DisplayName("Find User By Email")
    @Test void findByEmail(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");

        saveThreeUsers(firstUser, secondUser, thirdUser);

        User foundUser = userRepository.findByEmail("ol@gmail.com");
        assertEquals(secondUser,foundUser);
        assertEquals("ol@gmail.com",secondUser.getEmail());

    }

    private void saveThreeUsers(User firstUser, User secondUser, User thirdUser) {
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);
    }

    @DisplayName("Delete User Using Email")
    @Test void deleteByEmailTest(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");
        saveThreeUsers(firstUser, secondUser, thirdUser);
        userRepository.delete("ola@gmail.com");
        assertEquals(2,userRepository.count());
    }
    @DisplayName("Find By Email After Deleting User")
    @Test void findByEmail_works_afterDeleteTest(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");
        saveThreeUsers(firstUser, secondUser, thirdUser);

        userRepository.delete("ol@gmail.com");

        User foundUser = userRepository.findByEmail("ol@gmail.com");
        assertNull(foundUser);
    }

    @DisplayName("Save After Delete And The Email Is Correct")
    @Test
    void saveAfterADelete_givesCorrectEmailTest(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");
        saveThreeUsers(firstUser, secondUser, thirdUser);
        userRepository.delete("ola@gmail.com");
        User newUser = userRepository.save(new User());
        assertNull(newUser.getEmail());
    }

    @DisplayName("Delete By User")
    @Test void deleteByUserTest(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");
        saveThreeUsers(firstUser, secondUser, thirdUser);

        userRepository.delete(firstUser);
        assertEquals(2,userRepository.count());
        assertNull(userRepository.findByEmail("ola@gmail.com"));
    }

    @DisplayName("Find All")
    @Test void findAllTest(){
        User firstUser = new User();
        firstUser.setEmail("ola@gmail.com");
        User secondUser = new User();
        secondUser.setEmail("ol@gmail.com");
        User thirdUser = new User();
        thirdUser.setEmail("Oll@gmail.com");
        saveThreeUsers(firstUser, secondUser, thirdUser);
        List<User> all = userRepository.findAll();
        assertEquals(3,all.size());
    }

}