package com.example.threads_20.Views.Home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.threads_20.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    FirebaseFirestore fireStore;
    MaterialButton postButton;
    TextInputEditText postText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Initialize Firebase Firestore
        fireStore = FirebaseFirestore.getInstance();

        // Find views by ID
        postText = findViewById(R.id.postContent); // Corrected ID to match XML
        postButton = findViewById(R.id.postButton);

        // Set up button click listener
        postButton.setOnClickListener(v -> postData());
    }

    public void postData() {
        // Get text from input field
        String postContent = postText.getText().toString();

        // Create data map to be stored in Firestore
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("name", "Maisam");
        map.put("phone", "0318");
        map.put("Text", postContent); // Use user input

        // Save data to Firestore
        fireStore.collection("posts").document("123").set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PostActivity.this, "Post successful!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostActivity.this, "Post failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
