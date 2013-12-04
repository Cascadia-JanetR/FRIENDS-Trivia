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
	private int highScore; // player's high score
	private Quiz curQuiz; // the current Quiz the user is taking
	private int curQuestionNum; // the current question number the player is on
	private boolean gameOver; // boolean to determine if the game is over yet
	private Question curQuestion; // the current Question the user is on
	private ArrayList<String> possibleAnswers; // the list of possible answers to curQuestion
	private TextView questionTextView; // the TextView that displays current question
	private Button answerButton1; // the first answer Button (top)
	private Button answerButton2; // the 2nd answer Button
	private Button answerButton3; // the 3rd answer Button
	private Button answerButton4; // the 4th answer Button (bottom)
	
	// Constants
	private final int LEVEL_1_PTS = 100; // Level 1 questions are worth 100 pts
	private final int LEVEL_2_PTS = 200; // Level 2 questions are worth 200 pts
	private final int LEVEL_3_PTS = 300; // Level 3 questions are worth 300 pts
	private final int NUM_QUESTIONS = 5; // Number of questions per level
	private final int NUM_LEVELS = 3; // Number of levels in the game

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		// Get data from intent
		// Will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		curScore = getIntent().getIntExtra("curScore", -1);
		highScore = getIntent().getIntExtra("highScore", -1);
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
		
		// Create a new Quiz using the current level
		curQuiz = new Quiz(curLevel);
		
		// Set the current question number to 1
		curQuestionNum = 1;
		
		// Load the first question
		loadNextQuestion();
	}// end onCreate
	
	// Method to load the next question
	public void loadNextQuestion() {
		// Set the current Question
		curQuestion = curQuiz.getNextQuestion();
		// Get the list of possible answers to the current question
		possibleAnswers = new ArrayList<String>(curQuestion.getAllShuffledAnswers());
		
		// Display the question in questionTextView
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
	}// end loadNextQuestion
	
	// Method to handle answering a question (when user taps an answer button)
	public void answerQuestion(Button answerButton) {
		// Handle correct/incorrect answer
		// Check if game is over, if so, check for new high score and go to GameOverActivity
		// Check if level is over, if so, go to LevelStart Activity
		// Otherwise, increment curQuestion and load the next question
	}// end answerQuestion
	
	// Method to determine if the current level is over
	public boolean levelOver() {
		// if the current question number is greater than the number of questions
		// then return true, otherwise return false
		if (curQuestionNum > NUM_QUESTIONS) {
			curLevel++; // Increment the current level
			return true;
		}
		return false;
	}// end levelOver
	
	// Method to determine if the game is over
	public boolean gameOver() {
		// if the current level is greater than the number of levels, return true,
		// otherwise, return false
		if (curLevel > NUM_LEVELS)
			return true;
		return false;
	}// end gameOver

//  No menu for right now
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.play_game, menu);
//		return true;
//	}

}
