package dao;

import model.Person;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains Person records list.
 * Index in array is a person id.
 */
@Singleton
public class PersonDao {
    private List<Person> list = new ArrayList<>();

    @Lock(LockType.WRITE)
    public void addPerson(Person p) {
        list.add(p);
    }

    @Lock(LockType.READ)
    public Person getPerson(int index) {
        Person p = list.get(index);
        p.setId(index);
        return p;
    }

    @Lock(LockType.WRITE)
    public void editPerson(Person p) {
        list.set(p.getId(), p);
    }

    @Lock(LockType.WRITE)
    public int size() {
        return list.size();
    }
}
