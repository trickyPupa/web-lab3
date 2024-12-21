package web.dao;

import web.models.Point;

import java.util.List;

public interface PointDAO {
    public List<Point> getAll();
    public void deleteAll();
    public void save(Point point);
    public Point get(long id);
    public boolean isEmpty();
}
