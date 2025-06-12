import arc.*;

//import javax.swing.*;
//import java.awt.event.*; - for mouse input

public class cptmain{


public static void main (String[] args){
	
	Console con = new Console();
		
		char chChoice ='Z';
		
		
		//EXTRA FEATURE5: console window is 1280x720 with the title of the game
		//JFrame frame = new JFrame("Vlad's Word Guessing Game!");
        //JTextArea console = new JTextArea();
		//console.setText("Let's Play the Game!");
		//frame.add(new JScrollPane(console));
        //frame.setSize(1280, 720); // Set window size in pixels
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
        // use JLabel variables for input ie gettext() and output settext()
        
        // when starting, display the game name and the options that can be selected
		
		
		
		// if they choose play game, start it with asking their name and printing themes to console
		String strTheme;
		String Ftype = ".txt";
		
		//keep playing the game until Quit
		while(chChoice != 'Q'){
		  con.println("Welcome to The Word Guessing Game");
		  con.println("(P)lay Game - enter P to select this option");
		  con.println("(V)iew LeaderBoard - enter V to select this option");
		  con.println("(A)dd Theme - enter A to select this option");
		  con.println("(H)elp - enter H to select this option");
		  con.println("(Q)uit - enter Q to select this option");
		  con.println("To select an option, please enter the letter in brackets");
		  chChoice = con.readLine().charAt(0);
		  if(chChoice == 'P'){
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
			boolean GuessLetter = false;
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
					
					//con.println("The hidden word is:"+strHiddenWord);
					con.println("The dashed version of the random word is:"+strDashedWord);
					
					//have the user guess a letter and if correct replace the dashes with the letter (can be more than one spot)
					//initiatlize points
					//have a Guess boolean variable which is initialized as false and if a letter is guessed is set to true
					intPoints=strHiddenWord.length();
					con.println("You are starting off with this many points:"+intPoints);
					ch=' ';
					// loop through guessing letters until all dashes or points are gone
				while(strDashedWord.toString().contains("-") && intPoints > 0){
					
					con.println("Please enter your guess for a letter:");
					ch = con.readChar();
					//parse strDashedWord letter by letter and compare with letter entered by the user
					//if match replace dash with letter, may be more than one spot
					for(intcount=0;intcount<strHiddenWord.length();intcount++) {
					   if(strHiddenWord.charAt(intcount) == ch) {
						 GuessLetter = true;
						 strDashedWord.setCharAt(intcount,ch);
					   }
					   else {
					     GuessLetter = false;
					   }
				    } 
				    if (!strDashedWord.toString().contains("-")){
						PlayerWins = PlayerWins+1;
						if(intWordIdx < intThemeItemsNo - 1) {
					       intWordIdx = intWordIdx +1;
				        }
				        // else reached the end of the theme, last item/word was processed
				        else {
					       strPlayAgain = "No";
					    }
				    }  
					    
				    if (GuessLetter == false){
						intPoints=intPoints-1;
						if(intPoints == 0) {
					       strPlayAgain = "No";
					    }
					  
					}
					
					con.println(strDashedWord);
					con.println("You have this many points remaining:"+intPoints);
				    
				} // end while dashedword
				if (intPoints == 0) {
					strPlayAgain = "No";
			    }	
		} // end while play again
		LeaderBoard.println(PlayerWins);
		LeaderBoard.close();
		con.clear();
		con.println("Please enter a theme or Done to exit guessing");
	    strContinuePlay = con.readLine();
	    if(strContinuePlay.contains("Done")) {
			DonePlayGame = true;
	    }
	   } // end while not done play game
	        //con.clear();
	        //println("Welcome to The Word Guessing Game");
			//con.println("(P)lay Game - enter P to select this option");
		    //con.println("(V)iew LeaderBoard - enter V to select this option");
		    //con.println("(A)dd Theme - enter A to select this option");
		    //con.println("(H)elp - enter H to select this option");
		    //con.println("(Q)uit - enter Q to select this option");
		    //con.println("To select an option, please enter the letter in brackets");
		    //chChoice = con.readLine().charAt(0); 
	} // end if "play game"
   
        
        // start add theme
        
