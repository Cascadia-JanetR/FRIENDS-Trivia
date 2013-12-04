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
		questions = new ArrayList<Question>(); // create new ArrayList of Questions
		generateQuestions(curLevel); // generate 5 questions from the current level
	}
	
	/**
	 * Generate a randomized ArrayList of 5 Questions at the current level
	 * @param curLevel
	 */
	public void generateQuestions(int curLevel) {
		switch (curLevel) {
			// If current level is 1, add 5 level-one questions:
			case 1: addNewQuestion("How many babies does Phoebe have?",
								   "Three",
								   "Two",
								   "One",
								   "Zero");
					addNewQuestion("How many kids does Ross have?",
								   "2",
								   "3",
								   "1",
								   "0");
					addNewQuestion("Why can't Chandler dance at his wedding?",
								   "His shoes are too slippery",
								   "He doesn't know how",
								   "No dance partner",
								   "He didn't like the music");
					addNewQuestion("Whose pants does Joey wear as eating pants?",
								   "Phoebe's",
								   "Ross's",
								   "Chandler's",
								   "Rachel's");
					addNewQuestion("Monica has how many catagories of towels?",
								   "11",
								   "7",
								   "5",
								   "9");
					Collections.shuffle(questions); // Shuffle the list of questions
					break;
			
			// If current level is 2, add 5 level-two questions:
			case 2: addNewQuestion("Ross's first wife was?",
								   "Carol",
								   "Emily",
								   "Rachel",
								   "Julie");
					addNewQuestion("Who is Ross's second wife?",
								   "Emily",
								   "Carol",
								   "Rachel",
								   "Julie");
					addNewQuestion("How many times has Ross been divorced?",
								   "3 times",
								   "2 times",
								   "4 times",
								   "1 time");
					addNewQuestion("What game show does Joey audition to be the host of?",
								   "Bamboozled",
								   "Fireball",
								   "Price is Right",
								   "Wango Tango");
					addNewQuestion("Who's name is on the TV Guide delivered to Joey and Chandler's apartment?",
								   "Miss Chanandler Bong",
								   "Mr. Chandler Bing",
								   "Joey Tribbani",
								   "Monica Gellar");
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
	
	/**
	 * Gets the next Question object from the list.
	 * @return the first Question object in the list
	 *         this will return null if the list is empty
	 */
	public Question getNextQuestion() {
		if (questions.isEmpty()) {
			return null;
		}
		return questions.remove(0); // remove a Question from the list and return it
	}
	
}// end Quiz class

/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry NGoy
 */
