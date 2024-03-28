package com.athang.javatraining;

import com.athang.javatraining.jdbc.CRUDMySQL;

public class Main {
    public static void main(String[] args) {
        CRUDMySQL c = new CRUDMySQL();
        c.createTable();
        c.insertData();
        c.deleteData();
        //c.updateData();
    }
}
