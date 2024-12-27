package com.example.screentestapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.screentestapp.R;
import com.example.screentestapp.entity.NationData;

public class DetailActivity extends AppCompatActivity {
    TextView tvNation, tvYear, tvIDNation, tvIDYear, tvSlugNation;
    EditText etPopulation;
    Button btnUpdate;
    NationData nationData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNation = findViewById(R.id.tvNation);
        tvYear = findViewById(R.id.tvYear);
        tvIDNation = findViewById(R.id.tvIDNation);
        tvIDYear = findViewById(R.id.tvIDYear);
        tvSlugNation = findViewById(R.id.tvSlugNation);
        etPopulation = findViewById(R.id.etPopulation);
        btnUpdate = findViewById(R.id.btnUpdate);

        nationData = (NationData) getIntent().getSerializableExtra("nation");
        tvNation.setText("Nation: "+nationData.getNation());
        tvYear.setText("Year: "+nationData.getYear());
        tvNation.setText("Nation id: "+nationData.getId());
        tvIDYear.setText("Year Id: "+nationData.getIdYear());
        tvSlugNation.setText("Slug Nation: "+nationData.getSlugNation());
        etPopulation.setText(nationData.getPopulation());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("screen_test", MODE_PRIVATE).edit().putInt("population", nationData.getPopulation()).apply();
                finish();
            }
        });
    }
}