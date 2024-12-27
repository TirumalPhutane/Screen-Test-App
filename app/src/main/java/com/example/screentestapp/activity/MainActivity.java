package com.example.screentestapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screentestapp.R;
import com.example.screentestapp.adapter.RecyclerAdapter;
import com.example.screentestapp.entity.NationData;
import com.example.screentestapp.util.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<NationData> nationDataList;
    int population;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        nationDataList = new ArrayList<NationData>();

        population = getSharedPreferences("screen_test", MODE_PRIVATE).getInt("population", 0);
        getNationsData();

        recyclerAdapter = new RecyclerAdapter(this, nationDataList, population);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerAdapter.notifyDataSetChanged();

    }

    private void getNationsData() {
        RetrofitClient.getInstance().getApi().getNations().enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                try {
                    JSONArray array = response.body().getJSONArray("data");
                    for (int i=0; i< array.length(); i++) {
                        NationData nationData = new NationData();
                        nationData.setId(array.getJSONObject(i).getString("ID Nation"));
                        nationData.setNation(array.getJSONObject(i).getString("Nation"));
                        nationData.setIdYear(array.getJSONObject(i).getInt("ID Year"));
                        nationData.setYear(array.getJSONObject(i).getString("Year"));
                        nationData.setPopulation(array.getJSONObject(i).getInt("Population"));
                        nationData.setSlugNation(array.getJSONObject(i).getString("Slug Nation"));
                        nationDataList.add(nationData);
                    }
                } catch (JSONException e) {
                    Log.e("exception","exception", e);
                    Toast.makeText(MainActivity.this, "Can't load response...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_LONG).show();
            }
        });
    }
}