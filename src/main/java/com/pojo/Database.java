package com.pojo;

import javax.sql.DataSource;

/**
 * @author Chris.Ge
 */
public class Database {
    private final DataSource ds;
    private final int num;

    public Database(DataSource ds, int num) {
        this.ds = ds;
        this.num = num;
    }

    public int getBigNum(int num) {
        return num + 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Database{");
        sb.append("ds=").append(ds);
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }
}
