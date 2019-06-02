package com.example.checkboxtryout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.checkboxtryout.DB.Checkbox_Item;
import com.example.checkboxtryout.DB.Checkbox_ItemDao;
import com.example.checkboxtryout.DB.DbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.setConfig(this);
        populateSampleData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });



    // Get listview checkbox.
        final ListView listViewWithCheckbox = findViewById(R.id.list_view_with_checkbox);

        // Initiate listview data.
        final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
 public void refreshMe()
{
    finish();
    startActivity(getIntent());
}

    public static void populateSampleData() {
        if (Checkbox_ItemDao.loadAllRecords().size() == 0) {

            Checkbox_Item spin = new Checkbox_Item();

            spin.setID(Checkbox_ItemDao.maxId());
            spin.setAction("Laundry");
            Checkbox_ItemDao.insertRecord(spin);

            spin = new Checkbox_Item();

            spin.setID(Checkbox_ItemDao.maxId());
            spin.setAction("Dishes");
            Checkbox_ItemDao.insertRecord(spin);

            spin = new Checkbox_Item();

            spin.setID(Checkbox_ItemDao.maxId());
            spin.setAction("Homework");
            Checkbox_ItemDao.insertRecord(spin);

            spin = new Checkbox_Item();

            spin.setID(Checkbox_ItemDao.maxId());
            spin.setAction("Yoga");
            Checkbox_ItemDao.insertRecord(spin);



        }
    }
    private void openDialog() {
        Dialog actionDialog = new Dialog();
        actionDialog.show(getSupportFragmentManager(),"My Action Dialog");
    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList()
    {
        String itemTextArr[] = GetStringArray(Checkbox_ItemDao.loadAction());

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();


        for (String itemText : itemTextArr) {
            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
