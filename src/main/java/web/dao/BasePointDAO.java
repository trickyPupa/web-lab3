package web.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import web.models.Point;

import java.util.List;

@Singleton
public class BasePointDAO implements PointDAO {

    @PersistenceContext(unitName = "web3")
    private EntityManager em;

    private List<Point> pointList;

    @Override
    public List<Point> getAll() {
        return pointList;
    }

    @Override
    public Point get(long id) {
        return em.find(Point.class, id);
    }

    @Override
    public void deleteAll() {
        em.createQuery("DELETE FROM Point a")
                .executeUpdate();

        updateList();
    }

    @Override
    public void save(Point point) {
        em.persist(point);

        updateList();
    }

    @PostConstruct
    private void updateList() {
        pointList = em.createQuery("FROM Point a order by id desc", Point.class)
                .getResultList();
    }

    @Override
    public boolean isEmpty() {
        return pointList.isEmpty();
    }
}
