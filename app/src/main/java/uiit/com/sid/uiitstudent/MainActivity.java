package uiit.com.sid.uiitstudent;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import uiit.com.sid.uiitstudent.Helper.Console;
import uiit.com.sid.uiitstudent.Properties.Student;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth userAuth;
    private DatabaseReference userDataRef;
    DrawerLayout drawer;
    CircleImageView circleImageViewUser;
    TextView headerTitleTextView, headerSubtitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAuth = FirebaseAuth.getInstance();
        userDataRef = FirebaseDatabase.getInstance().getReference().child("Students").child(userAuth.getCurrentUser().getUid());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        headerTitleTextView = headerView.findViewById(R.id.nav_header_title_text_view);
        headerSubtitleTextView = headerView.findViewById(R.id.nav_header_subtitle_text_view);

        userDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                headerTitleTextView.setText(student.getName());
                headerSubtitleTextView.setText(String.valueOf(student.getRollNumber())+" "+student.getBranch().toString()+" "+String.valueOf(student.getSemester()+" Sem"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Console.toast(getApplicationContext(),"Error");
            }
        });

        circleImageViewUser = headerView.findViewById(R.id.nav_header_image_view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_website) {
            String webAddress = "http://www.hpuniv.ac.in/content/university-detail/home.php?uiit";
            Uri websiteUri = Uri.parse(webAddress);
            Intent openWebsite = new Intent(Intent.ACTION_VIEW,websiteUri);
            startActivity(openWebsite);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container,new HomeFragment()).commit();
                break;
            case R.id.nav_attendance:
                Console.toast(getApplicationContext(),"Attendance");
                break;
            case R.id.nav_notices:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new NoticesFragment()).commit();
                break;
            case R.id.nav_study_resources:
                Console.toast(getApplicationContext(),"Study Resources");
                break;
            case R.id.nav_club_activities:
                Console.toast(getApplicationContext(),"Club Activities");
                break;
            case R.id.nav_web_portal:
                Intent openWebPortal = new Intent(getApplicationContext(),WebPortalActivity.class);
                startActivity(openWebPortal);
                break;
            case R.id.nav_feedback:
                Intent openFeedback = new Intent(getApplicationContext(),FeedbackActivity.class);
                startActivity(openFeedback);
                break;
            case R.id.nav_about_us:
                Console.toast(getApplicationContext(),"About Us");
                break;
            case R.id.nav_sign_out:
                userAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
