package tech.geekahmed.diabeticcoma.Home;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.Helpers.TrackingService;
import tech.geekahmed.diabeticcoma.Home.Adapters.HistoryAdapter;
import tech.geekahmed.diabeticcoma.Models.History;
import tech.geekahmed.diabeticcoma.R;


public class HomeFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected HistoryAdapter mHistoryAdapter;
    private ArrayList<History> histories = new ArrayList<>();
    private Button startGPS;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;


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
        startGPS = getView().findViewById(R.id.start_gps);
        getUserData();
        initListener();
    }

    private void initListener() {
        startGPS.setOnClickListener(view -> {
            if (!isLocationServiceRunning()){
                if (!checkPermission()){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                } else {
                    startLocationService();
                    startGPS.setText(R.string.stop);
                }

            } else {
                stopLocationService();
                startGPS.setText(R.string.start);
            }
        });
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

    private Boolean isLocationServiceRunning(){
        ActivityManager activityManager = (ActivityManager) (getActivity().getSystemService(Context.ACTIVITY_SERVICE));
        if (activityManager != null){
            for (ActivityManager.RunningServiceInfo serviceInfo: activityManager.getRunningServices(Integer.MAX_VALUE)){
                if (TrackingService.class.getName().equals(serviceInfo.service.getClassName())){
                    if (serviceInfo.foreground)
                        return true;
                }
            }
            return false;
        }

        return false;
    }

    private void startLocationService(){
        if (!isLocationServiceRunning()){
            Intent intent = new Intent(getActivity(), TrackingService.class);
            intent.setAction(TrackingService.ACTION_START_LOCATION_SERVICE);
            getActivity().startService(intent);
            Toast.makeText(getContext(), "Location Service Started", Toast.LENGTH_SHORT).show();
        }
    }
    private void stopLocationService(){
        if (isLocationServiceRunning()){
            Intent intent = new Intent(getContext(), TrackingService.class);
            intent.setAction(TrackingService.ACTION_STOP_LOCATION_SERVICE);
            getActivity().startService(intent);
            Toast.makeText(getContext(), "Location Service Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission(){
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationService();
                startGPS.setText(R.string.stop);
            } else{
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }
}