package es.londite.joseantonio.firstassginment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {

    DBlist db;
    public int indix = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        SQLiteDatabase myDB = openOrCreateDatabase("myDB", MODE_PRIVATE, null);

    /*
        private static synchronized SQLiteDatabase open() throws SQLException {

        return SQLiteOpenHelper.getWritableDatabase();
    }

    */

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonShowList = (Button) findViewById(R.id.buttonShowList);

        //Makes the Add button do things, more specifically grab the text in the field and adds it to the db
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new DBlist(getBaseContext());
                EditText textEntry = (EditText) findViewById(R.id.textEntry);
                String textvalue = textEntry.getText().toString();
                db.open();
                insertInDB(textvalue, indix);

            }
        });
        //Makes the ShowList button  eeerhm... show the list
        buttonShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String getName;
                SimpleCursorAdapter adapter;



                ListView listaCosas = (ListView) findViewById(R.id.listaCosas);

                db = new DBlist(getBaseContext());
                db.open();
                Cursor cur = db.returnData();
                if (cur.moveToFirst()){
                    do {
                        getName = cur.getString(1);

                    }while(cur.moveToNext());
                }
                db.close();


            }
        });
    }
//Okay here is where the magic is produced
    private int insertInDB(String textvalue, int indix) {

        db.insertInDB(indix, textvalue);
        Toast.makeText(getBaseContext(), "Data stored", Toast.LENGTH_SHORT).show();
        db.close();
        indix += 1;

        return indix;
    }

/*
    private void extractFromDB(ListView listaCosas) {


    }
*/



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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
