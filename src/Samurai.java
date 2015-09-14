import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Samurai extends Sudoku {
	//attribute
		String[][] board;
		
		Sudoku topLeft;
		Sudoku topRight;
		Sudoku middle;
		Sudoku bottomLeft;
		Sudoku bottomRight;
		
		public Samurai(){
			this.initialize();
			
			Sudoku topLeft = new Sudoku(9);
			this.topLeft = topLeft;
			
			Sudoku topRight = new Sudoku(9);
			this.topRight = topRight;
			
			Sudoku middle = new Sudoku(9);
			this.middle = middle;
			
			Sudoku bottomLeft = new Sudoku(9);
			this.bottomLeft = bottomLeft;
			
			Sudoku bottomRight = new Sudoku(9);
			this.bottomRight = bottomRight;
		}
		
		public void initialize(){
			 String[][] blank = new String[21][21];
		        
		        for ( int nx = 0; nx < 21 ; ++nx ) {
		            for ( int ny = 0; ny < 21 ; ++ny ) {
		            	blank[nx][ny]= "  ";
		            }
		        }
		        
		        this.board = blank;
		        
		        
		}
			
		
		public void setUp(String x, Sudoku sudoku){
			//printBoard(sudoku);
			for(int i = 0; i < x.length(); i++){
				int j = i + 1;
				String s = x.substring(i, j);
				if(!s.equals("0")){
					int r = i / 9;
					int c = i % 9;
					if(!sudoku.setCell(sudoku, r, c, s)){
						System.out.println("This puzzle is unsolvable");
						good = false;
					}
					
					//printBoard(sudoku);
				}
			}
			
			//printBoard(sudoku);
		}
		
		public void checkOverlap(Samurai samurai){
			//check overlapping squares
			
					//start with topleft
					for (int i = 0; i<3; ++i){
						for (int j = 0; j<3; ++j){
							String s1 = samurai.middle.board[i][j];
							String s2 = samurai.topLeft.board[i+6][j+6];
							String sNew = "";
							
							if (s1.length()==1){
								samurai.topLeft.setCell(samurai.topLeft,i+6,j+6, s1);
								break;
							}
							else if (s2.length()==1){
								samurai.middle.setCell(samurai.middle, i,j, s2);
							}
							
							else{
							for (int n = 1; n<=9; ++n){
								String num = Integer.toString(n);
								if (s1.contains(num) && s2.contains(num)){
									sNew = sNew+num;
								}
							}
//							samurai.middle.setCell(samurai.middle, i, j, sNew);
//							samurai.topLeft.setCell(samurai.topLeft, i+6, j+6, sNew);
							
							samurai.middle.board[i][j]=sNew;
							samurai.topLeft.board[i+6][j+6]=sNew;
							
							}
						}
					}
					
//					// topright
					for (int i = 0; i<3; ++i){
						for (int j = 0; j<3; ++j){
							String s1 = samurai.middle.board[i][j + 6];
							String s2 = samurai.topRight.board[i + 6][j];
							String sNew = "";
																		
							if (s1.length()==1){
								samurai.topRight.setCell(samurai.topRight,i + 6,j, s1);
							}
							else if (s2.length()==1){
								samurai.middle.setCell(samurai.middle, i, j + 6, s2);
							}
							
							else{
							
							for (int n = 1; n<=9; ++n){
								String num = Integer.toString(n);
								if (s1.contains(num)&& s2.contains(num)){
									sNew = sNew+num;
								}
							}
							//HI u can't use setCell here bc then it removes like the whole string of possible values
							// and weird shit happens
							//Okay, I was just trying to uncomment it to see if that was the error
							//the answers printed out better, but one of them was wrong
							//Can you explain how the method is supposed to work?
							//yeah one sec im typing it
				//			samurai.middle.setCell(samurai.middle, i, j, sNew);
				//			samurai.topRight.setCell(samurai.topRight, j, i, sNew);
							samurai.topRight.board[i+6][j]=sNew;
							samurai.middle.board[i][j+6]=sNew;
						}
						}
					}
//					
					//bottomleft
					for (int i = 0; i<3; ++i){
						for (int j = 0; j<3; ++j){
							String s1 = samurai.middle.board[i +6][j];
							String s2 = samurai.bottomLeft.board[i][j + 6];
							String sNew = "";
							
							if (s1.length()==1){
								samurai.bottomLeft.setCell(samurai.bottomLeft,i,j + 6, s1);
							}
							else if (s2.length()==1){
								samurai.middle.setCell(samurai.middle, i+6, j, s2);
							}
							
							else{
							
							for (int n = 1; n<=9; ++n){
								String num = Integer.toString(n);
								if (s1.contains(num)&& s2.contains(num)){
									sNew = sNew+num;
								}
							}
//							samurai.middle.setCell(samurai.middle, i, j, sNew);
//							samurai.bottomLeft.setCell(samurai.bottomLeft, j, i, sNew);
							samurai.bottomLeft.board[i][j+6]=sNew;
							samurai.middle.board[i+6][j]=sNew;
						}
						}
					}
					
					//bottomright
					for (int i = 6; i<9; ++i){
						for (int j = 6; j<9; ++j){
							String s1 = samurai.middle.board[i][j];
							String s2 = samurai.bottomRight.board[i-6][j-6];
							String sNew = "";
							
							if (s1.length()==1){
								samurai.bottomRight.setCell(samurai.bottomRight,i-6,j-6, s1);
							}
							else if (s2.length()==1){
								samurai.middle.setCell(samurai.middle, i,j, s2);
							}
							
							else{
							for (int n = 1; n<=9; ++n){
								String num = Integer.toString(n);
								if (s1.contains(num)&& s2.contains(num)){
									sNew = sNew+num;
								}
							}
//							samurai.middle.setCell(samurai.middle, i, j, sNew);
//							samurai.bottomRight.setCell(samurai.bottomRight, i-6, j-6, sNew);
							if (sNew.length() ==1){
								samurai.middle.setCell(samurai.middle, i, j, sNew);
								samurai.bottomRight.setCell(samurai.bottomRight, i-6, j-6, sNew);
							}
							else{
							samurai.middle.board[i][j]=sNew;
							samurai.bottomRight.board[i-6][j-6]=sNew;
							}
						}
					}
					}
		}
		
	public boolean moreDifficult(Samurai sam) {
		Samurai s = new Samurai();
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++){
				s.bottomLeft.board[i][j] = sam.bottomLeft.board[i][j];
				s.bottomRight.board[i][j] = sam.bottomRight.board[i][j];
				s.topLeft.board[i][j] = sam.topLeft.board[i][j];
				s.topRight.board[i][j] = sam.topRight.board[i][j];
				s.middle.board[i][j] = sam.middle.board[i][j];
			}
		int n = 9;
		int r = 0;
		int c = 0;
		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++)
				if(s.middle.board[i][j].length() > 1 && s.middle.board[i][j].length() < n){
					n = s.middle.board[i][j].length();
					r = i; 
					c = j;
		}
		for(int i = 0; i < n; i++)
			if(s.middle.setCell(s.middle, r, c, s.middle.board[r][c].substring(i, i+1))){
				if(s.middle.difficultSam(s.middle)){
					s.checkOverlap(s);
					if(s.topLeft.difficultSam(s.topLeft) && s.topRight.difficultSam(s.topRight) &&
						s.bottomLeft.difficultSam(s.bottomLeft) && s.bottomRight.difficultSam(s.bottomRight)){
						this.topLeft.board = s.topLeft.board;
						this.middle.board = s.middle.board;
						this.topRight.board = s.topRight.board;
						this.bottomLeft.board = s.bottomLeft.board;
						this.bottomRight.board = s.bottomRight.board;
						return true;
					}
				}
				for(int x = 0; x < 9; x++)
					for(int y = 0; y < 9; y++){
						s.bottomLeft.board[x][y] = sam.bottomLeft.board[x][y];
						s.bottomRight.board[x][y] = sam.bottomRight.board[x][y];
						s.topLeft.board[x][y] = sam.topLeft.board[x][y];
						s.topRight.board[x][y] = sam.topRight.board[x][y];
						s.middle.board[x][y] = sam.middle.board[x][y];
					}
						
			}
		return false;
	}
		
		public static void solve(String x, Samurai samurai){
			long starttime = System.currentTimeMillis();
			String topleft = x.substring(0, 81);
			String topright = x.substring(81, 162);
			String middle = x.substring(162, 243);
			String bottomleft = x.substring(243, 324);
			String bottomright = x.substring(324, 405);
			
			samurai.setUp(topright, samurai.topRight);
			samurai.setUp(topleft, samurai.topLeft);
			samurai.setUp(middle,samurai.middle);
			samurai.setUp(bottomright, samurai.bottomRight);
			samurai.setUp(bottomleft, samurai.bottomLeft);
			
//			System.out.println("BEFORE CHECK OVERLAP-");
//			samurai.middle.printBoard(samurai.middle);
			
			//samurai.checkOverlap(samurai);
			
//			System.out.println("AFTER CHECK OVELAP -");
//			samurai.middle.printBoard(samurai.middle);
//			System.out.println();
			
			//samurai.middle=samurai.middle.solvedBoard(samurai.middle);
			for(int i = 0; i < 1000; i++){
				samurai.checkOverlap(samurai);
				samurai.middle.check(samurai.middle);
				samurai.checkOverlap(samurai);
				samurai.topLeft.check(samurai.topLeft);
				samurai.checkOverlap(samurai);
				samurai.topRight.check(samurai.topRight);
				samurai.checkOverlap(samurai);
				samurai.bottomLeft.check(samurai.bottomLeft);
				samurai.checkOverlap(samurai);
				samurai.bottomRight.check(samurai.bottomRight);
				samurai.checkOverlap(samurai);
			}
			samurai.moreDifficult(samurai);
		/*
			samurai.checkOverlap(samurai);
			samurai.middle = samurai.middle.solvedBoard(samurai.middle);
			samurai.checkOverlap(samurai);
			samurai.topLeft = samurai.topLeft.solvedBoard(samurai.topLeft);
			samurai.checkOverlap(samurai);
			samurai.bottomLeft = samurai.bottomLeft.solvedBoard(samurai.bottomLeft);
			samurai.checkOverlap(samurai);
			
			samurai.topRight = samurai.topRight.solvedBoard(samurai.topRight);
			
			samurai.bottomRight = samurai.bottomRight.solvedBoard(samurai.bottomRight);
			*/
			long endtime = System.currentTimeMillis();
			
			
			for (int i =0; i<21; ++i){
				for (int j=0; j<21; ++j){
					if ((i<9) && (j<9)){
						//System.out.println("sup");
						String num = samurai.topLeft.getCell(i,j);
						samurai.board[i][j]=num;
						//System.out.println(num);
					}
					if ((i>11)&& (j<9)){
						samurai.board[i][j]=samurai.bottomLeft.board[i-12][j];
					}
					if ((i>11) && (j>11)){
						samurai.board[i][j]=samurai.bottomRight.board[i-12][j-12];
					}
					if ((i<9) && (j>11)){
						samurai.board[i][j]=samurai.topRight.board[i][j-12];
					}
					if (((i>5)&&(i<15)) && ((j>5)&&(j<15))){
						samurai.board[i][j]=samurai.middle.board[i-6][j-6];
					}
				}
			}
				
			samurai.printBoard();
			
			if (good){
				//long endtime = System.currentTimeMillis();
				long runtime = endtime - starttime;
				System.out.println();
				System.out.println("This puzzle took " + runtime + " ms to solve.");
			}
		}
		
		public void printBoard(){
			System.out.println("The solution is -");
			for (int nx=0; nx<21; ++nx){
	    		for (int ny=0; ny<21; ++ny){
	    			System.out.print(board[nx][ny] + " ");
	    			if (ny==20){
	    				System.out.println();
	    			}
	    		}
	    	}
		}
		/*
		public static void main(String[] args){
			
			String x = fileToString();
			if (x.length()==4){
				String s = generatePuzzle();
				System.out.println("The randomly generated puzzle is:");
				printGen(s);
				System.out.println();
				Sudoku sudoku = new Sudoku(9);
				//System.out.println("The solution is -");
				solve(s,sudoku);
			}
			else if (x=="badfile"){
				//nothing
			}
			else if (x.length() == 405){
				Samurai samurai = new Samurai();
				//System.out.println("The solution is -");
				solve(x, samurai);
			}
			else{
				int n = x.length();
				n = (int) Math.sqrt(n);
				Sudoku sudoku = new Sudoku(n);
				sudoku.size = n;
				//System.out.println("The solution is -");
				//sudoku.printBoard(sudoku);
				solve(x, sudoku);
			}	    			    		
		}
		
		 */
	}
