package unam.mx.tarea03a;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;

    User user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = getIntent().getExtras();

        user = extras.getParcelable("USER");


        TextView txtName = findViewById(R.id.txtNameMain2);
        TextView txtSquats = findViewById(R.id.txtSquatsMain2);
        TextView txtPushUps = findViewById(R.id.txtPushUpsMain);
        TextView txtCrunches = findViewById(R.id.txtCrunchesMain);
        TextView txtImc = findViewById(R.id.txtImcMain);
        TextView txtHR = findViewById(R.id.txtHearRateMain);

        Button btnIMC = findViewById(R.id.btnIMC);
        Button btnHR = findViewById(R.id.btnHR);
        Button btnSTR = findViewById(R.id.btnSTR);

        txtName.setText(user.getName());
        txtSquats.setText(String.valueOf(user.getSquats()));
        txtPushUps.setText(String.valueOf(user.getPushUps()));
        txtCrunches.setText(String.valueOf(user.getCrunches()));
        txtImc.setText(String.valueOf(user.getImc()));
        txtHR.setText(String.valueOf(user.getHearRate()));

        Toolbar toolbar =  findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.btnAccept, R.string.btnCancel);


        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NAV", "PRESSED IMC");
                Intent k = new Intent( MainActivity.this, ImcActivity.class);
                k.putExtra("USER", user);
                startActivity(k);
            }
        });

        btnSTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NAV", "PRESSED STRENGTH");

                Intent i = new Intent( MainActivity.this, Strength.class);
                i.putExtra("USER", user);
                startActivity(i);
            }
        });

        btnHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NAV", "PRESSED HEART");
                Intent i = new Intent(MainActivity.this, HeartRate.class);
                i.putExtra("USER", user);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.heart){

            Log.d("NAV", "PRESSED HEART");
            Intent i = new Intent(MainActivity.this, HeartRate.class);
            i.putExtra("USER", user);
            startActivity(i);
        }
        if (id == R.id.strength){
            Log.d("NAV", "PRESSED STRENGTH");

            Intent i = new Intent(MainActivity.this, Strength.class);
            i.putExtra("USER", user);
            startActivity(i);
        }
        if (id == R.id.imc){
            Log.d("NAV", "PRESSED IMC");
            Intent k = new Intent(MainActivity.this, ImcActivity.class);
            k.putExtra("USER", user);
            startActivity(k);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.userMenu:
                Intent intent = new Intent(MainActivity.this,  UserActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
                break;
            case R.id.optionMenu:
                Log.d("MENU", "OPTION MENU clicked");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}