package com.example.checkboxtryout.DB;

import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class Checkbox_ItemDao extends DbManager {
    private static final String TAG = "Checkbox_ItemDao";

    protected static SQLiteDatabase database;
    protected static DbManager mDbManager;
    protected static  String[] allColumns = DbSchema.Table_Checkbox_Item.allColumns;


    protected Checkbox_ItemDao() {
    }

    protected static void database_open() throws SQLException {
        mDbManager = DbManager.getsInstance();
        database = mDbManager.getDatabase();
    }

    protected static void database_close() {
        mDbManager = DbManager.getsInstance();
        mDbManager.close();
    }

    public static Checkbox_Item loadRecordById(int mID)  { 
        database_open();
        Cursor cursor = database.query(DbSchema.Table_Checkbox_Item.TABLE_NAME,allColumns,  "ID = ?" , new String[] { String.valueOf(mID) } , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Checkbox_Item checkbox_item = new Checkbox_Item();
        checkbox_item = cursorToCheckbox_Item(cursor);

        cursor.close();
        database_close();

        return checkbox_item;
    }

    public static ArrayList<Checkbox_Item> loadAllRecords() {
        ArrayList<Checkbox_Item> checkbox_itemList = new ArrayList<Checkbox_Item>();
        database_open();

        Cursor cursor = database.query(
                DbSchema.Table_Checkbox_Item.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Checkbox_Item checkbox_item = cursorToCheckbox_Item(cursor);
            checkbox_itemList.add(checkbox_item);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return checkbox_itemList;
    }

    // Please always use the typed column names (Table_Checkbox_Item) when passing arguments.
    // Example: Table_Checkbox_Item.Column_Name
    public static ArrayList<Checkbox_Item> loadAllRecords(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        ArrayList<Checkbox_Item> checkbox_itemList = new ArrayList<Checkbox_Item>();
        database_open();

        if(TextUtils.isEmpty(selection)){
            selection = null;
            selectionArgs = null;
        }

        Cursor cursor = database.query(
                DbSchema.Table_Checkbox_Item.TABLE_NAME,
                allColumns,
                selection==null ? null : selection,
                selectionArgs==null ? null : selectionArgs,
                groupBy==null ? null : groupBy,
                having==null ? null : having,
                orderBy==null ? null : orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Checkbox_Item checkbox_item = cursorToCheckbox_Item(cursor);
            checkbox_itemList.add(checkbox_item);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return checkbox_itemList;
    }

    public static long insertRecord(Checkbox_Item checkbox_item) {
        ContentValues values = new ContentValues();
        values = getCheckbox_ItemValues(checkbox_item);
        database_open();
        long insertId = database.insert(DbSchema.Table_Checkbox_Item.TABLE_NAME , null, values);
        database_close();
        return insertId;
    }

    public static int updateRecord(Checkbox_Item checkbox_item) { 
        ContentValues values = new ContentValues();
        values = getCheckbox_ItemValues(checkbox_item);
        database_open();
        String[] where = new String[] { String.valueOf(checkbox_item.getID()) }; 
        int updatedId = database.update(DbSchema.Table_Checkbox_Item.TABLE_NAME , values, DbSchema.Table_Checkbox_Item.COL_ID + " = ? ",where );
        database_close();
        return updatedId;
    }

    public static int deleteRecord(Checkbox_Item checkbox_item) { 
        database_open();
        String[] where = new String[] { String.valueOf(checkbox_item.getID()) }; 
        int deletedCount = database.delete(DbSchema.Table_Checkbox_Item.TABLE_NAME , DbSchema.Table_Checkbox_Item.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteRecord(String id) {
        database_open();
        String[] where = new String[] { id }; 
        int deletedCount = database.delete(DbSchema.Table_Checkbox_Item.TABLE_NAME , DbSchema.Table_Checkbox_Item.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteAllRecords() {
        database_open();
        int deletedCount = database.delete(DbSchema.Table_Checkbox_Item.TABLE_NAME , null, null );
        database_close();
        return deletedCount;
    }

    protected static ContentValues getCheckbox_ItemValues(Checkbox_Item checkbox_item) {
        ContentValues values = new ContentValues();

        values.put(DbSchema.Table_Checkbox_Item.COL_ID, checkbox_item.getID());
        values.put(DbSchema.Table_Checkbox_Item.COL_ACTION, checkbox_item.getAction());

        return values;
    }

    protected static Checkbox_Item cursorToCheckbox_Item(Cursor cursor)  {
        Checkbox_Item checkbox_item = new Checkbox_Item();

        checkbox_item.setID(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Checkbox_Item.COL_ID)));
        checkbox_item.setAction(cursor.getString(cursor.getColumnIndex(DbSchema.Table_Checkbox_Item.COL_ACTION)));

        return checkbox_item;
    }

    public static ArrayList<String> loadAction(){
        database_open();

        String query = "SELECT "+DbSchema.Table_Checkbox_Item.COL_ACTION+" FROM " +DbSchema.Table_Checkbox_Item.TABLE_NAME;

        Cursor cursor = database.rawQuery(query,null);


        ArrayList<String> theActions = new ArrayList<>();


        if (cursor != null) {
            while(cursor.moveToNext()) {
                theActions.add(cursor.getString(0));

            }
        }




        cursor.close();
        database_close();
        return theActions;
    }

    public static int maxId(){
        database_open();
        String query = "SELECT MAX("+DbSchema.Table_Checkbox_Item.COL_ID+") FROM "+DbSchema.Table_Checkbox_Item.TABLE_NAME;
        Cursor cursor  = database.rawQuery(query,null);
        int maxID=-1;



        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            if (cursor != null)
            {
                maxID = cursor.getInt(0);
            }
        }
        cursor.close();
        database_close();

        if(maxID != -1)
            return maxID+1;
        else
            return 0;
    }

}

