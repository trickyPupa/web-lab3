package web.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Setter
@Getter
@Log4j2
@RequestScoped
@Named("response")
public class ResponseManager {
    private boolean redirect = true;

    public String mainPageResponse() {
        if (redirect) {
            return "main?faces-redirect=true";
        }
        return "main";
    }
}
