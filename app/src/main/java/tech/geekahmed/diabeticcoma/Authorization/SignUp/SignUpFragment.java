package tech.geekahmed.diabeticcoma.Authorization.SignUp;

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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tech.geekahmed.diabeticcoma.Helpers.FirebaseService;
import tech.geekahmed.diabeticcoma.Home.HomeActivity;
import tech.geekahmed.diabeticcoma.Models.User;
import tech.geekahmed.diabeticcoma.R;

public class SignUpFragment extends Fragment {

    private ProgressBar progressBar;
    private EditText email, password, phone, firstName, lastName;
    private Button signUp;
    private AwesomeValidation validation = new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar = getView().findViewById(R.id.sign_up_progress_bar);
        email = getView().findViewById(R.id.email_et);
        password = getView().findViewById(R.id.password_et);
        phone = getView().findViewById(R.id.phone_number_et);
        firstName = getView().findViewById(R.id.first_name_et);
        lastName = getView().findViewById(R.id.last_name_et);
        signUp = getView().findViewById(R.id.sign_up_button);
        initValidations();
        initListener();

    }


    private void initListener(){
        signUp.setOnClickListener(v -> {
            if (!email.getText().toString().isEmpty())
                validation.addValidation(
                        email,
                        android.util.Patterns.EMAIL_ADDRESS,
                        getString(R.string.sign_up_invalid_email_wrong_format)
                );
            else
                validation.addValidation(
                        email,
                        android.util.Patterns.EMAIL_ADDRESS,
                        getString(R.string.sign_up_invalid_email_empty)
                );

            if (validation.validate()) {
                User user = new User();
                user.setEmail(email.getText().toString().trim());
                user.setPassword(email.getText().toString().trim());
                user.setName(firstName.getText().toString().trim().concat(" " + lastName.getText().toString().trim()));
                progressBar.setVisibility(View.VISIBLE);
                enableViews(false);
                FirebaseService.getInstance().signUp(user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(requireActivity(), "Registration Success ", Toast.LENGTH_SHORT).show();

                            Map<String, Object> data = new HashMap<>();
                            data.put("name", user.getName());
                            data.put("email", user.getEmail());
                            data.put("phone_number", user.getPhoneNumber());

                            FirebaseService.getInstance().addUserToFireStore(data);
                            startActivity(new Intent(requireActivity(), HomeActivity.class));
                        } else {
                            Toast.makeText(requireActivity(), "Failed Registration ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            enableViews(true);
                        }
                    }
                });
            }
        });
    }
    private void initValidations() {
        validation.addValidation(
                firstName,
                "[\\p{L}\\p{N}\\s]+",
                getString(R.string.name_must_bealphanumeric_text)
        );
        validation.addValidation(
                lastName,
                "[\\p{L}\\p{N}\\s]+",
                getString(R.string.name_must_bealphanumeric_text)
        );
        validation.addValidation(
                email,
                android.util.Patterns.EMAIL_ADDRESS,
                getString(R.string.sign_up_invalid_email_empty)
        );
        validation.addValidation(
                password,
                ".{8,15}",
                getString(R.string.sign_up_invalid_password)
        );
    }
    private void enableViews(Boolean enable){
        email.setEnabled(enable);
        password.setEnabled(enable);
        phone.setEnabled(enable);
        firstName.setEnabled(enable);
        lastName.setEnabled(enable);
    }
}