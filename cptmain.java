import arc.*;

public class cptmain{

public static void main (String[] args){
	Console con = new Console();
		
		String strChoice = "";
		
		// when you start, see a logo, play game, view leaderboard, add theme and quit
		con.println("Word Guessing Game!");
		con.println("1: Play Game");
		con.println("2: View LeaderBoard");
		con.println("3: Add Theme");
		con.println("4: Quit");
		con.println("What would you like to choose (please enter the name of the option)?");
		strChoice = con.readLine();
		// if they choose play game, start it with asking their name and printing themes to console
		
		if(strChoice.contains("Play Game")){
			con.clear();
			String strPlayer = "";
			con.println("What is your name?");
			strPlayer = con.readLine();
			String strPlayAgain = "Yes";
			int PlayerWins = 0;
			TextOutputFile LeaderBoard = new TextOutputFile("leaderboard.txt");
			LeaderBoard.println(strPlayer);
			TextInputFile Themes = new TextInputFile("themes.txt");
			String strTheme;
			String Ftype = ".txt";
			TextInputFile Theme;
			int intThemeItemsNo = 0;
			int randomNum = 0;
			String strHiddenWord;
			StringBuilder strDashedWord;
			int intPoints;
			char ch;
			boolean Guess;
			boolean FirstRound = true;
			while(Themes.eof() == false){
				con.println(Themes.readLine());
			}
			Themes.close();
			con.println("Please enter one theme to play");
			strTheme = con.readLine();

			while (strPlayAgain.contains("Yes")) {
				

				// depending on the theme they enter, open the respective txt file, then select a random word from that file and print to console using ---
					Theme = new TextInputFile(strTheme.concat(Ftype));
					while(Theme.eof() == false){
							Theme.readLine();
							intThemeItemsNo = intThemeItemsNo +1 ;
					}
					Theme.close();
					// select a random number from 0 to the number of items and use that in the theme file with the corsoponding word
					// FEATURE: rather than the 1st item ie position 0 pick a random item
					 if (FirstRound = true){
					 randomNum = (int)(Math.random() * (intThemeItemsNo));
				     }
					 //con.println(randomNum);
					String strThemeArray[] = new String[intThemeItemsNo];
					int intIdx = 0;
					Theme = new TextInputFile(strTheme.concat(Ftype));
					while(Theme.eof() == false){
							strThemeArray[intIdx]= Theme.readLine();
							intIdx = intIdx +1;
					}
					// print using ---- 
					int intcount;
					strHiddenWord = strThemeArray[randomNum];
					strDashedWord = new StringBuilder(strHiddenWord);
					
					for(intcount=0;intcount<strHiddenWord.length();intcount++){
					   strDashedWord.setCharAt(intcount,'-');
					}
					//testing
					//con.println("The random word is:"+strHiddenWord);
					con.println("The dashed version of the random word is:"+strDashedWord);
					
					//have the user guess a letter and if correct replace the dashes with the letter (can be more than one spot)
					//initiatlize points
					//have a Guess boolean variable which is initialized as false and if a letter is guessed is set to true
					intPoints=strHiddenWord.length();
					ch=' ';
					// loop through guessing letters until all dashes or points are gone
					while(strDashedWord.toString().contains("-") && intPoints != 0){
					Guess = false;
					con.println("Please enter your guess for a letter:");
					ch = con.readChar();
					//parse strDashedWord letter by letter and compare with letter entered by the user
					//if match replace dash with letter, may be more than one spot
					for(intcount=0;intcount<strHiddenWord.length();intcount++) {
					   if(strHiddenWord.charAt(intcount) == ch) {
						 Guess = true;
						 strDashedWord.setCharAt(intcount,ch);
					   }
				    } 
				    if (Guess == false){
						intPoints=intPoints-1;
					}
					//testing
					//con.println(Guess);
					con.println(strDashedWord);
					con.println("You have:"+intPoints);
				} //end while
				if (intPoints > 0) {
					PlayerWins = PlayerWins+1;
					LeaderBoard.println(PlayerWins);
					if(randomNum < intThemeItemsNo) {
					randomNum = randomNum +1;
					FirstRound = false;	
				    }
					// reached the end of the theme, last item/word was processed
					else {
					strPlayAgain = "No";
					}
			    }
			    // player lost, points = 0
			    else{ 
					strPlayAgain = "No";
			    }
				LeaderBoard.close();
		}
		
		}// end while "play game"

		
	}// end public static
}// end cptmain

