package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    @Override
    public void add(User user) {
        User merge = entityManager.merge(user);
        entityManager.persist(merge);
    }

    @Transactional
    @Override
    public void delete(User user) {
        User merge = entityManager.merge(user);
        entityManager.remove(merge);
    }
}
