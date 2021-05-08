package tech.geekahmed.diabeticcoma.Home.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import tech.geekahmed.diabeticcoma.Models.History;
import tech.geekahmed.diabeticcoma.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<History> histories;
    private LayoutInflater mInflater;
    public HistoryAdapter(Context context, ArrayList<History> histories){
        this.histories = histories;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.coma_history_item, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryAdapter.HistoryViewHolder holder, int position) {
            holder.coma_time.setText(histories.get(position).getTimestamp().toDate().toString());
            holder.coma_location.setText(histories.get(position).getLocation());
            holder.coma_disc.setText(histories.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView coma_disc, coma_location, coma_time;
        public HistoryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            coma_disc = itemView.findViewById(R.id.coma_disc);
            coma_location = itemView.findViewById(R.id.coma_location);
            coma_time = itemView.findViewById(R.id.coma_time);
        }
    }
}
