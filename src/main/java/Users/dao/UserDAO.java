package Users.dao;

import Users.model.User;



import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void add(User user);
    void edit(int id,User user);
    User getById(int id);
    void delete(int id);
}