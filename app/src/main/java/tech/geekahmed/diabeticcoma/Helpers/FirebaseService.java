package tech.geekahmed.diabeticcoma.Helpers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.lang.reflect.Array;
import java.util.Map;



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
    public Task<AuthResult> signUp(String email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    // Sign In
    public Task<AuthResult> signIn(String email, String password){
        return mAuth.signInWithEmailAndPassword(email, password);
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
    public void sendGPSData(GeoPoint geoPoint){
        firebaseFirestore.collection("users").document(getCurrentUser().getUid()).update("current_location", geoPoint);
    }
    // Add Emergency Number
    public void addEmergencyNumber(String number){
        firebaseFirestore.collection("users").document(getCurrentUser().getUid()).update("emergency_numbers", FieldValue.arrayUnion(number));
    }
    // Delete Emergency Number
    public void deleteEmergencyNumber(String number){
        firebaseFirestore.collection("users").document(getCurrentUser().getUid()).update("emergency_numbers", FieldValue.arrayRemove(number));
    }
    // Add RPI ID
    public void addRpiID(String ID){
        firebaseFirestore.collection("users").document(getCurrentUser().getUid()).update("RpiID", ID);
    }
    // Get History
    public Task<DocumentSnapshot> getUserDocument(){
        String uid = getCurrentUser().getUid();
        return firebaseFirestore.collection("users").document(uid).get();
    }
}

