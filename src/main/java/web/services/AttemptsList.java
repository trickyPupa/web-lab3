package web.services;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import web.dao.BaseAttemptDAO;
import web.models.Attempt;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("attemptsList")
@ApplicationScoped
public class AttemptsList extends LazyDataModel<Attempt> implements Serializable {
    @Inject
    private BaseAttemptDAO service;

    @Override
    public int count(Map<String, FilterMeta> map) {
        return service.getCount();
    }

    @Override
    public List<Attempt> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
        return service.getList(first, pageSize);
    }
}