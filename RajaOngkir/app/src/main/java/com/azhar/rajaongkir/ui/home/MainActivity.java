package com.azhar.rajaongkir.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.rajaongkir.R;
import com.azhar.rajaongkir.data.cost.DataType;
import com.azhar.rajaongkir.ui.search.SearchCityActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final int REQUEST_SOURCE = 1;
    private static final int REQUEST_DESTINATION = 2;

    private String source_id = "";
    private String destination_id = "";

    private MainPresenter presenter;
    private MainAdapter adapter;

    private List<DataType> data = new ArrayList<>();
    private List<String> courier = new ArrayList<>();

    TextInputEditText inputKotaAsal, inputKotaTujuan;
    Button btnSubmit;
    LinearLayout llMain;
    RecyclerView rvMain;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputKotaAsal = findViewById(R.id.inputKotaAsal);
        inputKotaTujuan = findViewById(R.id.inputKotaTujuan);
        btnSubmit = findViewById(R.id.btnSubmit);
        llMain = findViewById(R.id.llMain);
        rvMain = findViewById(R.id.rvMain);
        progressBar = findViewById(R.id.progressBar);

        presenter = new MainPresenter(this);

        adapter = new MainAdapter(this, data, courier);
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));

        inputKotaAsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchCityActivity.class);
                intent.putExtra("requestCode", REQUEST_SOURCE);
                startActivityForResult(intent, REQUEST_SOURCE);
            }
        });

        inputKotaTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchCityActivity.class);
                intent.putExtra("requestCode", REQUEST_DESTINATION);
                startActivityForResult(intent, REQUEST_DESTINATION);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();
                courier.clear();
                presenter.setupENV(getOrigin(), getDestination(), 1000);
            }
        });
    }

    @Override
    public void onLoadingCost(boolean loadng, int progress) {
        if (loadng) {
            llMain.setVisibility(View.VISIBLE);
            rvMain.setVisibility(View.GONE);
            progressBar.setProgress(progress);
        } else {
            progressBar.setProgress(progress);
            llMain.setVisibility(View.GONE);
            rvMain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResultCost(List<DataType> data, List<String> courier) {
        this.data.addAll(data);
        this.courier.addAll(courier);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorCost() {
        rvMain.setVisibility(View.GONE);
        llMain.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getOrigin() {
        return source_id;
    }

    @Override
    public String getDestination() {
        return destination_id;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SOURCE && resultCode == RESULT_OK) {
            inputKotaAsal.setText(data.getStringExtra("city"));
            source_id = data.getStringExtra("city_id");
        } else if (requestCode == REQUEST_DESTINATION && resultCode == RESULT_OK) {
            inputKotaTujuan.setText(data.getStringExtra("city"));
            destination_id = data.getStringExtra("city_id");
        }
    }

}
