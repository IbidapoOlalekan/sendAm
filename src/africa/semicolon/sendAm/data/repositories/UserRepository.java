package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;

import java.util.List;

public interface UserRepository {
    User save(User newUser);
    User findByEmail(String email);
    List<User> findAll();
    void delete(User user);
    void delete(String email);

    int count();
}
