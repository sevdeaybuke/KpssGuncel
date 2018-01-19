package com.sevdeaybuke.kpssguncel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private Button answerButton;
    private RadioGroup radioGroup;
    private RadioButton a_option , b_option, c_option, d_option, e_option, rb_selected;
    private TextView questionText;
    private int currentQuestionIndex;
    private ArrayList<Question> questionArrayList;
    private DatabaseReference databaseReference;
    private int scoreNum = 0;
    private TextView scoreText;
    private TextView gameState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Init();

        databaseReference = FirebaseDatabase.getInstance().getReference("questions");

        questionArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Question question = postSnapshot.getValue(Question.class);
                    questionArrayList.add(question);
                }

                //currentQuestionIndex = 0;
                displayQuestion(currentQuestionIndex);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioGroup.getCheckedRadioButtonId()!=-1) {
                    controlAnswers();
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen bir seçim yapınız!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void controlAnswers() {

        if(answerCheck()){

            scoreNum = scoreNum + 5;
            scoreText.setText("PUANINIZ: "+ " " + String.valueOf(scoreNum));
            Toast.makeText(getApplicationContext(),"TEBRİKLER DOĞRU CEVAP",Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(getApplicationContext(),"ÜZGÜNÜM YANLIŞ CEVAP",Toast.LENGTH_SHORT).show();

        }

        goOn();

    }

    private void goOn() {

        currentQuestionIndex = (currentQuestionIndex + 1)%questionArrayList.size();

        if(currentQuestionIndex==0){

            gameState.setVisibility(View.VISIBLE);
            radioGroup.clearCheck();
            answerButton.setEnabled(false);
            return;
        }

        displayQuestion(currentQuestionIndex);

    }

    private boolean answerCheck() {

        String answer = "";
        int id = radioGroup.getCheckedRadioButtonId();
        rb_selected = (RadioButton)findViewById(id);
        if(rb_selected == a_option){
            answer = "a";
        }
        if(rb_selected == b_option){
            answer = "b";
        }
        if(rb_selected == c_option){
            answer = "c";
        }
        if(rb_selected == d_option){
            answer = "d";
        }
        if(rb_selected == e_option){
            answer = "e";
        }

        return questionArrayList.get(currentQuestionIndex).isCorrectAnswer(answer);
    }

    private void Init() {

        answerButton = (Button)findViewById(R.id.answerButton);
        questionText = (TextView)findViewById(R.id.questionText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        a_option = (RadioButton)findViewById(R.id.a_option);
        b_option = (RadioButton)findViewById(R.id.b_option);
        c_option = (RadioButton)findViewById(R.id.c_option);
        d_option = (RadioButton)findViewById(R.id.d_option);
        e_option = (RadioButton)findViewById(R.id.e_option);
        scoreText = (TextView)findViewById(R.id.scoreText);
        gameState = (TextView)findViewById(R.id.gameState);
        radioGroup.clearCheck();

    }

    private void displayQuestion(int pos){

        radioGroup.clearCheck();
        questionText.setText(questionArrayList.get(pos).getQuestionText());
        a_option.setText(questionArrayList.get(pos).getChoice_a());
        b_option.setText(questionArrayList.get(pos).getChoice_b());
        c_option.setText(questionArrayList.get(pos).getChoice_c());
        d_option.setText(questionArrayList.get(pos).getChoice_d());
        e_option.setText(questionArrayList.get(pos).getChoice_e());

    }
}
