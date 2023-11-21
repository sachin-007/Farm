package com.example.farm.adapter;
// PlotAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farm.R;
import com.example.farm.model.PlotModel;

import java.util.List;

public class PlotAdapter extends RecyclerView.Adapter<PlotAdapter.PlotViewHolder> {

    private List<PlotModel> plotList;
    private Context context;

    public PlotAdapter(Context context, List<PlotModel> plotList) {
        this.context = context;
        this.plotList = plotList;
    }

    @NonNull
    @Override
    public PlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plot, parent, false);
        return new PlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlotViewHolder holder, int position) {
        PlotModel plot = plotList.get(position);
        holder.tvFarmName.setText(getValidString(plot.getFarmName()));
        holder.tvTotalFarmArea.setText(getValidString(plot.getTotalFarmArea()));
        holder.tvPlotName.setText(getValidString(plot.getPlotName()));
        holder.tvSoilType.setText(getValidString(plot.getSoilType()));
        holder.tvLocation.setText(getValidString(plot.getLocation()));
        holder.tvPlotArea.setText(getValidString(plot.getPlotArea()));
    }

    private String getValidString(String value) {
        return value != null ? value : "N/A";
    }

    @Override
    public int getItemCount() {
        return plotList.size();
    }

    public void setData(List<PlotModel> plotList) {
        this.plotList = plotList;
        notifyDataSetChanged();
    }

    public static class PlotViewHolder extends RecyclerView.ViewHolder {
        TextView tvFarmName;
        TextView tvTotalFarmArea;
        TextView tvPlotName;
        TextView tvSoilType;
        TextView tvLocation;
        TextView tvPlotArea;

        public PlotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFarmName = itemView.findViewById(R.id.tvFarmName);
            tvTotalFarmArea = itemView.findViewById(R.id.tvTotalFarmArea);
            tvPlotName = itemView.findViewById(R.id.tvPlotName);
            tvSoilType = itemView.findViewById(R.id.tvSoilType);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPlotArea = itemView.findViewById(R.id.tvPlotArea);
        }
    }
}