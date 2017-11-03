package com.example.adila.chatsms;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TulisChat extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    EditText createNama, createKonten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulis_chat);

        getSupportActionBar().setTitle("Tulis Chat");

        createNama = (EditText) findViewById(R.id.namaCreate);
        createKonten = (EditText) findViewById(R.id.kontenCreate);

        sp = getSharedPreferences(MainActivity.PREF_CHAT,MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void send(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("namaKontak",createNama.getText().toString());
            jsonObject.put("kontenChat",createKonten.getText().toString());
            jsonObject.put("tanggal",new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
        } catch (JSONException e){
            e.printStackTrace();
        }

        if (sp.contains("chat")){
            String data = sp.getString("chat","NO_DATA");

            try {
                JSONArray jsonArray = new JSONArray(data);
                jsonArray.put(jsonObject);
                spEditor.putString("chat",jsonArray.toString());
                spEditor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            spEditor.putString("chat",jsonArray.toString());
            spEditor.apply();
        }
        finish();
    }
}
