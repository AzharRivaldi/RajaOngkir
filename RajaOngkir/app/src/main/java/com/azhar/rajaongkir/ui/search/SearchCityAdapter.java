package com.azhar.rajaongkir.ui.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.rajaongkir.R;
import com.azhar.rajaongkir.data.city.DataCity;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.ViewHolder> {

    Context context;
    List<DataCity> data;

    public SearchCityAdapter(Context context, List<DataCity> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCity.setText(data.get(position).getType()+" "+data.get(position).getCityName());
        holder.tvProvince.setText("Provinsi "+data.get(position).getProvince());
        holder.llCity.setOnClickListener(v -> {
            switch (((Activity) context).getIntent().getExtras().getInt("requestCode")){
                case 1:
                    Intent source = new Intent();
                    source.putExtra("city", holder.tvCity.getText().toString());
                    source.putExtra("city_id", data.get(position).getCityId());
                    ((Activity) context).setResult(Activity.RESULT_OK, source);
                    break;
                case 2:
                    Intent destination = new Intent();
                    destination.putExtra("city", holder.tvCity.getText().toString());
                    destination.putExtra("city_id", data.get(position).getCityId());
                    ((Activity) context).setResult(Activity.RESULT_OK, destination);
                    break;
            }
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCity;
        TextView tvProvince;
        LinearLayout llCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvProvince = itemView.findViewById(R.id.tvProvince);
            llCity = itemView.findViewById(R.id.llCity);
        }
    }
}
