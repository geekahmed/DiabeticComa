package tech.geekahmed.diabeticcoma.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.R;

public class SettingsFragment extends Fragment {

    public EditText number;
    public EditText RpiID;
    public Button addNumber;
    public Button deleteNumber;
    public Button addRPI;
    public TextView emergencyNumbers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addNumber = getView().findViewById(R.id.addenum);
        number = getView().findViewById(R.id.enumber);
        deleteNumber = getView().findViewById(R.id.deletenum);
        addRPI = getView().findViewById(R.id.addRPI);
        emergencyNumbers = getView().findViewById(R.id.viewNumbers);
        RpiID = getView().findViewById(R.id.RpiID);
        showInformation();
        addRPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rpiID = RpiID.getText().toString();
                FirebaseService.getInstance().addRpiID(rpiID);
                showInformation();
            }
        });
        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergencyNumber = number.getText().toString();
                FirebaseService.getInstance().addEmergencyNumber(emergencyNumber);
                showInformation();
            }
        });
        deleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergencyNumber = number.getText().toString();
                FirebaseService.getInstance().deleteEmergencyNumber(emergencyNumber);
                showInformation();
            }
        });
    }
    public void showInformation(){
        emergencyNumbers.setText(" ");
        Task<DocumentSnapshot> document = FirebaseService.getInstance().getUserDocument().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        RpiID.setText(document.getString("RpiID"));
                        ArrayList<String> numbers = (ArrayList) document.get("emergency_numbers");
                        if(numbers != null){
                            for (String i : numbers) {
                                emergencyNumbers.setText(emergencyNumbers.getText().toString()+"\n\n "+i);
                            }
                        }
                    }
                }
            }
        });


    }

    private void initListeners(){

    }
}