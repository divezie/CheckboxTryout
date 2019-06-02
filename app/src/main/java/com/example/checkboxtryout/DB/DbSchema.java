package com.example.checkboxtryout.DB;

import android.provider.BaseColumns;

public class DbSchema {
    private static final String TAG = "DbSchema";

    public static final String DATABASE_NAME = "checkbox.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SORT_ASC = " ASC";
    public static final String SORT_DESC = " DESC";
    public static final String[] ORDERS = {SORT_ASC,SORT_DESC};
    public static final int OFF = 0;
    public static final int ON = 1;

    public static final class Table_Checkbox_Item implements BaseColumns  { 
        // Table Name
        public static final String TABLE_NAME = "checkbox_item";

        // Table Columns
        public static final String COL_ID = "ID";
        public static final String COL_ACTION = "Action";

        // Create Table Statement
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS checkbox_item ( " + 
            COL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  " + 
            COL_ACTION + " TEXT );";

        // Drop table statement
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS checkbox_item;";

        // Columns list array
        public static final String[] allColumns = {
            COL_ID,
            COL_ACTION };
    }

}
