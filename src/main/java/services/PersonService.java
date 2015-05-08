package services;

import model.Person;
import dao.PersonDao;

import javax.ejb.*;
import javax.inject.Inject;

/**
 * Service for saving new person and editing person.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonService {

    @Inject
    private PersonDao store;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean savePerson(Person p) {
        store.addPerson(p);
        return true;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean editPerson(Person p) {
        store.editPerson(p);
        return true;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Person getPerson(int id) {
        return store.getPerson(id);
    }

}
