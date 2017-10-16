package com.i005114.taller2_2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i005114.taller2_2.Adapters.AdapterPost;
import com.i005114.taller2_2.Adapters.AdapterUser;
import com.i005114.taller2_2.Connection.HttpManager;
import com.i005114.taller2_2.Models.ModelPosts;
import com.i005114.taller2_2.Models.Modeluser;
import com.i005114.taller2_2.Parser.JsonPost;
import com.i005114.taller2_2.Parser.JsonUser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Posts extends AppCompatActivity {
    ProgressBar progressBarAlbum;
    RecyclerView recyclerViewAlbum;
    List<ModelPosts> modelPostsListt;
    AdapterPost adapterPost;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        toolbar= (Toolbar)findViewById(R.id.toobar1);
        showtoolbar(getResources().getString(R.string.txt4),true);

        progressBarAlbum = (ProgressBar) findViewById(R.id.id_pb_item_album);
        recyclerViewAlbum = (RecyclerView) findViewById(R.id.id_rv_item_album);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAlbum.setLayoutManager(linearLayoutManager);

        loadData(getIntent().getExtras().getString("userId"));
    }
    public void showtoolbar(String title,boolean upButton){
        //

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent ListSong = new Intent(getApplicationContext(),Posts.class);
        startActivity(ListSong);
        return super.onOptionsItemSelected(item);
    }

    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }
    public void loadData(String userId){
        if (isOnLine()){
            TaskAlbum taskAlbum = new TaskAlbum();
            taskAlbum.execute("https://jsonplaceholder.typicode.com/posts?userId="+userId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
    public void processData(){
        adapterPost = new AdapterPost( modelPostsListt, getApplicationContext());
        recyclerViewAlbum.setAdapter(adapterPost);
    }

    public class TaskAlbum extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarAlbum.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
               modelPostsListt = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarAlbum.setVisibility(View.GONE);
        }
    }
}
