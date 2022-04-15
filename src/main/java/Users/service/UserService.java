package Users.service;

import Users.model.User;

import java.util.List;


public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(User User);
    void edit(User User);
    User getById(int id);
}
