package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class HeartRate extends AppCompatActivity {
    String user, hrRes;
    int puls, hr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        Bundle extra = getIntent().getExtras();
        user = extra.getString("USER");
        TextView userHR = (TextView) findViewById(R.id.userHR);
        userHR.setText(user);

        EditText heartPuls = (EditText) findViewById(R.id.txtHeartPulses);
        TextView heartRes = (TextView) findViewById(R.id.heartRes);
        heartPuls.setOnEditorActionListener((v, actionId, key) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                puls = Integer.parseInt(heartPuls.getText().toString());
                puls = puls * 4;
                hr = puls;
                if (hr <= 100 && hr >= 60){
                    hrRes = "Normal";
                }else if (hr + 10 >= 60  && hr <= 100){
                    hrRes = "Not too bad";
                }else if (hr - 10 <= 100) {
                    hrRes = "Not too bad";
                }else{
                    hrRes = "Bad. Try again or go to a medic.";
                }

                heartRes.setText(hrRes);

                // hide virtual keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
            return true;
        });


        ImageButton home = (ImageButton) findViewById(R.id.btnHome2);
        home.setOnClickListener(view -> {
            Intent i = new Intent(HeartRate.this, MainActivity.class);
            i.putExtra("hrRes", hrRes);
            i.putExtra("hr", hr);
            startActivity(i);
        });
    }
}