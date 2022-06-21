package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    User user;
    DBHandler db;


    private void setGenre(Switch sGenre){
        if (user.getGenre() == 1) {
            sGenre.setText("Male");
            sGenre.setChecked(false);
        }else{
            sGenre.setText("Female");
            sGenre.setChecked(true);
        }
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle extras = getIntent().getExtras();
        user= extras.getParcelable("USER");

        TextView eName = (TextView) findViewById(R.id.entryNameUser);
        TextView ePassword = (TextView) findViewById(R.id.entryPasswordUser);
        Switch sGenre = (Switch) findViewById(R.id.switchGenreUser);
        Button btnAccept = (Button) findViewById(R.id.btnAcceptUser);
        Button btnCancel = (Button) findViewById(R.id.btnCancelUser);

        eName.setHint(user.getName());
        ePassword.setHint("");
        setGenre(sGenre);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = eName.getText().toString();
                String newPass = ePassword.getText().toString();
                int newGenre = (sGenre.isActivated())? 2: 1;
                db = new DBHandler(getApplicationContext());
                if (db.userExists(newName)){
                    Toast.makeText(getApplicationContext(), "That user name already exists", Toast.LENGTH_LONG).show();

                }else{
                    if (newName.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "User cannot be empty", Toast.LENGTH_LONG).show();
                        eName.setText(user.getName());
                        return;
                    }

                    user.setName(newName);
                    if (newPass.isEmpty()){
                        db.updateUser(newName, newGenre, user.getSquats(), user.getPushUps(), user.getCrunches(),
                                user.getImc(), user.getHearRate());
                    }else{
                        db.updateUser(newName, newPass, newGenre, user.getSquats(), user.getPushUps(), user.getCrunches(),
                                user.getImc(), user.getHearRate());
                    }
                    Toast.makeText(getApplicationContext(), "Changes done!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eName.setText(user.getName());
                ePassword.setText("");
                setGenre(sGenre);
                finish();
            }
        });
    }
}