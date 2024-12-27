package com.example.screentestapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screentestapp.activity.DetailActivity;
import com.example.screentestapp.R;
import com.example.screentestapp.entity.NationData;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    List<NationData> list;
    int population;

    public RecyclerAdapter(Context context, List<NationData> list, int population) {
        this.context = context;
        this.list = list;
        this.population = population;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_adapter, null);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.tvNation.setText("Nation: "+list.get(position).getNation());
        holder.tvYear.setText("Year: "+list.get(position).getYear());
        if (population == 0) {
            holder.tvPopulation.setText("Population: "+list.get(position).getPopulation());
        } else {
            holder.tvPopulation.setText("Population: "+population);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNation, tvYear, tvPopulation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNation = itemView.findViewById(R.id.tvNation);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("nation", list.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
