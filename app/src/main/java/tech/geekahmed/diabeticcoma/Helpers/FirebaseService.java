package tech.geekahmed.diabeticcoma.Helpers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import tech.geekahmed.diabeticcoma.Models.User;

// Implement Singleton
public class FirebaseService {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    private static FirebaseService firebaseService = null;

    private FirebaseService(){
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
    public static FirebaseService getInstance(){
        if (firebaseService == null)
            firebaseService = new FirebaseService();
        return firebaseService;
    }

    // Sign Up
    public Task<AuthResult> signUp(User user){
        return mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
    }

    // Sign In
    public Task<AuthResult> signIn(User user){
        return mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
    }

    // Get Current User
    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

    // Add User Data to FireStore
    public void addUserToFireStore(Map<String, Object> user){
        firebaseFirestore.collection("users").document(getCurrentUser().getUid()).set(user);
    }

    // Send GPS Data

    // Add Emergency Number

    // Delete Emergency Number

    // Add RPI ID

    // Get History
    public Task<DocumentSnapshot> getUserDocument(){
        String uid = getCurrentUser().getUid();
        return firebaseFirestore.collection("users").document(uid).get();
    }
}
