/*
 * PlayGameActivity.java
 * 
 * Activity where the user answers 5 questions.
 */

package edu.cascadia.friendsfantrivia;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlayGameActivity extends Activity {
	
	// Instance variables
	int curLevel; // level player is currently on
	int curScore; // player's current score

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		// Get data from intent
		// Will be -1 if there's a problem
		curLevel = getIntent().getIntExtra("curLevel", -1);
		curScore = getIntent().getIntExtra("curScore", -1);
		// Test toast - make sure we got the correct current level
		//Toast.makeText(this, Integer.toString(curLevel), Toast.LENGTH_SHORT).show();
		
		// Put curLevel as the title in the action bar
		setTitle("Level " + curLevel);
	}// end onCreate

//  No menu for right now
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.play_game, menu);
//		return true;
//	}

}
