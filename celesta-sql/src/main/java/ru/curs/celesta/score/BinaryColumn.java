package ru.curs.celesta.score;

import ru.curs.celesta.dbutils.BLOB;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Binary column (IMAGE or BLOB type).
 */
public final class BinaryColumn extends Column {

    /**
     * Celesta type of the column data.
     */
    public static final String CELESTA_TYPE = "BLOB";
    private String defaultvalue;

    public BinaryColumn(TableElement table, String name) throws ParseException {
        super(table, name);
    }

    @Override
    protected void setDefault(String lexvalue) {
        defaultvalue = lexvalue;
    }

    @Override
    public String getDefaultValue() {
        return defaultvalue;
    }

    @Override
    public String jdbcGetterName() {
        return "getBlob";
    }

    @Override
    void save(PrintWriter bw) throws IOException {
        super.save(bw);
        bw.write(" BLOB");
        if (!isNullable()) {
            bw.write(" NOT NULL");
        }
        String defaultVal = getDefaultValue();
        if (defaultVal != null) {
            bw.write(" DEFAULT ");
            bw.write(defaultVal);
        }
    }

    @Override
    public String getCelestaType() {
        return CELESTA_TYPE;
    }

    @Override
    public Class<?> getJavaClass() {
        return BLOB.class;
    }

    @Override
    public String getCelestaDefault() {
        return defaultvalue;
    }
}
