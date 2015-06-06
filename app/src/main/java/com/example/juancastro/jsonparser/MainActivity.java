package com.example.juancastro.jsonparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class MainActivity extends ListActivity {
private Context context;

    // All static variables
    static final String URL = "http://resources.260mb.net/animales.json";

    static final String KEY_ID = "id";
    static final String KEY_NOMBRE_COMUN = "nombre_comun";
    static final String KEY_NOMBRE_CIENT = "nombre_cient";
    static final String KEY_HABITAT = "habitat";
    static final String KEY_CARACTERISTICAS = "caracteristicas";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /** application context. */
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item, new String[] { KEY_ID, KEY_NOMBRE_COMUN, KEY_NOMBRE_CIENT, KEY_HABITAT, KEY_CARACTERISTICAS , }, new int[] {
                    R.id.id_v_animal, R.id.v_ncomun,R.id.v_ncient, R.id.v_habit, R.id.v_caract});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // getting values from selected ListItem
                    String txt_id = ((TextView) view.findViewById(R.id.id_v_animal)).getText().toString();
                    String txt_comun = ((TextView) view.findViewById(R.id.v_ncomun)).getText().toString();
                    String txt_cient=((TextView) view.findViewById(R.id.v_ncient)).getText().toString();
                    String txt_habit=((TextView) view.findViewById(R.id.v_habit)).getText().toString();
                    String txt_caract=((TextView) view.findViewById(R.id.v_caract)).getText().toString();

                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), vista_individual.class);
                    in.putExtra(KEY_ID, txt_id);
                    in.putExtra(KEY_NOMBRE_COMUN, txt_comun);
                    in.putExtra(KEY_NOMBRE_CIENT, txt_cient);
                    in.putExtra(KEY_HABITAT, txt_habit);
                    in.putExtra(KEY_CARACTERISTICAS, txt_caract);


                    startActivity(in);

                }
            });

        }


        protected Boolean doInBackground(final String... args) {

            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONArray json = jParser.getJSONFromUrl(URL);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);
                    String ani = c.getString(KEY_ID);
                    String ncom = c.getString(KEY_NOMBRE_COMUN);
                    String ncien = c.getString(KEY_NOMBRE_CIENT);
                    String hab = c.getString(KEY_HABITAT);
                    String car = c.getString(KEY_CARACTERISTICAS);

                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(KEY_ID, ani);
                    map.put(KEY_NOMBRE_COMUN, ncom);
                    map.put(KEY_NOMBRE_CIENT, ncien);
                    map.put(KEY_HABITAT, hab);
                    map.put(KEY_CARACTERISTICAS, car);

                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;

        }

    }
}









