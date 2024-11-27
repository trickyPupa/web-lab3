package web.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import web.services.AreaCheckService;
import web.services.StoreService;

@Named("pointController")
@ApplicationScoped
public class PointController {

    @Inject
    private StoreService storeService;

    @Inject
    private AreaCheckService areaCheckService;

}
