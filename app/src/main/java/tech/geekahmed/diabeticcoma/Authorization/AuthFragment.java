package tech.geekahmed.diabeticcoma.Authorization;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import tech.geekahmed.diabeticcoma.R;


public class AuthFragment extends Fragment {
    private Button signInButton, signUpButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signInButton = getView().findViewById(R.id.sign_in_button);
        signUpButton = getView().findViewById(R.id.join_button);

        initListeners();
    }

    private void initListeners() {
        signInButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.signInFragment);
        });
        signUpButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.signUpFragment);
        });
    }
}