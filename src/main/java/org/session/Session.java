package org.session;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Session} provides a main API from the ORM. It uses {@link GenericJdbcDao} foe all
 * database operations. Session itself handles everything related to entities. It stores
 * all loaded entities in the map and acts as cache when the user is trying to load and entity.
 */
@RequiredArgsConstructor
public class Session {
    private final GenericJdbcDao genericJdbcDao;
    private final Map<EntityKey<?>, Object> entitiesMap = new HashMap<>();

    /**
     * Loads an entity from the database. If entity has been already loaded, then the cache
     * instance will be returned and no database call will be performed.
     *
     * @param type  entity type
     * @param id    entity identifier
     * @param <T>   generic entity type parameter
     * @return      loaded entity
     */
    public <T> T find(Class<T> type, Object id) {
        var entityKey = new EntityKey<>(type, id);
        var entity = entitiesMap.computeIfAbsent(entityKey, genericJdbcDao::load);
        return (T) entity;
    }
}
