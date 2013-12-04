package edu.cascadia.friendsfantrivia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Question class
 * 
 * A Quiz will be made up of 5 Question objects for the Friends trivia app. Each
 * Question object will consist of the question String, correct answer String, and
 * an ArrayList of incorrect answer Strings.
 */
public class Question {
	
	private final String question;
	private final String correctAnswer;
	private final ArrayList<String> incorrectAnswers;
	
	/**
	 * Constructor for Question objects
	 * 
	 * @param question - The question String.
	 * @param correctAnswer - The correct answer String.
	 * @param incorrectAnswers - An ArrayList of incorrect answer Strings.
	 */
	public Question(String question, String correctAnswer, ArrayList<String> incorrectAnswers) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = new ArrayList<String>(incorrectAnswers);
	}
	
	/**
	 * Get the question String for this Question.
	 * @return question String.
	 */
	public String getQuestionString() {
		return question;
	}
	
	/**
	 * Get a shuffled list of possible answers for this Question.
	 * @return a shuffled list of possible answers for this Question.
	 */
	public List<String> getAllShuffledAnswers() {
		List<String> result = new ArrayList<String>();
		result.addAll(incorrectAnswers); // add all the incorrect answers
		result.add(correctAnswer); // add the correct answer
		Collections.shuffle(result); // shuffle the list
		return result; // return the list of Strings
	}
	
	/**
	 * Determine if the user's answer to the question is correct.
	 * @param userAnswer - The user's answer
	 * @return - true if user's answer is the same as correctAnswer
	 *           false otherwise
	 */
	public boolean isCorrectAnswer(String userAnswer) {
		if (userAnswer.equals(correctAnswer)) {
			return true;
		} else {
			return false;
		}
	}
	
}// end Question class

/*
 * Friends Fan Trivia app
 * 
 * 
 * Cascadia Community College - BIT 272 (Mobile App Development) - Fall 2013
 * Ben Harrison, Janet Rasque, Henry Ngoy
 */