package Users.service;

import Users.dao.UserDAO;
import Users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserDAO userDAO;

    @Transactional
    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void delete(int id) {userDAO.delete(id);}

    @Transactional
    @Override
    public void edit(int id, User user) {
        userDAO.edit(id,user);
    }

    @Transactional
    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

}
