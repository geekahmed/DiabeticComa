package tech.geekahmed.diabeticcoma.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.Home.Adapters.HistoryAdapter;
import tech.geekahmed.diabeticcoma.Models.History;
import tech.geekahmed.diabeticcoma.Models.User;
import tech.geekahmed.diabeticcoma.R;


public class HomeFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected HistoryAdapter mHistoryAdapter;
    private ArrayList<History> histories = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        histories.clear();
        mRecyclerView = getView().findViewById(R.id.history_rv);
        getUserData();

    }

    public void getUserData(){
        FirebaseService.getInstance().getUserDocument().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> map = document.getData();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            if (entry.getKey().equals("history")) {
                                ArrayList<Map<String, Object>> history = (ArrayList<Map<String, Object>>) entry.getValue();

                                for (Map<String, Object> elem: history){
                                    histories.add(new History((String) elem.get("description"), (String) elem.get("location"), (Timestamp) elem.get("timestamp")));
                                }
                                Log.d("TAG1", histories.toString());
                                Log.d("TAG1", histories.get(0).getDescription());
                            }
                        }
                        if (histories.size() > 0){
                            mHistoryAdapter = new HistoryAdapter(getContext(), histories);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            mRecyclerView.setAdapter(mHistoryAdapter);
                        }
                        Log.d("TAG", "DocumentSnapshot data: " + map);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }

}