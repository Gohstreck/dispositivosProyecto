package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    User user;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(getApplicationContext());

        TextView userTxt = (TextView) findViewById(R.id.userTextLogin);
        TextView passwordTxt = (TextView) findViewById(R.id.passwordTextLogin);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCancel = findViewById(R.id.btnCancelar);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name =  userTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                user = db.getUser(name,password );

                if (user != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "No such user exists or invalid password", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =  userTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                if (db.userExists(name)){
                    Toast.makeText(getApplicationContext(), "This user already exists", Toast.LENGTH_LONG).show();
                    userTxt.setText("");
                }else{
                    db.addNewUser(name, password, 0,0,0,0,0.0f,0);
                    user = db.getUser(name, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USER", user);

                    startActivity(intent);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =  userTxt.getText().toString();
                db.clean(name);
                Toast.makeText(getApplicationContext(), "See u", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });

    }
}