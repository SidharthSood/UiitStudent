package uiit.com.sid.uiitstudent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import uiit.com.sid.uiitstudent.Properties.Notice;
//import uiit.com.sid.uiitstudent.Properties.Student;
//import uiit.com.sid.uiitstudent.Properties.StudentsBranch;

public class Splash extends AppCompatActivity {

    private FirebaseAuth userAuth;
//    private DatabaseReference userRef;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        userAuth = FirebaseAuth.getInstance();
//        userRef = FirebaseDatabase.getInstance().getReference();
//        addData();
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);
    }

//    private void addData() {
//        FirebaseUser currentUser = userAuth.getCurrentUser();
//        if (currentUser != null) {
//            String email = currentUser.getEmail();
//            String id = currentUser.getUid();
//            Student student = new Student(id,email,"Sidharth Sood",63001,8, StudentsBranch.CSE);
//            userRef.child("Students").child(id).setValue(student);
//        }
//
//        userRef.child("Notices").child("Notice01").setValue(new Notice("Notice01","http://uiit.ac.in/download/new_doc/Notice%20_for%20_Fee%20_Receipt.pdf","Notice for fee receipt",true));
//    }

    @Override
    protected void onStart() {
        super.onStart();
        final Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    sharedPreferences = getSharedPreferences(getString(R.string.login_state), Context.MODE_PRIVATE);
                    FirebaseUser currentUser = userAuth.getCurrentUser();
                    if (currentUser != null) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        };
        thread.start();
    }

    @Override
    public void onBackPressed() {

    }
}
