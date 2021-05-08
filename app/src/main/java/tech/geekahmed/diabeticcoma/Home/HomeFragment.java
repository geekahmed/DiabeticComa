package tech.geekahmed.diabeticcoma.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.Home.Adapters.HistoryAdapter;
import tech.geekahmed.diabeticcoma.Models.User;
import tech.geekahmed.diabeticcoma.R;


public class HomeFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected HistoryAdapter mHistoryAdapter;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = getView().findViewById(R.id.history_rv);

    }

    public void getUserData(){
        FirebaseService.getInstance().getUserDocument().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        user = new User();
                        // user.setHistory(document.get("history"));
                    } else {
                    }
                } else {

                }
            }
        });
    }

}