package web.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import web.models.Attempt;

import java.util.List;

@ApplicationScoped
public class BaseAttemptDAO implements AttemptDAO {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    @Override
    @Transactional
    public List<Attempt> getAll() {
        return em.createQuery("FROM Attempt a order by id desc", Attempt.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Attempt get(long id) {
        return em.find(Attempt.class, id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM Attempt a")
                .executeUpdate();
    }

    @Override
    @Transactional
    public void save(Attempt attempt) {
        em.persist(attempt);
    }

    @Override
    @Transactional
    public boolean isEmpty() {
        return em.createQuery("SELECT EXISTS(a) FROM Attempt a", Boolean.class).getSingleResult();
    }
}
