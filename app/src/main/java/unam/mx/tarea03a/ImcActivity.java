package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ImcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbtnHombre:
                if (checked)
                    Log.d("RADIO BTN", "Apretado hombre");
                    break;
            case R.id.rbtnMujer:
                if (checked)
                    Log.d("RADIO BTN", "Apretado mujer");
                    break;
        }
    }


}