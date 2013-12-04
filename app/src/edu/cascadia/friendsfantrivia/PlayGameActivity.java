/*
 * PlayGameActivity.java
 * 
 * Activity where the user answers 5 questions.
 */

package edu.cascadia.friendsfantrivia;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGameActivity extends Activity {
	
	// Instance variables
	private int curLevel; // level player is currently on
	private int curScore; // player's current score
	private int highScore; // player's high score
	private Quiz curQuiz; // the current Quiz the user is taking
	private int curQuestionNum; // the current question number the player is on
	private Question curQuestion; // the current Question the user is on
	private ArrayList<String> possibleAnswers; // the list of possible answers to curQuestion
	
	// UI variables
	private TextView pointValueTextView; // TextView that displays the point value
	private TextView playerScoreTextView; // TextView that displays the player's score
	private TextView questionTextView; // TextView that displays current question
	private Button answerButton1; // the first answer Button (top)
	private Button answerButton2; // the 2nd answer Button
	private Button answerButton3; // the 3rd answer Button
	private Button answerButton4; // the 4th answer Button (bottom)
	private ImageView progressAnswer1ImageView; // 1st (left) question progress icon
	private ImageView progressAnswer2ImageView;
	private ImageView progressAnswer3ImageView;
	private ImageView progressAnswer4ImageView;
	private ImageView progressAnswer5ImageView;
	
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
		
		// Get all the UI elements
		pointValueTextView = (TextView)findViewById(R.id.pointValueTextView); // point value
		playerScoreTextView = (TextView)findViewById(R.id.playerScoreTextView); // player's score
		questionTextView = (TextView)findViewById(R.id.questionTextView); // question text
		answerButton1 = (Button)findViewById(R.id.answerButton1); // top answer button
		answerButton2 = (Button)findViewById(R.id.answerButton2);
		answerButton3 = (Button)findViewById(R.id.answerButton3);
		answerButton4 = (Button)findViewById(R.id.answerButton4); // bottom answer button
		progressAnswer1ImageView = (ImageView)findViewById(R.id.progressAnswer1ImageView); // first question progress icon
		progressAnswer2ImageView = (ImageView)findViewById(R.id.progressAnswer2ImageView);
		progressAnswer3ImageView = (ImageView)findViewById(R.id.progressAnswer3ImageView);
		progressAnswer4ImageView = (ImageView)findViewById(R.id.progressAnswer4ImageView);
		progressAnswer5ImageView = (ImageView)findViewById(R.id.progressAnswer5ImageView);
		
		// Display point value (in upper-left)
		pointValueTextView.setText(getPointValue(curLevel) + " points");
		// Display player's current score (in upper-right)
		playerScoreTextView.setText("Score: " + curScore);
		// Display each question progress icon as not answered (at the bottom)
		progressAnswer1ImageView.setImageResource(R.drawable.ic_not_answered);
		progressAnswer2ImageView.setImageResource(R.drawable.ic_not_answered);
		progressAnswer3ImageView.setImageResource(R.drawable.ic_not_answered);
		progressAnswer4ImageView.setImageResource(R.drawable.ic_not_answered);
		progressAnswer5ImageView.setImageResource(R.drawable.ic_not_answered);
		
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
		questionTextView.setText(curQuestion.getQuestionString());
		
		// Display the answers in the answer buttons
		answerButton1.setText(possibleAnswers.get(0));
		answerButton2.setText(possibleAnswers.get(1));
		answerButton3.setText(possibleAnswers.get(2));
		answerButton4.setText(possibleAnswers.get(3));
		
		// TODO: Add a CountDownTimer, set up what should happen when it
		// ends (wrong answer), on each tick (update timer TextView etc.).
	}// end loadNextQuestion
	
	// Called when user taps an answer button
	public void answerQuestion(View view) {
		// Get which button was tapped
		Button answerButton = (Button)view;
		// Get the tapped button's text as a String
		String answerText = answerButton.getText().toString();
		
		// Handle correct/incorrect answer (color, sound, vibrate?,
		// update icon at bottom)
		if (curQuestion.isCorrectAnswer(answerText)) { // if answer is correct:
			// TODO: button turns green
			// TODO: play good sound
			curScore += getPointValue(curLevel); // add correct number of points
			playerScoreTextView.setText("Score: " + curScore); // Update player's current score
			// update the question progress icon (at bottom) - true means correct
			updateQuestionProgressIcon(curQuestionNum, true);
		} else { // if answer is wrong:
			// TODO: button turns red
			// TODO: play bad sound
			// TODO: device vibrates (if device supports it)
			// update the question progress icon (at bottom) - false means incorrect
			updateQuestionProgressIcon(curQuestionNum, false);
			// No score added
		}
		
		// TODO: Cancel the CountDownTimer (if necessary)
		
		// TODO: Slight delay before going to next screen or loading next question
		
		
		// Check if level is over
		if (levelOver()) {
			// First check if game is over, if so, check for new high score and
			// go to GameOverActivity
			if (gameOver()) {
				// Test Toast
				Context context = getApplicationContext();
				CharSequence text = "Game Over! Go to GameOverActivity";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				// TODO: Check for high score and overwrite if necessary,
				// TODO: Add GameOverActivity, and go to it here
			} else {
				// Otherwise to to the next level
				curLevel++; // Increment the current level
				Intent intent = new Intent(this, LevelStart.class); // create a new intent
				intent.putExtra("curLevel", curLevel); // add current level into the intent
				intent.putExtra("curScore", curScore); // add current score to the intent
				intent.putExtra("highScore", highScore); // add high score to the intent
				startActivity(intent); // start the LevelStart activity
			}
		}
		// Level is not over. Increment curQuestion and load the next question
		else {
			++curQuestionNum;
			loadNextQuestion();
		}
	}// end answerQuestion
	
	// Method to determine if the current level is over
	public boolean levelOver() {
		// if the current question number equals the number of questions
		// then level is over. Return true, otherwise return false.
		if (curQuestionNum == NUM_QUESTIONS) {
			return true;
		}
		return false;
	}// end levelOver
	
	// Method to determine if the game is over
	public boolean gameOver() {
		// if the current level equals the number of levels, game is over,
		// return true, otherwise, return false
		if (curLevel == NUM_LEVELS)
			return true;
		return false;
	}// end gameOver
	
	/**
	 * Determines the point value of the current question
	 * @param curLevel - The current level the player is on.
	 * @return - The point value of the questions at the current level.
	 */
	public int getPointValue(int curLevel) {
		if (curLevel == 1)
			return LEVEL_1_PTS;
		if (curLevel == 2)
			return LEVEL_2_PTS;
		if (curLevel == 3)
			return LEVEL_3_PTS;
		// if we get here, there was a problem, return -1
		return -1;
	}
	
	/**
	 * Updates the icons at the bottom of the screen with
	 * If user got the answer right, it's a green box, if wrong, it's a red box
	 * @param curQuestionNum - The current question number
	 * @param correct - Was the answer correct?
	 */
	public void updateQuestionProgressIcon(int curQuestionNum, boolean correct) {
		// Get current question number. If correct, set image resource to correct
		// icon, otherwise set it to the incorrect icon
		switch (curQuestionNum) {
			case 1: if (correct)
						progressAnswer1ImageView.setImageResource(R.drawable.ic_correct);
					else
						progressAnswer1ImageView.setImageResource(R.drawable.ic_incorrect);
					break;
			case 2: if (correct)
						progressAnswer2ImageView.setImageResource(R.drawable.ic_correct);
					else
						progressAnswer2ImageView.setImageResource(R.drawable.ic_incorrect);
					break;
			case 3: if (correct)
						progressAnswer3ImageView.setImageResource(R.drawable.ic_correct);
					else
						progressAnswer3ImageView.setImageResource(R.drawable.ic_incorrect);
					break;
			case 4: if (correct)
						progressAnswer4ImageView.setImageResource(R.drawable.ic_correct);
					else
						progressAnswer4ImageView.setImageResource(R.drawable.ic_incorrect);
					break;
			case 5: if (correct)
						progressAnswer5ImageView.setImageResource(R.drawable.ic_correct);
					else
						progressAnswer5ImageView.setImageResource(R.drawable.ic_incorrect);
					break;
			default: // shouldn't get here
					break;
		}
	}// end updateQuestionProgressIcon

//  No menu for right now
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.play_game, menu);
//		return true;
//	}

}
