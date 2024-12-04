package web.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import web.models.Attempt;

import java.util.List;

@ApplicationScoped
public class BaseAttemptDAO implements AttemptDAO {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    @Override
    @Transactional
    public List<Attempt> getAll() {
        return em.createQuery("SELECT a FROM Attempt a order by id desc", Attempt.class)
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

    @Transactional
    public int getCount() {
        return em.createQuery("select count(a) from Attempt a", Number.class)
                .getSingleResult()
                .intValue();
    }

    @Transactional
    public List<Attempt> getList(int start, int count) {
        return em.createQuery("select a from Attempt a order by id desc", Attempt.class)
                .setFirstResult(start)
                .setMaxResults(count)
                .getResultList();
    }
}
