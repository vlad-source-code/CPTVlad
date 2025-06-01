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
		con.println("What would you like to choose?");
		strChoice = con.readLine();
		// if they choose play game, start it with asking their name and printing themes to console
		if(strChoice.contains("Play Game")){
			String strName;
			con.println("What is your name?");
			strName = con.readLine();
			TextInputFile Themes = new TextInputFile("themes.txt");
				while(Themes.eof() == false){
					con.println(Themes.readLine());
				}
				Themes.close();
				con.println("Please enter one theme to play");
				String strTheme;
				strTheme = con.readLine();
				// depending on the theme they enter, open the respective txt file, then select a random word from that file and print to console using ---
				if(strTheme.contains("Foods")){
					TextInputFile Foods = new TextInputFile("Foods.txt");
					int intFoodsNo = 0;
					while(Foods.eof() == false){
							Foods.readLine();
							intFoodsNo = intFoodsNo +1 ;
					}
					Foods.close();
					// select a random number from 0 to the number of fodd items and use that in the food file with the corsoponding word
					 int randomNum = (int)(Math.random() * (intFoodsNo));
					 con.println(randomNum);
					String strFoods[] = new String[intFoodsNo];
					int intIdx = 0;
					Foods = new TextInputFile("Foods.txt");
					while(Foods.eof() == false){
							strFoods[intIdx]=Foods.readLine();
							intIdx = intIdx +1;
					}
					// print using ---- 
					int intcount;
					String strHiddenWord = strFoods[randomNum];
					StringBuilder strDashedWord = new StringBuilder(strHiddenWord);
					
					for(intcount=0;intcount<strHiddenWord.length();intcount++){
					   strDashedWord.setCharAt(intcount,'-');
					}
					//con.println("The random word is:"+strHiddenWord);
					con.println("The dashed version of the random word is:"+strDashedWord);
				}
		
		}
		
	}
}

