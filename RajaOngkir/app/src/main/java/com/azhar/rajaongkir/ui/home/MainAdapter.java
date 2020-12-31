package com.azhar.rajaongkir.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.rajaongkir.R;
import com.azhar.rajaongkir.data.cost.DataType;
import com.azhar.rajaongkir.network.Helper;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    List<DataType> data;
    List<String> courier;
    int imgLogo;

    public MainAdapter(Context context, List<DataType> data, List<String> courier) {
        this.context = context;
        this.data = data;
        this.courier = courier;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String strLogo = courier.get(position);

        if (strLogo.equals("JNE"))
            imgLogo = R.drawable.logo_jne;
        else if (strLogo.equals("POS"))
            imgLogo = R.drawable.logo_pos;
        else if (strLogo.equals("TIKI"))
            imgLogo = R.drawable.logo_tiki;

        holder.imgLogoKurir.setImageResource(imgLogo);
        holder.tvType.setText("Jenis Layanan : " + data.get(position).getService());
        holder.tvPrice.setText(Helper.formatRupiah(data.get(position).getCost().get(0).getValue()));
        holder.tvEst.setText(data.get(position).getCost().get(0).getEtd() + " Hari");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEst;
        TextView tvPrice;
        TextView tvType;
        ImageView imgLogoKurir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEst = itemView.findViewById(R.id.tvEst);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvType = itemView.findViewById(R.id.tvType);
            imgLogoKurir = itemView.findViewById(R.id.imgLogo);
        }
    }
}
