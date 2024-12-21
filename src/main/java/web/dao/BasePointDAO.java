package web.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import web.models.Point;

import java.util.List;

@ApplicationScoped
public class BasePointDAO implements PointDAO {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    @Override
    @Transactional
    public List<Point> getAll() {
        return em.createQuery("FROM Point a order by id desc", Point.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Point get(long id) {
        return em.find(Point.class, id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM Point a")
                .executeUpdate();
    }

    @Override
    @Transactional
    public void save(Point point) {
        em.persist(point);
    }

    @Override
    @Transactional
    public boolean isEmpty() {
        return em.createQuery("SELECT EXISTS(a) FROM Point a", Boolean.class).getSingleResult();
    }
}
