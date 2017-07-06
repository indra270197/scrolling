package com.example.brown.proto;

/**
 * Created by Afifatul on 6/18/2015.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HitechAdapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Hitech> data_ht=new ArrayList<Hitech>();

    private static LayoutInflater inflater = null;

    public HitechAdapter(Activity a, ArrayList<Hitech> d) {
        activity = a; data_ht = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_ht.size();
    }
    public Object getItem(int position) {
        return data_ht.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_ht, null);
        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView nama = (TextView) vi.findViewById(R.id.nama);
        TextView harga = (TextView) vi.findViewById(R.id.harga);
        TextView deskripsi = (TextView) vi.findViewById(R.id.deskripsi);
        TextView lokasi = (TextView) vi.findViewById(R.id.lokasi);
        TextView lantai = (TextView) vi.findViewById(R.id.lantai);

        Hitech daftar_ht = data_ht.get(position);
        id.setText(daftar_ht.getHtId());
        nama.setText(daftar_ht.getHtName());
        harga.setText(daftar_ht.getHtHarga());
        deskripsi.setText(daftar_ht.getHtDeskripsi());
        lokasi.setText(daftar_ht.getHtLokasi());
        lantai.setText(daftar_ht.getHtLantai());
        return vi;
    }
}