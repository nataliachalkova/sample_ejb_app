package services;

import com.google.common.base.Strings;
import dao.PersonDao;
import model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Service for validation person and person id
 */
@Stateless
public class PersonValidationService {

    @Inject
    private PersonDao store;

    public boolean validate(Person p) {
        if (p == null) return false;
        if (Strings.isNullOrEmpty(p.getName()) || Strings.isNullOrEmpty(p.getSurname()) || Strings.isNullOrEmpty(p.getPatronymic())
                || Strings.isNullOrEmpty(p.getDateOfBirth())) {
            return false;
        }
        return true;
    }

    public boolean validate(String id) {
        if (Strings.isNullOrEmpty(id) || Strings.isNullOrEmpty(id.trim())) return false;
        try {
            int i = Integer.parseInt(id);
            if (i < 0 || i > store.size() - 1) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
