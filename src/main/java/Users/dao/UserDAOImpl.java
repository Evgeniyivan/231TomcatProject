package Users.dao;

import Users.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {
//
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> allUsers() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("FROM User").list();
//    }
//
//    @Override
//    public void add(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(user);
//    }
//
//    @Override
//    public void delete(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(user);
//    }
//
//    @Override
//    public void edit(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
//    }
//
//    @Override
//    public User getById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, id);
//    }
@PersistenceContext
private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void edit(int id,User user) {
        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setEmail(user.getEmail());

    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(User.class, id));
    }


}



