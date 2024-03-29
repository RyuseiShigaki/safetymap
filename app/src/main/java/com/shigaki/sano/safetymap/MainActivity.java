package com.shigaki.sano.safetymap;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shigaki.sano.safetymap.db.PlaceDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!(NetworkCheck.netWorkCheck(this.getApplicationContext()))) {
            Toast.makeText(this, "インターネットに接続してください", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            setContentView(R.layout.activity_main);

            Button button_open_map = findViewById(R.id.button_open_map);
            button_open_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }
            });

            Button add_user_spot = (Button) findViewById(R.id.add_user_spot);
            add_user_spot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddPlaceActivity.class);
                    startActivity(intent);
                }
            });

            Button search_near_spot = (Button) findViewById(R.id.search_near_spot);
            search_near_spot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SortDistanceActivity.class);
                    startActivity(intent);
                }
            });

            Button delete_spot = (Button)findViewById(R.id.button_hide);
            delete_spot.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    Intent intent = new Intent(MainActivity.this, DeleteSpotActivity.class);
                    startActivity(intent);
                    return true;
                }
            });
        }
        


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // 戻るボタンの処理
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(homeIntent);

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if(!(NetworkCheck.netWorkCheck(this.getApplicationContext()))) {
            Toast.makeText(this, "インターネットに接続してください", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
