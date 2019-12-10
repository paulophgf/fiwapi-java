package br.com.phgf.fiwapi.enumeration;

import br.com.phgf.fiwapi.system.FiwapiException;

public enum EnumTable {

    FILTER("filter"),
    NAT("nat"),
    MANGLE("mangle"),
    RAW("raw"),
    SECURITY("security");

    private String value;


    private EnumTable(String value) {
        this.value = value;
    }


    public static EnumTable get(String tableName) {
        EnumTable table;
        try {
            if (tableName == null) {
                throw new FiwapiException("The table NULL not exists");
            }
            table = EnumTable.valueOf(tableName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FiwapiException("The table " + tableName + " not exists");
        }
        return table;
    }

    public String getValue() {
        return value;
    }

}
