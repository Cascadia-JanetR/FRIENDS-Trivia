 /*
  * StartActivity.java
  * 
  * Launch activity (home screen). Player can start playing a game
  * of trivia from here.
  */
package edu.cascadia.friendsfantrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {
	
	// Instance variables
	private int curLevel; // the current level the player is on (starts at 1)

	// This is called when the app first starts
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		curLevel = 1; // Player always starts at level 1
		
		// Add OnClickListener to playButton
		Button playButton = (Button) findViewById(R.id.playButton);
		playButton.setOnClickListener(playButtonListener);
	}// end onCreate
	
	// OnClickListener for "Play!" button. Calls startGame method.
	public OnClickListener playButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startGame(v); // start the game
		}
	};// end playButtonListener
	
	// Starts the game (launch the StartActivity activity)
	public void startGame(View v) {
		Intent intent = new Intent(this, LevelStart.class); // create a new intent
		intent.putExtra("curLevel", curLevel); // add current level into the intent
		startActivity(intent); // start the LevelStart activity
	}// end startGame
	
}// end class


/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry NGoy
 */