package edu.cascadia.friendsfantrivia;

import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

/**
 * Quiz class
 * 
 * A Quiz object will consist of 5 Question objects. This class will provide an
 * easy way to create a quiz for each level of the trivia game.
 */
public class Quiz {
	
	private ArrayList<Question> questions;

	/**
	 * Constructor for Quiz objects
	 * @param curLevel - The current level that the player is on.
	 */
	public Quiz(int curLevel) {
		generateQuestions(curLevel); // generate 5 questions from the current level
	}
	
	/**
	 * Generate a randomized ArrayList of 5 Questions at the current level
	 * @param curLevel
	 */
	public void generateQuestions(int curLevel) {
		switch (curLevel) {
			// If current level is 1, add 5 level-one questions:
			case 1: addNewQuestion("Question1 text",
								   "Question1 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question2 text",
								   "Question2 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question3 text",
								   "Question3 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question4 text",
								   "Question4 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question5 text",
								   "Question5 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					Collections.shuffle(questions); // Shuffle the list of questions
					break;
			
			// If current level is 2, add 5 level-two questions:
			case 2: addNewQuestion("Question1 text",
								   "Question1 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question2 text",
								   "Question2 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question3 text",
								   "Question3 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question4 text",
								   "Question4 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question5 text",
								   "Question5 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					Collections.shuffle(questions); // Shuffle the list of questions
					break;
				
			// If current level is 3, add 5 level-three questions:
			case 3: addNewQuestion("Question1 text",
								   "Question1 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question2 text",
								   "Question2 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question3 text",
								   "Question3 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question4 text",
								   "Question4 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					addNewQuestion("Question5 text",
								   "Question5 answer",
								   "Incorrect answer 1",
								   "Incorrect answer 2",
								   "Incorrect answer 3");
					Collections.shuffle(questions); // Shuffle the list of questions
					break;
			
			// We shouldn't get to the default case, since the game just has 3 levels
			default: Log.d("generateQuestions", "curLevel = " + curLevel); // show in log
					break;
		}// end switch statement
	}
	
	/**
	 * Adds a new Question object to the Quiz
	 * @param question - the question String to add
	 * @param answer - the answer to this question
	 * @param incorrectAnswer1 - an incorrect answer to this question
	 * @param incorrectAnswer2 - an incorrect answer to this question
	 * @param incorrectAnswer3 - an incorrect answer to this question
	 */
	public void addNewQuestion(String question, String answer, String incorrectAnswer1,
			String incorrectAnswer2, String incorrectAnswer3) {
		ArrayList<String> incorrectAnswers = new ArrayList<String>();
		incorrectAnswers.add(incorrectAnswer1);
		incorrectAnswers.add(incorrectAnswer2);
		incorrectAnswers.add(incorrectAnswer3);
		// Add the Question object to the questions ArrayList
		questions.add(new Question(question, answer, incorrectAnswers));
	}
}
