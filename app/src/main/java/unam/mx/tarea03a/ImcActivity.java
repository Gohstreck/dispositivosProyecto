package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class ImcActivity extends AppCompatActivity {
    short genre = 2;
    int age;
    float height, weight, imc;
    String imcRes, user;


    private void calculateIMC(float weight, float height){


        imc = weight/ (float) Math.pow(height,2);

        if (genre == 1) {
            if (imc < 20) {
                imcRes = "Malnourished";
            } else if (20 <= imc && imc < 25) {
                imcRes = "Normal";
            } else if (25 <= imc && imc < 30) {
                imcRes = "Overweight";
            } else if (30 <= imc && imc < 40) {
                imcRes = "Obesity";
            } else {
                imcRes = "Severe Obesity";
            }
        }
        if (genre ==2){
            if (imc < 19) {
                imcRes = "Malnourished";
            }else if(19 <= imc && imc <24) {
                imcRes = "Normal";
            }
            else if(24 <= imc && imc < 27) {
                imcRes = "Overweight";
            }
            else if(27 <= imc && imc < 32) {
                imcRes = "Obesity";
            }
            else{
                imcRes = "Severe Obesity";
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        Bundle extras = getIntent().getExtras();
        user = extras.getString("USER");
        Log.d("IMC", user);

        TextView userTextView = (TextView) findViewById(R.id.userIMC);
        userTextView.setText(user);
        Button calc = (Button) findViewById(R.id.btnCalcImc);
        calc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                TextView ageAux = (TextView) findViewById(R.id.eEdad);
                CharSequence ageCharAux = ageAux.getText();

                TextView heightAux = (TextView) findViewById(R.id.eAltura);
                CharSequence hCharAux = heightAux.getText();

                TextView weightAux = (TextView) findViewById(R.id.ePeso);
                CharSequence wCharAux = weightAux.getText();


                if(ageCharAux==null || ageCharAux.toString().isEmpty() ||
                    hCharAux == null || hCharAux.toString().isEmpty() ||
                    wCharAux == null || wCharAux.toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                age = Integer.parseInt(ageCharAux.toString());

                height = Float.parseFloat(hCharAux.toString());

                weight = Float.parseFloat(wCharAux.toString());

                calculateIMC(weight, height);


                TextView textImcRes = (TextView) findViewById(R.id.textImcRes);

                textImcRes.setText(imcRes);


            }});

        ImageButton home = (ImageButton) findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ImcActivity.this, MainActivity.class);
                i.putExtra("imcRes", imcRes);
                i.putExtra("imc", imc);
                startActivity(i);
            }
        });


    }




    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbtnHombre:
                if (checked)
                    Log.d("RADIO BTN", "Apretado hombre");
                    genre = 1;
                    break;
            case R.id.rbtnMujer:
                if (checked)
                    Log.d("RADIO BTN", "Apretado mujer");
                    genre = 2;
                    break;
        }

    }


}