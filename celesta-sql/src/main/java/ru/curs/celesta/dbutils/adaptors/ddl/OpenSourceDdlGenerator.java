package ru.curs.celesta.dbutils.adaptors.ddl;

import ru.curs.celesta.dbutils.adaptors.DBAdaptor;

import static ru.curs.celesta.dbutils.adaptors.constants.CommonConstants.*;
import static ru.curs.celesta.dbutils.adaptors.constants.OpenSourceConstants.*;

import ru.curs.celesta.dbutils.adaptors.column.ColumnDefinerFactory;
import ru.curs.celesta.dbutils.meta.DbColumnInfo;
import ru.curs.celesta.dbutils.meta.DbIndexInfo;
import ru.curs.celesta.event.TriggerQuery;
import ru.curs.celesta.score.*;

import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class OpenSourceDdlGenerator extends DdlGenerator {

    public OpenSourceDdlGenerator(DBAdaptor dmlAdaptor) {
        super(dmlAdaptor);
    }

    @Override
    List<String> dropIndex(Grain g, DbIndexInfo dBIndexInfo) {
        String sql = dropIndex(g.getName(), dBIndexInfo.getIndexName());
        String sql2 = dropIndex(
                g.getName(),
                dBIndexInfo.getIndexName() + CONJUGATE_INDEX_POSTFIX
        );

        return Arrays.asList(sql, sql2);
    }

    @Override
    String dropTriggerSql(TriggerQuery query) {
        String sql = String.format(
                "DROP TRIGGER \"%s\" ON %s",
                query.getName(), tableString(query.getSchema(), query.getTableName())
        );
        return sql;
    }

    @Override
    List<String> updateColumn(Connection conn, Column c, DbColumnInfo actual) {
        List<String> result = new LinkedList<>();
        // Начинаем с удаления default-значения
        String sql = String.format(
                ALTER_TABLE + tableString(c.getParentTable().getGrain().getName(), c.getParentTable().getName())
                        + " ALTER COLUMN \"%s\" DROP DEFAULT", c.getName()
        );
        result.add(sql);

        updateColType(c, actual, result);

        // Проверяем nullability
        if (c.isNullable() != actual.isNullable()) {
            sql = String.format(
                    ALTER_TABLE + tableString(c.getParentTable().getGrain().getName(), c.getParentTable().getName())
                            + " ALTER COLUMN \"%s\" %s", c.getName(), c.isNullable() ? "DROP NOT NULL" : "SET NOT NULL");
            result.add(sql);
        }

        // Если в данных пустой default, а в метаданных -- не пустой -- то
        if (c.getDefaultValue() != null || (c instanceof DateTimeColumn && ((DateTimeColumn) c).isGetdate())
                || (c instanceof IntegerColumn && ((IntegerColumn) c).getSequence() != null)) {
            sql = String.format(
                    ALTER_TABLE + tableString(c.getParentTable().getGrain().getName(), c.getParentTable().getName())
                            + " ALTER COLUMN \"%s\" SET %s",
                    c.getName(), ColumnDefinerFactory.getColumnDefiner(getType(), c.getClass()).getDefaultDefinition(c));
            result.add(sql);
        }

        return result;
    }

    @Override
    Optional<String> dropAutoIncrement(Connection conn, TableElement t) {
        String sql = String.format("drop sequence if exists \"%s\".\"%s_seq\"", t.getGrain().getName(), t.getName());
        return Optional.of(sql);
    }

    abstract void updateColType(Column c, DbColumnInfo actual, List<String> batch);

    private String dropIndex(String schemaName, String indexName) {
        return String.format(
                "DROP INDEX IF EXISTS %s", tableString(schemaName, indexName)
        );
    }
}
