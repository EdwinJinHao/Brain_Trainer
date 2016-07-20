package com.kaplan.pdma.BrainTrainer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class mutiplyscreen extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    Button selectChallenge;
    RelativeLayout gameRelativeLayout;
    MediaPlayer correct;
    MediaPlayer error;
    MediaPlayer resultsound;
    MediaPlayer bgmusic;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutiplyscreen);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        selectChallenge = (Button)findViewById(R.id.selectChallengeButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
    }

    public void playAgain(View view) {

        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        selectChallenge.setVisibility(View.INVISIBLE);

        bgmusic= MediaPlayer.create(getApplicationContext(), R.raw.music);
        bgmusic.start();
        bgmusic.setLooping(true);

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }
            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                resultsound= MediaPlayer.create(getApplicationContext(), R.raw.tada);
                resultsound.start();
                bgmusic.stop();

                playAgainButton.setVisibility(View.VISIBLE);

                selectChallenge.setVisibility(View.VISIBLE);
                selectChallenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bgmusic.stop();
                        Intent selectscreen=new Intent(getApplicationContext(),selectscreen.class);
                        startActivity(selectscreen);
                    }
                });
            }
        }.start();
    }

    public void generateQuestion() {

        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " x " + Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a * b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a * b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
            correct= MediaPlayer.create(getApplicationContext(), R.raw.applause);
            correct.start();
        } else {
            resultTextView.setText("Wrong!");
            error= MediaPlayer.create(getApplicationContext(), R.raw.error2);
            error.start();
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

}
