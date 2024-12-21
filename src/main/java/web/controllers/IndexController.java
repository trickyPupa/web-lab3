package web.controllers;

import lombok.extern.log4j.Log4j2;
import web.dao.PointDAO;
import web.models.Point;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Log4j2
@Named("indexController")
@ApplicationScoped
public class IndexController implements Serializable {
    @Inject
    private PointDAO dao;

    public List<Point> getList() {
        return dao.getAll();
    }

    public String getListToString() {
        return dao.getAll().toString();
    }

    public boolean isEmpty() {
        return dao.isEmpty();
    }
}
