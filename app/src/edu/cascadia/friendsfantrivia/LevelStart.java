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
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class LevelStart extends Activity {
	
	// Instance variables
	private int curLevel; // The level player is currently on
	private int curScore; // Player's current score
	
	// UI elements
	private LinearLayout levelStartRootLayout; // Top-level layout (set background)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_start);
		
		// Get the data from the intent. Values will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		curScore = getIntent().getIntExtra("curScore", -1);
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
		
		// Put curLevel as startButton text (Example: "Level 2")
		ImageButton startButton = (ImageButton)findViewById(R.id.startButton);
		startButton.setTag("Start Level " + curLevel);
		
		// Add OnClickListener to "Start Level x" button
		startButton.setOnClickListener(startLevelButtonListener);
		
		// Show the correct background image for the next level
		levelStartRootLayout = (LinearLayout)findViewById(R.id.levelStartRootLayout);
		if (curLevel == 1) {
			levelStartRootLayout.setBackgroundResource(R.drawable.level1);
		} else if (curLevel == 2) {
			levelStartRootLayout.setBackgroundResource(R.drawable.level2);
		} else { // curLevel == 3
			levelStartRootLayout.setBackgroundResource(R.drawable.level3);
		}
	}// end onCreate
	
	// OnClickListener for "Play!" button. Calls startGame method.
	public OnClickListener startLevelButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startNextLevel(v); // start the next level when player taps button
		}
	};// end playButtonListener
	
	// Starts the next level (goes to PlayGameActivity)
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