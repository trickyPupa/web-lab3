package web.controllers;

import lombok.extern.log4j.Log4j2;
import web.dao.AttemptDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Log4j2
@Named("removalController")
@ApplicationScoped
public class RemovalController implements Serializable {
    @Inject
    private AttemptDAO dao;

    public void clear() {
        dao.deleteAll();
        log.info("All attempts have been deleted");
    }
}
