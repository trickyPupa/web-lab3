package web.containers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import web.models.Attempt;

import java.io.Serializable;
import java.util.List;

@Log4j2
@Named("attemptContainer")
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

    public List<Double> getXValues() {
        return Attempt.X_VALUES;
    }
}
