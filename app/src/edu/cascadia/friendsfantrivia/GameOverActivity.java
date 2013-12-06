/*
 * GameOverActivity.java
 * 
 * Activity that shows Game Over, the player's score, their high score, and
 * their rank.
 */

package edu.cascadia.friendsfantrivia;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {
	
	// Instance Variables
	private int score; // player's score
	private int highScore; // player's best score
	private int rank; // player's rank
	
	// Constants
	public static final int AVID_FAN = 1; // Rank 1
	public static final int FANATIC = 2; // Rank 2
	public static final int MASTER = 3; // Rank 3
	public static final String SAVED_PREFS = "SavedPrefs"; // name of SharedPreferences file
	public static final String HIGH_SCORE = "highScore";
	public static final String RANK = "rank";
	
	// UI elements
	TextView yourScoreValueTextView; // Shows the player's score
	TextView highScoreValueTextView; // Shows the player's high score
	TextView newHighScoreTextView; // Shows text "New High Score!"
	TextView rankTextView; // Shows text "Rank:"
	TextView rankMessageTextView; // Shows player's rank
	Button playAgainButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Get data from intent. Values will be -1 if there's a problem.
		score = getIntent().getIntExtra("curScore", -1);
		
		SharedPreferences prefs = this.getSharedPreferences(SAVED_PREFS, MODE_PRIVATE);
		// Get the player's rank from SharedPreferences
		rank = prefs.getInt(RANK, AVID_FAN); // default rank is AVID_FAN
		
		// Get the UI elements
		yourScoreValueTextView = (TextView)findViewById(R.id.yourScoreValueTextView);
		highScoreValueTextView = (TextView)findViewById(R.id.highScoreValueTextView);
		newHighScoreTextView = (TextView)findViewById(R.id.newHighScoreTextView);
		rankTextView = (TextView)findViewById(R.id.rankTextView);
		rankMessageTextView = (TextView)findViewById(R.id.rankMessageTextView);
		playAgainButton = (Button)findViewById(R.id.playAgainButton);
		
		// Set the typeface of the rank message to the Friends font
		Typeface friendsTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/FRIENDS_.TTF");
	    rankMessageTextView.setTypeface(friendsTypeface);
	    
	    // Display player's score in yourScoreValueTextView
	    yourScoreValueTextView.setText(String.valueOf(score));
	    
	    // Get player's high score from SharedPreferences
	    highScore = prefs.getInt(HIGH_SCORE, 0); // default high score is 0
	    
	    // If player beat their high score, save it, display "New High Score!",
	    // and determine player's rank
	    if (score > highScore) {
	    	rank = determineRank(score);
	    	saveHighScoreAndRank(prefs, score, rank);
	    	newHighScoreTextView.setVisibility(View.VISIBLE); // make the string visible
	    } else { // player didn't beat high score
	    	newHighScoreTextView.setVisibility(View.INVISIBLE); // "new high score" invisible
	    }
	    
	    // Get player's high score from SharedPreferences again
	    highScore = prefs.getInt(HIGH_SCORE, 0); // default high score is 0
	    // Display player's high score in highScoreValueTextView
	    highScoreValueTextView.setText(String.valueOf(highScore));
	    
	    // Display appropriate rank message in rankMessageTextView
	    rankMessageTextView.setText(getRankString(rank));
	    
	}// end onCreate
	
	/**
	 * Saves the player's high score and rank to SharedPreferences
	 * @param prefs - user's SharedPreferences file
	 * @param score - player's score
	 * @param rank - player's rank
	 */
	public void saveHighScoreAndRank(SharedPreferences prefs, int score, int rank) {
		Editor editor = prefs.edit(); // use Editor object to edit SharedPreferences
		editor.putInt(HIGH_SCORE, score); // put new high score
		editor.putInt(RANK, rank); // put new rank
		editor.commit(); // commit changes
	}
	
	/**
	 * Determines the player's rank, given their score
	 * @param score - player's score
	 * @return - an int that represents the player's rank
	 */
	public int determineRank(int score) {
		// if score < something, return AVID_FAN
		// if score >= something <= something2 return FANATIC
		// else return MASTER
		if (score < 1000) {
			return AVID_FAN;
		} else if (score >= 1000 && score < 2000) {
			return FANATIC;
		} else { // score >= 2000
			return MASTER;
		}
	}
	
	/**
	 * Returns the correct String from resources according to the player's rank
	 * @param rank - the player's rank
	 * @return - A String message indicating the player's rank (i.e. "You are
	 * 			 a FRIENDS master!"
	 */
	public String getRankString(int rank) {
		if (rank == AVID_FAN) {
			return getString(R.string.rankmessage_avidfan);
		} else if (rank == FANATIC) {
			return getString(R.string.rankmessage_fanatic);
		} else { // rank == MASTER
			return getString(R.string.rankmessage_master);
		}
	}
	
	/**
	 * This method is called when the user taps the "Play Again!" Button.
	 * Switches back to the StartActivity.
	 * @param view - the View that was tapped
	 */
	public void playAgain(View view) {
		Intent intent = new Intent(this, StartActivity.class); // create Intent
		startActivity(intent); // start the StartActivity Activity
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_over, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}// end class


/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry Ngoy
 */
