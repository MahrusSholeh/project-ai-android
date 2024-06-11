package ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.R;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.api.model.RekomendasiModel;
import java.util.List;

public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.ViewHolder> {
    private final List<RekomendasiModel> recommendations;

    public RecomendationAdapter(List<RekomendasiModel> recommendations) {
        this.recommendations = recommendations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemrekomendasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RekomendasiModel recommendation = recommendations.get(position);
        holder.namaTempat.setText(recommendation.getNamaTempat());
        holder.lokasi.setText(recommendation.getLokasi());
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaTempat;
        public TextView lokasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTempat = itemView.findViewById(R.id.namatempat);
            lokasi = itemView.findViewById(R.id.lokasi);
        }
    }
}
