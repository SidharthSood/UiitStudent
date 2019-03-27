package uiit.com.sid.uiitstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import uiit.com.sid.uiitstudent.Helper.Console;
import uiit.com.sid.uiitstudent.Properties.Feedback;

public class FeedbackActivity extends AppCompatActivity {

    Button submitFeedbackButton;
    EditText feedbackEditText;
    private FirebaseAuth userAuth;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        userAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks");
        submitFeedbackButton = findViewById(R.id.button_submit_feedback);
        feedbackEditText = findViewById(R.id.editText_feedback);
        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackEditText.getText().toString().trim();
                if (feedback.isEmpty()) {
                    Console.showAlert(FeedbackActivity.this,"Empty Feedback","Feedback seems empty. Please enter feedback.");
                } else {
                    sendFeedback(feedback);
                    Console.toast(getApplicationContext(),"Feedback successfully submitted.");
                    Intent goBackToMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(goBackToMainActivity);
                    finish();
                }
            }
        });
    }

    private void sendFeedback(String feedbackText) {
        if (userAuth.getCurrentUser() != null) {
            String currentUserId = userAuth.getCurrentUser().getUid();
            Feedback feedback = new Feedback();
            feedback.setFeedbackText(feedbackText);
            feedback.setSenderId(currentUserId);
            feedback.setTimeStamp(ServerValue.TIMESTAMP);
            Console.toast(getApplicationContext(),ServerValue.TIMESTAMP.get("timestamp"));
            databaseRef.push().setValue(feedback);
        }
    }
}
