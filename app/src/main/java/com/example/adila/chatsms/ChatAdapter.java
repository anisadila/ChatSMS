package com.example.adila.chatsms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adila on 03-Nov-17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    JSONArray jsonArray;

    public ChatAdapter (JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.nama.setText(jsonObject.getString("namaKontak"));
            holder.konten.setText(jsonObject.getString("kontenChat"));
            holder.tanggal.setText(jsonObject.getString("tanggal"));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, konten, tanggal;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.namaItem);
            konten = (TextView) itemView.findViewById(R.id.kontenItem);
            tanggal = (TextView) itemView.findViewById(R.id.tanggalItem);
        }
    }
}
