package com.example.brown.proto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class HitechActivity extends ActionBarActivity {

    ListView list;

    HitechJSONParser jParser = new HitechJSONParser();
    ArrayList<Hitech> daftar_ht = new ArrayList<Hitech>();
    JSONArray daftarHt = null;
    String url_read_ht = "http://indrapurnama27.000webhostapp.com/locakupka/read_01.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_HT = "hitech";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_DESKRIPSI = "deskripsi";
    public static final String TAG_LOKASI = "lokasi";
    public static final String TAG_LANTAI = "lantai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview_ht);

        //jalankan ReadMhsTask
        ReadHtTask m= (ReadHtTask) new ReadHtTask().execute();

    }


    class ReadHtTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HitechActivity.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getHtList(); //memanggil method getMhsList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(HitechActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(HitechActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                list.setAdapter(new HitechAdapter(HitechActivity.this,daftar_ht)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }


        //method untuk memperoleh daftar mahasiswa dari JSON
        public String getHtList()
        {
            Hitech tempHt = new Hitech();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_ht,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarHt = json.getJSONArray(TAG_HT);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarHt.length() ; i++){
                        JSONObject c = daftarHt.getJSONObject(i);
                        tempHt = new Hitech();
                        tempHt.setHtId(c.getString(TAG_ID));
                        tempHt.setHtName(c.getString(TAG_NAMA));
                        tempHt.setHtHarga(c.getString(TAG_HARGA));
                        tempHt.setHtDeskripsi(c.getString(TAG_DESKRIPSI));
                        tempHt.setHtLokasi(c.getString(TAG_LOKASI));
                        tempHt.setHtLantai(c.getString(TAG_LANTAI));
                        daftar_ht.add(tempHt);
                    }
                    return "OK";
                }
                else {
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

    }
}


