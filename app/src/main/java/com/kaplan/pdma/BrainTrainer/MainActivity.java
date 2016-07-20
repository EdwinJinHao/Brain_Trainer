package com.kaplan.pdma.BrainTrainer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MediaPlayer Mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton go=(ImageButton)findViewById(R.id.imageButtongo);
        Mplayer= MediaPlayer.create(getApplicationContext(), R.raw.music);
        Mplayer.start();
        Mplayer.setLooping(true);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mplayer.stop();
                Intent selectscreen=new Intent(getApplicationContext(),selectscreen.class);
                startActivity(selectscreen);
            }
        });

    }
}
