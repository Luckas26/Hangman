
import java.util.Scanner;

public class Hangman {
	
	// instance variables
	private String secretWord, guess, progress = "", guessedLetters = "", person;
	private String copySecretWord;
	private int bad = 0;  // initial value 

	private String top =        " ________\n";  // scaffold top arm
	private String ropeOrBody = " |      |\n";  // rope or body
	private String head =       " |      O\n";  // head
	private String leftArm =    " |  >---|\n";  // left arm
	private String bothArms =   " |  >---|---<\n";  // both arms
	private String leftLeg1 =   " |     /\n";  // left leg
	private String leftLeg2 =   " |    /\n";  // left leg
	private String leftLeg3 =   " |   d\n";  // left leg
	private String bothLegs1 =  " |     / \\\n";  // both legs
	private String bothLegs2 =  " |    /   \\\n";  // both legs
	private String bothLegs3 =  " |   d     b\n";  // both legs
	private String post =       " |\n";            // scaffold post
	private String base =       " |_____________\n";  // scaffold base
	
	Scanner input = new Scanner(System.in);
		
	// constructor, for gameplay (test)
	public Hangman() {
		person = top + ropeOrBody + post + post + post + post + post + post + post + post + post + base;
		System.out.println("HANGMAN\nThe \"hangman word\" must be all lowercase, with no spaces or punctuation.  Please enter the hangman word, without letting any of the players see.");
		secretWord = input.next();
		copySecretWord = getSecretWord();
		for(int i = 0; i < secretWord.length(); i++)  // set up progress
			progress += "_";
		for(int i = 1; i <= 100; i++)
			System.out.println();  // 100 blank lines
			
		while(true) {
			updateGraphic();
			printSeenWord();
			showAttempts();
			System.out.print("What is your guess? ");
			guess = input.next();
			printGuessedLetters(guess);  // test
			checkWord();
			//printCopy();
			checkWin();
			ultimateGuess();

		}
		// gameplay
	}
	
	private void printGraphic() {
		System.out.println(person);
	}
	
	private void updateGraphic() {
		if(bad == 0) {
			person = top + ropeOrBody + post + post + post + post + post + post + post + post + post + base;
			System.out.println(person);
		}
		else if(bad == 1) {
			person = top + ropeOrBody + head + post + post + post + post + post + post + post + post + base;
			System.out.println(person);
		}
		else if(bad == 2) {
			person = top + ropeOrBody + head + ropeOrBody + ropeOrBody + ropeOrBody + post + post + post + post + post + base;
			System.out.println(person);
		}
		else if(bad == 3) {
			person = top + ropeOrBody + head + ropeOrBody + leftArm + ropeOrBody + post + post + post + post + post + base;
			System.out.println(person);
		}
		else if(bad == 4) {
			person = top + ropeOrBody + head + ropeOrBody + bothArms + ropeOrBody + post + post + post + post + post + base;
			System.out.println(person);
		}
		else if(bad == 5) {
			person = top + ropeOrBody + head + ropeOrBody + bothArms + ropeOrBody + leftLeg1 + leftLeg2 + leftLeg3 + post + post + base;
			System.out.println(person);
		}
		else if(bad == 6) {
			person = top + ropeOrBody + head + ropeOrBody + bothArms + ropeOrBody + bothLegs1 + bothLegs2 + bothLegs3 + post + post + base;
			System.out.println(person);
		}
		// handle bad 3, 4, 5, 6 and test the method
	}
	
	private void printSeenWord() {
		System.out.println("\"" + progress + "\"");
	}
	
	private void printSeenWord(String guess) {
		System.out.println("\"" + progress + "\"");
	}
	
	private void printGuessedLetters(String guess) {
		guessedLetters += guess;
		System.out.println("Guessed: " + guessedLetters);
	}
	
	private String getSecretWord() {
		return secretWord;
		}
	
	private void checkWord() {
		int index = copySecretWord.indexOf(guess);
		if(index >= 0) {
			progress = progress.substring(0, index) + guess + progress.substring(index + 1);  // guess = n, progress _______ -> __n____
			copySecretWord = copySecretWord.substring(0, index) + "_" + copySecretWord.substring(index + 1);
			
		} else {
			bad++;
			}
		
	}
	
	
	//private void printCopy() {
		//System.out.println(copySecretWord);
		//}
		
	/*private void setUnderLine() {
		int index = copySecretWord.indexOf(guess);
		copySecretWord = copySecretWord.substring(0, index) + "_" + copySecretWord.substring(index + 1);
		
		}*/
		
	private void ultimateGuess() {
		if (guess.length() > 1 && guess.equals(secretWord)) {
			progress = secretWord;
			for (int i =  0; i <= 100; i++){
				for (int x =  0; x <= 100; x++){
					System.out.print("YOU GUESSED THE RIGHT WORD  ");
					}
				}
			}
		if (guess.length() > 1 && guess.equals(secretWord) == false) {
			bad++;
			}
		
		}
		
		
	private void checkWin() {
		if (progress.equals(secretWord)){
			for (int i =  0; i <= 100; i++){
				for (int x =  0; x <= 100; x++){
					System.out.print("YOU WIN ");
					}
				}
			}
		if (bad == 6) {
			for (int i =  0; i <= 100; i++){
				for (int x =  0; x <= 100; x++){
					System.out.print("YOU LOSE ");
					}
				}
			}
		
		}
		
	private void showAttempts() {
		if (bad == 0) {
			System.out.println("ATTEMPTS: O-O-O-O-O-O");
			}
		if (bad == 1) {
			System.out.println("ATTEMPTS: O-O-O-O-O");
			}
		if (bad == 2) {
			System.out.println("ATTEMPTS: O-O-O-O");
			}
		if (bad == 3) {
			System.out.println("ATTEMPTS: O-O-O");
			}
		if (bad == 4) {
			System.out.println("ATTEMPTS: O-O");
			}
		if (bad == 5) {
			System.out.println("ATTEMPTS: O");
			}
		if (bad == 6) {
			System.out.println("NO ATTEMPTS, THE MAN IS DEAD :(");
			}
		
		
		}

	
	public static void main (String[] args) {
		new Hangman();
		
	}
}

