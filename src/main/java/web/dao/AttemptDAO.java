package web.dao;

import web.models.Attempt;

import java.util.List;

public interface AttemptDAO {
    public List<Attempt> getAll();
    public void deleteAll();
    public void save(Attempt attempt);
    public Attempt get(long id);
    public boolean isEmpty();
}
