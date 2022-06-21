package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Strength extends AppCompatActivity {

    User user;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
        Bundle extras = getIntent().getExtras();

        user = extras.getParcelable("USER");

        TextView ePushUps = (TextView) findViewById(R.id.entryPushUpsStrength);
        TextView eSquats = (TextView) findViewById(R.id.entrySquatsStrength);
        TextView eCrunches = (TextView) findViewById(R.id.entryCrunchesStrength);
        Button btnAccept = (Button) findViewById(R.id.btnAcceptStrength);
        Button btnCancel = (Button) findViewById(R.id.btnCancelStrength);

        eSquats.setText(user.getSquats());
        ePushUps.setText(user.getPushUps());
        eCrunches.setText(user.getCrunches());

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int newSquats = Integer.parseInt(eSquats.getText().toString());
                    int newPushUps = Integer.parseInt(ePushUps.getText().toString());
                    int newCrunches = Integer.parseInt(eCrunches.getText().toString());

                    user.setSquats(newSquats);
                    user.setPushUps(newPushUps);
                    user.setCrunches(newCrunches);

                    db.updateUser(user.getName(), user.getGenre(), user.getSquats(), user.getPushUps(),
                            user.getCrunches(), user.getImc(), user.getHearRate());
                    Toast.makeText(getApplicationContext(), "New data saved!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Strength.this, MainActivity.class);
                    i.putExtra("USER",user);
                    startActivity(i);

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Error obtaining data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eSquats.setText(user.getSquats());
                ePushUps.setText(user.getPushUps());
                eCrunches.setText(user.getCrunches());
                finish();
            }
        });


    }
}