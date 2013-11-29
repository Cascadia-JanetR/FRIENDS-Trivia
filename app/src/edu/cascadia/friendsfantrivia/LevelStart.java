/*
 * LevelStart.java
 * 
 * Activity where user can start the current level (there are 3 levels), so this
 * screen appears before the start of the first level, and in between levels
 * 2 and 3.
 */
package edu.cascadia.friendsfantrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LevelStart extends Activity {
	
	// Instance variables
	private int curLevel; // The level player is currently on
	private int curScore; // Player's current score

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_start);
		
		// Get the current level and score from the intent
		// curLevel/curScore will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		curScore = getIntent().getIntExtra("curScore", -1);
		// Test toast - make sure we got the correct current level and score
		//Toast.makeText(this, Integer.toString(curLevel), Toast.LENGTH_SHORT).show();
		// Toast.makeText(this, Integer.toString(curScore), Toast.LENGTH_SHORT).show();
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
		
		// Put curLevel as startButton text (Example: "Level 2")
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setText("Start Level " + curLevel);
		// Add OnClickListener to "Start Level x" button
		startButton.setOnClickListener(startLevelButtonListener);
	}// end onCreate
	
	// OnClickListener for "Play!" button. Calls startGame method.
	public OnClickListener startLevelButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startNextLevel(v); // start the next level when player taps button
		}
	};// end playButtonListener
	
	public void startNextLevel(View v) {
		Intent intent = new Intent(this, PlayGameActivity.class); // create a new intent
		intent.putExtra("curLevel", curLevel); // add current level to the intent
		intent.putExtra("curScore", curScore); // add current score to the intent
		startActivity(intent); // switch to PlayGameActivity
	}// end startNextLevel method

}// end class


/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry NGoy
 */