/*
 * LevelStart.java
 * 
 * Activity where user can start the current level (there are 3 levels), so this
 * screen appears before the start of the first level, and in between levels
 * 2 and 3.
 */
package edu.cascadia.friendsfantrivia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class LevelStart extends Activity {
	
	// Instance variables
	int curLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_start);
		
		// Get the current level from the intent
		// curLevel will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		// Test toast - make sure we got the correct current level
		//Toast.makeText(this, Integer.toString(curLevel), Toast.LENGTH_SHORT).show();
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
		
		// Put curLevel as startButton text (Example: "Level 2")
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setText("Level " + curLevel);
	}// end onCreate

}// end class


/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry NGoy
 */