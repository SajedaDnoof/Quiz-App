package com.jawx.android.quizapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class quizActivity extends AppCompatActivity {
    TextView QuestionsTextView;
    RadioButton T,F;
    RadioGroup group;
    Button showScore;
    CheckBox ch1 , ch2, ch3;
    EditText mEditText;
    int i = 0 , j=0 ;
    int score=0 ;
    boolean [] radioAnswers = {false, false, true, false, true, true, false};
    String [] questions = {
             "1) M416 is a sniper"
            ,"2) grenades has no time limits"
            ,"3) you can find ghillie suit  only in the plane drop"
            ,"4) 4X is the largest scope in the game"
            ,"5) AKM takes 7.62mm  ammo"
            ,"6) AWM is stronger than Kar89"
            ,"7) you cant play solo VS squads"
            ,"8) what scopes are in the game"
            ,"9) which maps are in the game"
            ,"10) write the max possible number of players in one team"
    };
    String [] checkAnswers = {
            "Red Dot" , "5X" , "8X",
            "Miramar", "Hawaii", "Sanhok"
    };
    String lastQuestionAnswer="4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        QuestionsTextView = (TextView) findViewById(R.id.Questions);
        T = (RadioButton) findViewById(R.id.Rbtn_T);
        F = (RadioButton) findViewById(R.id.Rbtn_F);
        group = (RadioGroup) findViewById(R.id.rb_groupe);
        ch1 = (CheckBox) findViewById(R.id.cb_st);
        ch2 = (CheckBox) findViewById(R.id.cb_nd);
        ch3 = (CheckBox) findViewById(R.id.cb_rd);
        showScore = (Button) findViewById(R.id.btn_score);
        mEditText = (EditText) findViewById(R.id.et_last_question);
        showNextQuestion();
    }

    public void showNextQuestion(){
        QuestionsTextView.setText(questions[i]);
        if(i == 7 || i == 8){
            ch1.setText(checkAnswers[j++]);
            ch2.setText(checkAnswers[j++]);
            ch3.setText(checkAnswers[j++]);
        }
    }

    public void checkAnswer(View view) {
        if (i <= 6) {
            if(T.isChecked() || F.isChecked()) {
                if ((radioAnswers[i] && T.isChecked()) || (!radioAnswers[i] && !T.isChecked())) {
                    score++;
                }
                if(i==6) nextPart(1);
                 group.clearCheck();
                    i++;
                    showNextQuestion();
            }
        }
        else if(i <= 8){
            checkCHECKBOXES();
            if(i==8) nextPart(2);
            i++;
            showNextQuestion();
        }
        else {
            checkEditText();
            done(view);
        }
    }

    private void checkCHECKBOXES() {
        if (ch1.isChecked() && !ch2.isChecked() && ch3.isChecked()){
            score++;
        }
        ch1.setChecked(false);
        ch2.setChecked(false);
        ch3.setChecked(false);
    }

    private void checkEditText() {
        if (mEditText.getText().toString().equals(lastQuestionAnswer)) {
            score++;
        }
    }

    private void nextPart(int partNum) {
        if(partNum == 1) {
            T.setVisibility(GONE);
            F.setVisibility(GONE);
            ch1.setVisibility(View.VISIBLE);
            ch2.setVisibility(View.VISIBLE);
            ch3.setVisibility(View.VISIBLE);
        }else {
            ch1.setVisibility(View.GONE);
            ch2.setVisibility(View.GONE);
            ch3.setVisibility(View.GONE);
            mEditText.setVisibility(View.VISIBLE);
        }
    }

    private void done(View view) {
        mEditText.setVisibility(GONE);
        view.setVisibility(GONE);
        showScore.setVisibility(View.VISIBLE);
        QuestionsTextView.setText("you have finished the quiz , WELL DONE");
    }

    public void displayScore(View view) {
        Toast.makeText(this, "Your Score : " + score + " / 10", Toast.LENGTH_SHORT).show();

    }
}
