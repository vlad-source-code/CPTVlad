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
		
		
		// if they choose play game, start it with asking their name and printing themes to console
		String strTheme;
		String Ftype = ".txt";
		
		while(!strChoice.contains("Quit")){
		  strChoice = con.readLine();
		  if(strChoice.contains("Play Game")){
			con.clear();
			boolean DonePlayGame = false;
			String strContinuePlay ="";
			while(!DonePlayGame) {
			TextOutputFile LeaderBoard = new TextOutputFile("leaderboard.txt", true);
			TextInputFile Theme;
		    TextInputFile Themes = new TextInputFile("themes.txt");
			String strPlayer = "";
			con.println("What is your name?");
			strPlayer = con.readLine();
			String strPlayAgain = "Yes";
			int PlayerWins = 0;
			LeaderBoard.println(strPlayer);
			
			
			int intThemeItemsNo = 0;
			int intWordIdx = 0;
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
            Theme = new TextInputFile(strTheme.concat(Ftype));
			// depending on the theme they enter, open the respective txt file, then select a random word from that file and print to console using ---
					
			while(Theme.eof() == false){
				Theme.readLine();
				intThemeItemsNo = intThemeItemsNo +1 ;
			}
			Theme.close();
			String strThemeArray[] = new String[intThemeItemsNo];
			int intIdx = 0;
			Theme = new TextInputFile(strTheme.concat(Ftype));
			int intcount;
			while(Theme.eof() == false){
				strThemeArray[intIdx]= Theme.readLine();
				intIdx = intIdx +1;
			}
			while (strPlayAgain.contains("Yes")) {
					
					strHiddenWord = strThemeArray[intWordIdx];
					strDashedWord = new StringBuilder(strHiddenWord);
					
					for(intcount=0;intcount<strHiddenWord.length();intcount++){
					   strDashedWord.setCharAt(intcount,'-');
					}
					//testing
					//con.println("The hidden word is:"+strHiddenWord);
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
				    
				} // end while dashedword
				if (intPoints >= 0) {
					    PlayerWins = PlayerWins+1;
					    
					    if(intWordIdx < intThemeItemsNo - 1) {
					       intWordIdx = intWordIdx +1;
				        }
					    // else reached the end of the theme, last item/word was processed
					    else {
					       strPlayAgain = "No";
					    }
			     }
			     // else player lost, points = 0
			     else{ 
					strPlayAgain = "No";
			     }	
		} // end while play again
		LeaderBoard.println(PlayerWins);
		LeaderBoard.close();
		con.println("Please enter Play to add another theme or Done to finish playing game");
	    strContinuePlay = con.readLine();
	    if(strContinuePlay.contains("Done")) {
			DonePlayGame = true;
	    }
	   } // end while not done play game
	   
	} // end if "play game"
        
        // start add theme
        
        if(strChoice.contains("Add Theme")){
	       con.clear();
	       Boolean DoneAddTheme = false;
           String strContinueAdd = "";
           while(!DoneAddTheme) {
	       con.println("Please enter a name (lowercase) for a new theme:");
	       strTheme = con.readLine();
	       TextOutputFile Theme = new TextOutputFile(strTheme.concat(Ftype));
	       TextOutputFile Themes = new TextOutputFile("themes.txt", true);
	       Themes.println(strTheme);
	       Themes.close();
	       String strNewWord = "";
	       while(!strNewWord.contains("Done")){
	            con.println("Please enter a new word for theme or Done when finished");
	            strNewWord=con.readLine();
	            if(!strNewWord.contains("Done")) {
	                Theme.println(strNewWord);
	            }
	       }
	       Theme.close();   
	    con.println("Please enter Add to add another theme or Done to finish adding themes");
	    strContinueAdd = con.readLine();
	    if(strContinueAdd.contains("Done")) {
			DoneAddTheme = true;
	    }
	   } 
	  } // end add theme
		if(strChoice.contains("View LeaderBoard")){
			//read from leadeboard.txt into a 2D array, buble-sort the array and print it to the console
			// determine how many records are in the leaderboard.txt file
			con.clear();
			int intCount =0;
			int intCount2 =0;
			TextInputFile LeaderBoardIn = new TextInputFile("leaderboard.txt");
			while(LeaderBoardIn.eof()== false) {
				LeaderBoardIn.readLine();
				intCount = intCount +1;
			}
			LeaderBoardIn.close();
			int intPlayers = intCount/2;
			String strLeaderBoardArray[][];
			strLeaderBoardArray = new String[intPlayers][2];
			String strPlayerTemp = "";
			String strScoreTemp = "";
		    // read the LeaderBoard.txt into StrLeaderBoard array
		    LeaderBoardIn = new TextInputFile("leaderboard.txt");
		    for(intCount=0; intCount<intPlayers; intCount++) {
				strLeaderBoardArray[intCount][0] = LeaderBoardIn.readLine();
				strLeaderBoardArray[intCount][1] = LeaderBoardIn.readLine();
		    }
		    LeaderBoardIn.close();
		    
		    // print the array before sorting
		    con.println("Before sorting");
		    for(intCount = 0; intCount < intPlayers; intCount++){
			    con.println(strLeaderBoardArray[intCount][0] + " - " + strLeaderBoardArray[intCount][1]);
		    }
		    
		    
		    
		    // bubble sort the array from highest to lowest score
		    con.println("After sorting");
		    for(intCount2 = 0; intCount2 < intPlayers-1; intCount2++){
				for(intCount = 0; intCount < intPlayers-1; intCount++){
					if(Integer.parseInt(strLeaderBoardArray[intCount][1]) < Integer.parseInt(strLeaderBoardArray[intCount+1][1])){
						// Swap
						// swap player name
						strPlayerTemp = strLeaderBoardArray[intCount][0];
						strLeaderBoardArray[intCount][0] = strLeaderBoardArray[intCount+1][0];
						strLeaderBoardArray[intCount+1][0] = strPlayerTemp;
						// swapping player score
						strScoreTemp = strLeaderBoardArray[intCount][1];
						strLeaderBoardArray[intCount][1] = strLeaderBoardArray[intCount+1][1];
						strLeaderBoardArray[intCount+1][1] = strScoreTemp;
				    }
			    }
		     }
		    
		    // print the sorted array
		    for(intCount = 0; intCount < intPlayers; intCount++){
			    con.println(strLeaderBoardArray[intCount][0] + " - " + strLeaderBoardArray[intCount][1]);
		    }
		}
		// end View LeaderBoard
	 con.clear();
	 con.println("Word Guessing Game!");
	 con.println("1: Play Game");
	 con.println("2: View LeaderBoard");
	 con.println("3: Add Theme");
	 con.println("4: Quit");
	 con.println("What would you like to choose (please enter the name of the option)?");
	 strChoice = con.readLine();	
	 } // end while not quit
	 
	con.println("You left the game, come back soon!!!");	
	}// end public static
}// end cptmain

