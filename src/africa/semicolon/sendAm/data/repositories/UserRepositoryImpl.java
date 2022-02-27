package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> userDb = new ArrayList<User>();

    @Override
    public User save(User newUser) {
       String email = newUser.getEmail();
       newUser.setEmail(email);
       userDb.add(newUser);
       return newUser;
    }

    @Override
    public User findByEmail(String email) {
        for (User newUser : userDb) {
            if (newUser.getEmail().equals(email)) return newUser;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDb;
    }

    @Override
    public void delete(User user) {
        userDb.remove(user);
    }

    @Override
    public void delete(String email) {
        User aUser = findByEmail(email);
        delete(aUser);
    }

    @Override
    public int count() {
        return userDb.size();
    }
}
