package com.example.adila.chatsms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    public static String PREF_CHAT = "file.main.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sp = getSharedPreferences(PREF_CHAT,0);
        String data = sp.getString("chat","NO_DATA");

        try {
            JSONArray jsonArray = new JSONArray(data);
            chatAdapter = new ChatAdapter(jsonArray);

            recyclerView.setAdapter(chatAdapter);
            chatAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("JSON", data);

    }

    public void createPesan(View view) {
        Intent in = new Intent(this,TulisChat.class);
        startActivity(in);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
