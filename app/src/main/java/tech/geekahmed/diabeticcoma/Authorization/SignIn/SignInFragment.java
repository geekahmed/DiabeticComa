package tech.geekahmed.diabeticcoma.Authorization.SignIn;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.Home.HomeActivity;
import tech.geekahmed.diabeticcoma.Models.User;
import tech.geekahmed.diabeticcoma.R;

public class SignInFragment extends Fragment {
    private Button signInButton;
    private EditText email, password;
    private User user;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signInButton = getView().findViewById(R.id.sign_in_button_frag);
        email = getView().findViewById(R.id.sign_in_email_et);
        password = getView().findViewById(R.id.sign_in_password_et);
        progressBar = getView().findViewById(R.id.sign_in_progress_bar);
        initListeners();
    }

    private void initListeners(){
        signInButton.setOnClickListener(v -> {
            user = new User(email.getText().toString().trim(), password.getText().toString().trim());
            progressBar.setVisibility(View.VISIBLE);
            enableViews(false);
            FirebaseService firebaseService = FirebaseService.getInstance();
            firebaseService.signIn(user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(requireActivity(), HomeActivity.class));
                    } else {
                        Toast.makeText(requireActivity(), "Login Failed ", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        enableViews(true);
                    }
                }
            });
        });
    }
    private void enableViews(Boolean enable){
        email.setEnabled(enable);
        password.setEnabled(enable);
    }
}