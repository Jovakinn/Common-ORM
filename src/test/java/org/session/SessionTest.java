package org.session;

import org.junit.Test;
import org.session.entity.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.session.factory.PersonFactory.*;

public class SessionTest {
    private final GenericJdbcDao genericJdbcDao = mock(GenericJdbcDao.class);
    private final Session session = new Session(genericJdbcDao);

    @Test
    public void findLoadsEntityFromTheDbById() {
        var person = givenDefaultPersonIsStoredInTheDb();

        var foundPerson = session.find(Person.class, DEFAULT_ID);

        assertThat(foundPerson).isEqualTo(person);
    }

    @Test
    public void findReturnsEntityFromCacheWhenItIsLoaded() {
        givenDefaultPersonIsStoredInTheDb();

        var person1 = session.find(Person.class, DEFAULT_ID);
        var person2 = session.find(Person.class, DEFAULT_ID);

        assertThat(person1).isSameAs(person2);
    }

    @Test
    public void findDoesNotCallDbWhenEntityIsAlreadyLoaded() {
        givenDefaultPersonIsStoredInTheDb();

        var person1 = session.find(Person.class, DEFAULT_ID);
        var person2 = session.find(Person.class, DEFAULT_ID);

        verify(genericJdbcDao, times(1)).load(DEFAULT_ENTITY_KEY);
    }

    private Person givenDefaultPersonIsStoredInTheDb() {
        var person = newDefaultPerson();
        when(genericJdbcDao.load(DEFAULT_ENTITY_KEY)).thenAnswer(invocation -> newDefaultPerson());
        return person;
    }
}