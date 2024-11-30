package web.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.models.Attempt;
import web.services.AreaCheckService;
import web.services.StoreService;
import web.services.ValidationService;

import java.util.List;

@Named("resultController")
@ApplicationScoped
public class ResultController {
    private static final Logger logger = LogManager.getLogger(ResultController.class);

    @Getter
    @Setter
    private Attempt current = new Attempt();

    @Inject
    private StoreService storeService;

    @Inject
    private AreaCheckService areaCheckService;

//    @Inject
//    private ValidationService validationService;

    public String check(Attempt attempt) {
        try {
            current.setResult(areaCheckService.checkArea(current));

            storeService.save(current);

            current = new Attempt();
        }
        catch (Exception e) {
            ;
        }

        return "main?faces-redirect=true";
    }

    public List<Attempt> getList() {
        return storeService.getAll();
    }

    public void clear() {
        ;
    }

    public boolean isEmpty() {
        return true;
    }

    public void validate() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            var a = validator.validate(current);
        }
    }
}
