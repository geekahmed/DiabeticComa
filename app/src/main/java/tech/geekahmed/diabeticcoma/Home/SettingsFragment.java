package tech.geekahmed.diabeticcoma.Home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.geekahmed.diabeticcoma.Home.Adapters.NumbersAdapter;
import tech.geekahmed.diabeticcoma.R;

public class SettingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NumbersAdapter numbersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initListeners(){

    }
}