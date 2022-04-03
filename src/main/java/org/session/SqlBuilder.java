package org.session;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.session.annotations.Table;

@Log
public class SqlBuilder {
    private static final String SELECT_FROM_TABLE_BY_ID_PARAM = "SELECT * FROM %s WHERE id = ?;";

    public String createSelectByIdQuery(Class<?> entityType) {
        String tableName = resolveTableName(entityType);
        return String.format(SELECT_FROM_TABLE_BY_ID_PARAM, tableName);
    }

    private String resolveTableName(Class<?> entityType) {
        log.info("Resolving table name for entity " + entityType);
        var tableAnnotation = entityType.getDeclaredAnnotation(Table.class);
        if (tableAnnotation == null){
            log.info("No table annotation found for entity " + entityType);
            return entityType.getSimpleName().toLowerCase();
        }
        else return tableAnnotation.value();
    }
}
