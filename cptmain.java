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
			String strPlayer;
			con.println("What is your name?");
			strPlayer = con.readLine();
			int PlayerWins = 0;
			TextOutputFile LeaderBoard = new TextOutputFile("leaderboard.txt");
			LeaderBoard.println(strPlayer);
			TextInputFile Themes = new TextInputFile("themes.txt");
				while(Themes.eof() == false){
					con.println(Themes.readLine());
				}
				Themes.close();
				con.println("Please enter one theme to play");
				String strTheme;
				String Ftype = ".txt";
				strTheme = con.readLine();
				// depending on the theme they enter, open the respective txt file, then select a random word from that file and print to console using ---
					TextInputFile Theme = new TextInputFile(strTheme.concat(Ftype));
					int intThemeItemsNo = 0;
					while(Theme.eof() == false){
							Theme.readLine();
							intThemeItemsNo = intThemeItemsNo +1 ;
					}
					Theme.close();
					// select a random number from 0 to the number of fodd items and use that in the food file with the corsoponding word
					 int randomNum = (int)(Math.random() * (intThemeItemsNo));
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
					String strHiddenWord = strThemeArray[randomNum];
					StringBuilder strDashedWord = new StringBuilder(strHiddenWord);
					
					for(intcount=0;intcount<strHiddenWord.length();intcount++){
					   strDashedWord.setCharAt(intcount,'-');
					}
					//testing
					//con.println("The random word is:"+strHiddenWord);
					con.println("The dashed version of the random word is:"+strDashedWord);
					
					//have the user guess a letter and if correct replace the dashes with the letter (can be more than one spot)
					//initiatlize points
					//have a Guess boolean variable which is initialized as false and if a letter is guessed is set to true
					int intPoints=strHiddenWord.length();
					char ch=' ';
					// loop through guessing letters until all dashes or points are gone
					while(strDashedWord.toString().contains("-") && intPoints != 0){
					boolean Guess = false;
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
					PlayerWins = PlayerWins +1;
					LeaderBoard.println(String.valueOf(PlayerWins));
			    }
				LeaderBoard.close();
		}// end strChoice "play game"

		
	}// end public static
}// end cptmain

