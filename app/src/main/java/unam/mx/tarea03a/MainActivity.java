package unam.mx.tarea03a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    String imcRes, hrRes;
    float imc;
    int hr;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Bundle extras = getIntent().getExtras();
            imcRes = extras.getString("imcRes", "");
            imc = extras.getFloat("imc");
            TextView lastIMC = (TextView) findViewById(R.id.txtLastIMC);
            lastIMC.setText("Last IMC: " + imc + ". " + imcRes);
            hrRes = extras.getString("hrRes", "");
            hr = extras.getInt("hr");
            TextView lastHR = (TextView) findViewById(R.id.txtLastHeart);
            lastHR.setText("Last HR: " + hr + ". " + hrRes);

        } catch (NullPointerException ignored){
            TextView lastIMC = (TextView) findViewById(R.id.txtLastIMC);
            lastIMC.setText("No prior IMC");

            TextView lastHR= (TextView) findViewById(R.id.txtLastHeart);
            lastHR.setText("No prior Heart Rate");
        }



        Button btnAccept = findViewById(R.id.btnAceptar);
        Button btnCancel = findViewById(R.id.btnCancelar);
        EditText user = findViewById(R.id.userText);
        EditText password = findViewById(R.id.passwordText);


        Toolbar toolbar =  findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImcActivity.class);
                intent.putExtra("USER", user.getText().toString());
                intent.putExtra("PASSWORD", password.getText().toString());
                startActivity(intent);

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.btnAccept, R.string.btnCancel);


        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("TAco",id+"");
        switch (id){
            case R.id.heart:
                EditText user = findViewById(R.id.userText);
                Intent i = new Intent(MainActivity.this, HeartRate.class);
                i.putExtra("USER", user.getText().toString());
                startActivity(i);

                break;
            case R.id.strength:
                Toast.makeText(this,R.string.drawer2, Toast.LENGTH_SHORT).show();
                Log.d("Drawer", "Input:  " + getString(R.string.drawer2));
                break;
            case R.id.hearing:
                Toast.makeText(this,R.string.drawer3, Toast.LENGTH_SHORT).show();
                Log.d("Drawer", "Input:  " + getString(R.string.drawer3));
                break;

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
            case R.id.colorMenu:
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                Log.d("MENU", "COLOR MENU clicked");
                startActivity(intent);
                return true;
            case R.id.genreMenu:
                Log.d("MENU", "GENRE MENU clicked");
                return true;
            case R.id.userMenu:
                Log.d("MENU", "USER MENU clicked");
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