package com.pojo;

/**
 * @author Chris.Ge
 */
public class Database {
    private final String ds;
    private final int num;

    public Database(String ds, int num) {
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