        if(chChoice == 'A'){
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
			//con.clear();
	        //println("Welcome to The Word Guessing Game");
			//con.println("(P)lay Game - enter P to select this option");
		    //con.println("(V)iew LeaderBoard - enter V to select this option");
		    //con.println("(A)dd Theme - enter A to select this option");
		    //con.println("(H)elp - enter H to select this option");
		    //con.println("(Q)uit - enter Q to select this option");
		    //con.println("To select an option, please enter the letter in brackets");
		    //chChoice = con.readLine().charAt(0); 
	    }
	   } 
	  } // end add theme
	   
	  
	  //start view leaderboard
		if(chChoice == 'V'){
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
				//EXTRA FEATURE4: if a player is "statitan" give it a bonus/extra win/guess ie increase its score by 1
				if(strLeaderBoardArray[intCount][0].contains("statitan")) {
			        strLeaderBoardArray[intCount][1] = Integer.toString(Integer.parseInt(strLeaderBoardArray[intCount][1])+1);
			    }  
		    }
		    LeaderBoardIn.close();
		    
		    // print the array before sorting
		    con.println("Before sorting");
		    //EXTRA FEATURE3: use system.out.println()
		    for(intCount = 0; intCount < intPlayers; intCount++){
			    System.out.println(strLeaderBoardArray[intCount][0] + " - " + strLeaderBoardArray[intCount][1]);
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
		    //con.clear();
	        //println("Welcome to The Word Guessing Game");
			//con.println("(P)lay Game - enter P to select this option");
		    //con.println("(V)iew LeaderBoard - enter V to select this option");
		    //con.println("(A)dd Theme - enter A to select this option");
		    //con.println("(H)elp - enter H to select this option");
		    //con.println("(Q)uit - enter Q to select this option");
		    //con.println("To select an option, please enter the letter in brackets");
		    //chChoice = con.readLine().charAt(0); 
		}
		// end View LeaderBoard
		
		// EXTRA FEATURE1: help option
		if(chChoice == 'H') {
			//con.clear();
			
			con.println("This game is based on entering your name and guessing words from different themes");
			con.println("There are three pre-defined themes in a list however you can add new themes");
			con.println("The user can select a theme and then you are prompted to guess the first word form the theme (has at least 10 words");
			con.println("The word is displayed masked with dashes instead of letters and you get a number of guesses equal to the number of letters");
			con.println("If you guess a letter, all the matched letters in the word are displayed");
			con.println("This ends when the first of you run out of points/guesses or you guess all the letters");
			con.println("If you guess a word, your are prompted to guess the following word in the theme");
			con.println("There is a hidden option which if selected displays  funny joke!!!");
			
			//con.clear();
	        //con.println("Welcome to The Word Guessing Game");
			//con.println("(P)lay Game - enter P to select this option");
		    //con.println("(V)iew LeaderBoard - enter V to select this option");
		    //con.println("(A)dd Theme - enter A to select this option");
		    //con.println("(H)elp - enter H to select this option");
		    //con.println("(Q)uit - enter Q to select this option");
		    //con.println("To select an option, please enter the letter in brackets");
		    //chChoice = con.readLine().charAt(0); 
	    }
	    // end help
	    
	    //EXTRA FEATURE2: hidden option J for joke
	    if(chChoice == 'J') {
			con.println("Player asks Chad ;) Why do we bother writing lines of code between code comment lines?");
			con.println("Chad answers I see that you met Mr Cadawas");
			
			//con.clear();
			//con.println("Welcome to The Word Guessing Game");
			//con.println("(P)lay Game - enter P to select this option");
		    //con.println("(V)iew LeaderBoard - enter V to select this option");
		    //con.println("(A)dd Theme - enter A to select this option");
		    //con.println("(H)elp - enter H to select this option");
		    //con.println("(Q)uit - enter Q to select this option");
		    //con.println("To select an option, please enter the letter in brackets");
		    //chChoice = con.readLine().charAt(0); 
		}
		// end joke
		
		
	 //con.clear();
	 //con.println("Word Guessing Game!");
	 //con.println("(P)lay Game - enter P to select this option");
	 //con.println("(V)iew LeaderBoard - enter V to select this option");
	 //con.println("(A)dd Theme - enter A to select this option");
	 //con.println("(H)elp - enter H to select this option");
	 //con.println("(Q)uit - enter Q to select this option");
	 //con.println("To select an option, please enter the letter in brackets");
	 //chChoice = con.readLine().charAt(0);	
	 } // end while not quit
	 
	con.println("You left the game, come back soon!!!");	
	}// end public static
}// end cptmain
