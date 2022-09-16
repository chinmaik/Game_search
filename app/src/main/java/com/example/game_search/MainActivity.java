package com.example.game_search;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_search.api.SetApi;
import com.example.game_search.pojo.Game;
import com.example.game_search.response.GameResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

 //   private ArrayList<Game> gameArrayList;
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;
    String[] games;
    int[] image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recycler_view);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Call<List<GameResponse>> gamelist = controller.getInstance().create(SetApi.class);

        gamelist.enqueue(new Callback<List<GameResponse>>() {
            @Override
            public void onResponse(Call<List<GameResponse>> call, Response<List<GameResponse>> response) {
                List<GameResponse> data = response.body();
                mAdapter = new GameAdapter(data);
                mRecyclerView.setAdapter(mAdapter);
                Log.d("this gd" ,String.valueOf(mAdapter.gameArrayList.size()));

            }

            @Override
            public void onFailure(Call<List<GameResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        //Hard coded implementation
//        games = new String[]{
//                "War Games",
//                "Mortal Kombat games",
//                "Dice Games",
//                "Fifa Games",
//                "Video games"
//
//        };
//
//        image = new int[]{
//                R.drawable.wargame,
//                R.drawable.mortal,
//                R.drawable.dice,
//                R.drawable.fifa,
//                R.drawable.video
//        };
//
//        for(int i=0;i<games.length;i++){
//
//            Game model = new Game(games[i],image[i]);
//            gameArrayList.add(model);
//
//        }
//        mAdapter = new GameAdapter(this, gameArrayList);
//        mAdapter.notifyDataSetChanged();

    }



    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView search = (SearchView) menuItem.getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
