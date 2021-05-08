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

import tech.geekahmed.diabeticcoma.R;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>{


    private ArrayList<String> numbers;
    private LayoutInflater mInflater;
    public NumbersAdapter(Context context, ArrayList<String> numbers){
        this.numbers = numbers;
        this.mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @NotNull
    @Override
    public NumbersAdapter.NumberViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.number_item, parent, false);
        return new NumbersAdapter.NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NumbersAdapter.NumberViewHolder holder, int position) {
        holder.number.setText(numbers.get(position));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        public NumberViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
        }
    }
}
