package com.example.checkboxtryout.DB;

import android.os.Bundle;
import java.util.Date;

public class Checkbox_Item {

    public static final String COL_ID = "ID";
    public static final String COL_ACTION = "Action";

    private Integer mID;
    private String mAction;

    public Checkbox_Item() {
    }

    public Checkbox_Item(Integer ID, String Action) {
        this.mID = ID;
        this.mAction = Action;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer ID) {
        this.mID = ID;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String Action) {
        this.mAction = Action;
    }


    public Bundle toBundle() { 
        Bundle b = new Bundle();
        b.putInt(COL_ID, this.mID);
        b.putString(COL_ACTION, this.mAction);
        return b;
    }

    public Checkbox_Item(Bundle b) {
        if (b != null) {
            this.mID = b.getInt(COL_ID);
            this.mAction = b.getString(COL_ACTION);
        }
    }

    @Override
    public String toString() {
        return "Checkbox_Item{" +
            " mID=" + mID +
            ", mAction='" + mAction + '\'' +
            '}';
    }


}
