package web.services;

import web.models.Attempt;

public interface ValidationService {
    public boolean isValid(Attempt attempt);
}
