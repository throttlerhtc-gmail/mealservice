package ru.shlanlinec.graduation.mealservice.util;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;

public class TriggerH implements Trigger {
    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
//      no need
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        throw new SQLException("feEFFEFEF");
    }

    @Override
    public void close() throws SQLException {
//      no need
    }

    @Override
    public void remove() throws SQLException {
//      no need
    }
}
