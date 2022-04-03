package org.session;


import org.junit.Test;
import org.session.annotations.Table;
import org.session.entity.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SqlBuilderTest {
    private SqlBuilder sqlBuilder = new SqlBuilder();

    @Test
    public void createSelectByIdQuery() {
        var selectByIdQuery = sqlBuilder.createSelectByIdQuery(Order.class);

        assertThat(selectByIdQuery).isEqualTo("SELECT * FROM order WHERE id = ?;");
    }

    @Test
    public void createSelectByIdQueryWhenTableNameIsExplicitlySpecified() {
        var selectByIdQuery = sqlBuilder.createSelectByIdQuery(User.class);

        assertThat(selectByIdQuery).isEqualTo("SELECT * FROM users WHERE id = ?;");
    }

    public static class Order {

    }

    @Table("users")
    public static class User {

    }
}