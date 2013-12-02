/*
 * PlayGameActivity.java
 * 
 * Activity where the user answers 5 questions.
 */

package edu.cascadia.friendsfantrivia;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PlayGameActivity extends Activity {
	
	// Instance variables
	private int curLevel; // level player is currently on
	private int curScore; // player's current score
	private Quiz curQuiz; // the current Quiz the user is taking
	private Question curQuestion; // the current Question the user is on
	private ArrayList<String> possibleAnswers; // the list of possible answers to curQuestion
	private TextView questionTextView; // the TextView that displays current question
	private Button answerButton1; // the first answer Button (top)
	private Button answerButton2; // the 2nd answer Button
	private Button answerButton3; // the 3rd answer Button
	private Button answerButton4; // the 4th answer Button (bottom)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		// Get data from intent
		// Will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		curScore = getIntent().getIntExtra("curScore", -1);
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
		
		// Create a new Quiz using the current level
		curQuiz = new Quiz(curLevel);
		
		// Get the current Question
		curQuestion = curQuiz.getNextQuestion();
		// Get the list of possible answers to the current question
		possibleAnswers = new ArrayList<String>(curQuestion.getAllShuffledAnswers());
		
		// Display the first question in questionTextView
		questionTextView = (TextView)findViewById(R.id.questionTextView);
		questionTextView.setText(curQuestion.getQuestionString());
		
		// Display the answers in the answer buttons
		answerButton1 = (Button)findViewById(R.id.answerButton1);
		answerButton1.setText(possibleAnswers.get(0));
		answerButton2 = (Button)findViewById(R.id.answerButton2);
		answerButton2.setText(possibleAnswers.get(1));
		answerButton3 = (Button)findViewById(R.id.answerButton3);
		answerButton3.setText(possibleAnswers.get(2));
		answerButton4 = (Button)findViewById(R.id.answerButton4);
		answerButton4.setText(possibleAnswers.get(3));
	}// end onCreate

//  No menu for right now
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.play_game, menu);
//		return true;
//	}

}
