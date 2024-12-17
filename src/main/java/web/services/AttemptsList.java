package web.services;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import web.dao.BaseAttemptDAO;
import web.models.Attempt;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Log4j2
@Named("attemptsList")
@ApplicationScoped
public class AttemptsList extends LazyDataModel<Attempt> implements Serializable {
    @Inject
    private BaseAttemptDAO service;

    @Override
    public List<Attempt> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters) {
        List<Attempt> attempts = service.getList(first, pageSize);
        setRowCount(service.getCount());
        return attempts;
    }
}