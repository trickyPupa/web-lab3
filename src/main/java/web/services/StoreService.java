package web.services;

import jakarta.persistence.Entity;
import web.models.Attempt;

import java.util.List;

public interface StoreService {
    public List<Attempt> getAll();
    public void deleteAll();
    public void save(Attempt attempt);
}
