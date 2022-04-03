package org.session;

public record EntityKey<T>(Class<T> type, Object id) {

}
