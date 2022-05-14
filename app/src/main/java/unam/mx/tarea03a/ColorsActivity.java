package unam.mx.tarea03a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);


        SeekBar redBar = (SeekBar)findViewById(R.id.skRed); // make seekbar object
        SeekBar blueBar = (SeekBar)findViewById(R.id.skBlue); // make seekbar objct
        SeekBar greenBar = (SeekBar)findViewById(R.id.skGreen); // make seekbar object

        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                TextView redText = (TextView)findViewById(R.id.redAct);
                String toPost = "" + redBar.getProgress() ;
                redText.setText(toPost);
            }
        });


        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                TextView blueText = (TextView)findViewById(R.id.blueAct);
                String toPost = "" + blueBar.getProgress() ;
                blueText.setText(toPost);
            }
        });

        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                TextView greenText = (TextView)findViewById(R.id.greenAct);
                String toPost = "" + greenBar.getProgress();
                greenText.setText(toPost);
            }
        });

        Button btnAccept = (Button) findViewById(R.id.btnAceptar);
        Button btnCancel = (Button) findViewById(R.id.btnCancelar);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

}