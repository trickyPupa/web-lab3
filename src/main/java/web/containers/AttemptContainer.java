package web.containers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
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
