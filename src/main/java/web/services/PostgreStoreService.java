package web.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import web.models.Attempt;

import java.util.List;

public class PostgreStoreService {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    @Transactional
    public List<Attempt> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attempt> cq = cb.createQuery(Attempt.class);
        Root<Attempt> root = cq.from(Attempt.class);

        cq.select(root);

        return em.createQuery(cq).getResultList();
    }

    @Transactional
    public Attempt get(int id) {
        return em.find(Attempt.class, id);
    }

    @Transactional
    public void deleteAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Attempt> cq = cb.createCriteriaDelete(Attempt.class);
        Root<Attempt> root = cq.from(Attempt.class);

        em.createQuery(cq).executeUpdate();
    }
}
