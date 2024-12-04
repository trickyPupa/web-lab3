package web.containers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.experimental.Delegate;
import web.models.Attempt;

import java.io.Serializable;

@Named
@SessionScoped
public class AttemptContainer implements Serializable {
    @Delegate
    private Attempt current = new Attempt();

    public Attempt get() {
        return current;
    }

    public void reset() {
        this.current = new Attempt();
    }
}
