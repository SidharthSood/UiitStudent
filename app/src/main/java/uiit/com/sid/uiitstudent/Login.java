package uiit.com.sid.uiitstudent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import uiit.com.sid.uiitstudent.Helper.Console;

public class Login extends AppCompatActivity {

    private FirebaseAuth userAuth;
    Button signInButton;
    EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.signInButton);
        usernameEditText = findViewById(R.id.usernameTextInputEditText);
        passwordEditText = findViewById(R.id.passwordTextInputEditText);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(usernameEditText.getText().toString().trim().toLowerCase(), passwordEditText.getText().toString());
            }
        });
    }

    private void signInUser(String email, String password) {
       userAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()) {
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   finish();
               } else {
                   Console.toast(getApplicationContext(),"Error");
               }
           }
       });
    }

}
