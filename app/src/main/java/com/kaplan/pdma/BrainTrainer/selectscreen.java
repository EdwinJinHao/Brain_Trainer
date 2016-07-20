package com.kaplan.pdma.BrainTrainer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class selectscreen extends AppCompatActivity {

    MediaPlayer Mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectscreen);

        ImageButton plus=(ImageButton)findViewById(R.id.imageButtonplus);
        ImageButton mutiply=(ImageButton)findViewById(R.id.imageButtonmutiply);
        Mplayer= MediaPlayer.create(getApplicationContext(), R.raw.music);
        Mplayer.start();
        Mplayer.setLooping(true);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mplayer.stop();
                Intent plusscreen=new Intent(getApplicationContext(),plusscreen.class);
                startActivity(plusscreen);
            }
        });

        mutiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mplayer.stop();
                Intent mutiplyscreen=new Intent(getApplicationContext(),mutiplyscreen.class);
                startActivity(mutiplyscreen);
            }
        });


    }
}
