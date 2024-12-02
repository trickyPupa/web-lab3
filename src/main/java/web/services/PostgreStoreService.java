package web.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import web.models.Attempt;

import java.util.List;

@ApplicationScoped
public class PostgreStoreService implements StoreService {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    @Override
    @Transactional
    public List<Attempt> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attempt> cq = cb.createQuery(Attempt.class);
        Root<Attempt> root = cq.from(Attempt.class);

        cq.select(root);

        return em.createQuery(cq).getResultList();
    }

    @Transactional
    public Attempt get(long id) {
        return em.find(Attempt.class, id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Attempt> cq = cb.createCriteriaDelete(Attempt.class);
        Root<Attempt> root = cq.from(Attempt.class);

        em.createQuery(cq).executeUpdate();
    }

    @Transactional
    public void save(Attempt attempt) {
        em.persist(attempt);
    }

    @Transactional
    public int getCount() {
        return 2;
    }

    @Transactional
    public List<Attempt> getAttemptsList(int start, int count) {
        return em.createQuery("select attempt from Attempt attempt", Attempt.class)
                .setFirstResult(start).setMaxResults(count).getResultList();
    }
}
