package org.session.factory;

import org.session.EntityKey;
import org.session.entity.Person;

public class PersonFactory {
    public static final Long DEFAULT_ID = 123L;
    public static final String DEFAULT_FIRST_NAME = "Max";
    public static final String DEFAULT_LAST_NAME = "Khodakov";
    public static final EntityKey<Person> DEFAULT_ENTITY_KEY = new EntityKey(Person.class, DEFAULT_ID);

    public static Person newDefaultPerson() {
        var person = new Person();
        person.setId(123L);
        person.setFirstName("Max");
        person.setLastName("Khodakov");
        return person;
    }
}
