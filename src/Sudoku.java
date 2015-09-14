import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Sudoku extends JPanel{
	// class constants
   	public static boolean good = true;
    //public static String BLANK = "123456789";
    
    // attribute
    String[][] board;
    int size;
    
    // public Sudoku( )
    public Sudoku() {
        this.initialize();
        this.size = 9;
    }
    public Sudoku(int n){
    	this.size = n;
    	this.initialize();
    	//size = n;
    }
    
    // public void initialize( )
    public void initialize() {
    	String values = "";
    	if (this.size==9){
    		values = "123456789";
    	}
    	else if (this.size==16){
    		values = "123456789ABCDEFG";
    	}
    	else if (this.size==4){
    		values = "1234";
    	}
        String[][] blank = new String[this.size][this.size];
        
        for ( int nx = 0; nx < this.size ; ++nx ) {
            for ( int ny = 0; ny < this.size ; ++ny ) {
                blank[nx][ny] = values;
            }
        }
        this.board = blank;
    }
    
    // public String[][] getBoard()
    public String[][] getBoard() {
        return this.board;
    }
    
    // public String getCell( int r, int c )
    public String getCell( int r, int c ) {
        String cell = null;
        
        if ( ( this.isRowIndex( r ) ) && ( this.isColumnIndex( c ) ) ) {
            cell = board[r][c];
        }
        
        return cell;
    }
    
    public int getHome(int x){
    	if (this.size==4){
    		if (x<2){
    			return 0;
    		}
    		if (x>1 && x<4){
    			return 2;
    		}
    	}
    	
    	else if (this.size==9)	{	    	
    	  if(x < 3)
        	  return  0;
          if(x > 2 && x < 6)
        	  return  3;
          if(x > 5 && x < 9)
        	  return  6;
    	}
    	
    	else if (this.size==16){
    		if (x<4){
    			return 0;
    		}
    		if (x>3 && x<8){
    			return 4;
    		}
    		if (x>7 && x<12){
    			return 8;
    		}
    		if (x>11 && x<16){
    			return 12;
    		}
    	}
          return 10;
    	
    }
    // public void setCell( int r, int c, String p )
    public boolean setCell(Sudoku suds, int r, int c, String p ) {
    	//System.out.println(r + " " + c + " " + p);
    	Sudoku sud = new Sudoku(this.size);
    	for(int i = 0; i < this.size; i++)
    		for(int j = 0; j < this.size; j++)
    			sud.board[i][j] = suds.board[i][j];    
        	
          for(int i = 0; i < this.size; i++)
        	  if(i != r && sud.board[i][c].equals(p))
        		  return false;
        
          for(int i = 0; i < this.size; i++)
        	  if(i != c && sud.board[r][i].equals(p))
        		  return false;
   
          int n = sud.getHome(r);
          int m = sud.getHome(c);
        
          for(int i = 0; i < Math.sqrt(this.size); i++){
        	  for(int j = 0; j < Math.sqrt(this.size); j++){
        		  int g = i + n;
        		  int h = j + m;
        		  if(g != r && h != c && sud.board[g][h].equals(p))
        			  return false;
        	  }
          }
          sud.board[r][c] = p;
          for(int i = 0; i < this.size; i++){
        	  if(i != c && sud.board[r][i].length() > 1){
	        		 sud.board[r][i] = sud.board[r][i].replaceAll(p, "");
	        	if(sud.board[r][i].length() == 1)
	        		if(!sud.setCell(sud, r, i, sud.board[r][i]))
	        				return false;
        	  }
        	  if(i != r && sud.board[i][c].length() > 1){
        		  sud.board[i][c] = sud.board[i][c].replaceAll(p, "");
        		if(sud.board[i][c].length() == 1)
        			if(!sud.setCell(sud, i, c, sud.board[i][c]))
        				//System.out.println("HERE!!");
        				return false;
        	  }
          }
          for(int i = 0; i < Math.sqrt(this.size); i++){
        	  for(int j = 0; j < Math.sqrt(this.size); j++){
        		  int g = i + n;
        		  int h = j + m;
        		  if(g != r && h != c && sud.board[g][h].length() > 1){
        			  sud.board[g][h] = sud.board[g][h].replaceAll(p, "");
        			  if(sud.board[g][h].length() == 1)
        				  if(!sud.setCell(sud, g, h, sud.board[g][h]))
        					  return false;
        		  }
        			  
        	  }
          }
          
       this.board = sud.board;
        return true;
        
    }
    
    public boolean check(Sudoku suds) {
    	Sudoku sud = new Sudoku(this.size);
    	for(int i = 0; i < this.size; i++)
    		for(int j = 0; j < this.size; j++)
    			sud.board[i][j] = suds.board[i][j];
    	for( int i = 0; i < this.size; i ++) {
    		for(int j = 0; j < this.size; j++) {
    			for(int n = 1; n < (this.size+1); n++){
    				boolean in = true;
    				String s = "";
    				if (n<10){
    					s = Integer.toString(n);
    				}
    				else{
    					if (n==10)
    						s="A";
    					if (n==11)
    						s="B";
    					if (n==12)
    						s="C";
    					if (n==13)
    						s="D";
    					if(n==14)
    						s="E";
    					if (n==15)
    						s="F";
    				}
    				if(sud.board[i][j].contains(s)){
    					for(int x = 0; x < this.size; x++){
    						if(x != i && sud.board[x][j].contains(s)){
    							in = false;
    							break;
    							}
    					}
    					if(in)
    						return setCell(sud, i, j, s);
    				
    				in = true;
    				for(int y = 0; y < this.size; y++){
    					if(j != y && board[i][y].contains(s)){
    						in = false;
    						break;
    					}
    				}
    				if(in)
    					return setCell(sud,i, j, s);
    			     int q = getHome(i);
    		          int m = getHome(j);
    		        
    		          for(int l = 0; l < Math.sqrt(this.size); l++){
    		        	  for(int p = 0; p < Math.sqrt(this.size); p++){
    		        		  int g = q + l;
    		        		  int h = m + p;
    		        		  if(g != i && h != j && board[g][h].contains(s)){
    		        			in = false;
    		        			break;
    		        		  }
    		        			  
    		        	  }
    		          if(!in)
    		        	  break;}
    		          if(in)
	    					return setCell(sud, i, j, s);
    				}
    			}
    		}				
    	}
    	return true;
    }
    
    public boolean difficult(Sudoku sud) {
    	int n = this.size;
    	int r = 0;
    	int c = 0;
    	Sudoku suds = new Sudoku(this.size);
    	//System.out.println("boom");
    	for(int i = 0; i < this.size; i++)
    		for(int j = 0; j < this.size; j++){
    			if(sud.board[i][j].length() > 1 && sud.board[i][j].length() < n){
    				n = sud.board[i][j].length();
    				r = i;
    				c = j;
    			}
    			suds.board[i][j] = sud.board[i][j];
    		}
    	if(n == this.size){
    		this.board = suds.board;
    		suds.printBoard(suds);
    		return true;
    	}
    	
    	for(int i =n - 1; i >=0; i--){
    	
    		//System.out.println(r+ " " + c +" " + suds.board[r][c]);
    		
    		char ch = suds.board[r][c].charAt(i);
    		String str = String.valueOf(ch);
    		if(suds.setCell(suds, r, c, str))	{
    		 if(difficult(suds))
    			 return true;
    		}	
    		
    		for(int x = 0; x < this.size; x++)
    			for(int u = 0; u < this.size; u++)
    				suds.board[x][u] = sud.board[x][u];
    	
    	}
    	return false;
    }
    
    public boolean difficultSam(Sudoku sud) {
    	int n = 9;
    	int r = 0;
    	int c = 0;
    	Sudoku suds = new Sudoku(9);
    	//System.out.println("boom");
    	for(int i = 0; i < 9; i++)
    		for(int j = 0; j < 9; j++){
    			if(sud.board[i][j].length() > 1 && sud.board[i][j].length() < n){
    				n = sud.board[i][j].length();
    				r = i;
    				c = j;
    			}
    			suds.board[i][j] = sud.board[i][j];
    		}
    	if(n == 9){
    		this.board = suds.board;
    		//suds.printBoard(suds);
    		return true;
    	}
    	
    	for(int i =n - 1; i >=0; i--){
    	
    		//System.out.println(r+ " " + c +" " + suds.board[r][c]);
    		
    		char ch = suds.board[r][c].charAt(i);
    		String str = String.valueOf(ch);
    		if(suds.setCell(suds, r, c, str))	{
    		 if(difficultSam(suds))
    			 return true;
    		}	
    		
    		for(int x = 0; x < 9; x++)
    			for(int u = 0; u < 9; u++)
    				suds.board[x][u] = sud.board[x][u];
    	
    	}
    	return false;
    }
    
		// public boolean isRowIndex( int r )
        public boolean isRowIndex( int r ) {
            boolean answer = ( r >= 0 ) && ( r < this.size );
            return answer;
        }
        
        // public boolean isColumnIndex( int c )
        public boolean isColumnIndex( int c ) {
            boolean answer = ( c >= 0 ) && ( c < this.size );
            return answer;
        }
    	public void printBoard(Sudoku sudoku){
    		System.out.println("The solution is -");
    		for (int i=0; i<this.size; ++i){
    			for (int j=0; j<this.size; ++j){
    					System.out.print(sudoku.board[i][j]+" ");
    					//if(j == 2 || j == 5)
    					//	System.out.print(" ");
    					if(j == (this.size-1))
    						System.out.println();
    			}
    			//if(i == 2 || i == 5)
    				//System.out.println();
    		}
    	}
    	
    	public static int randInt(int min, int max) {

    	    Random rand = new Random();

    	    int randomNum = rand.nextInt((max - min) + 1) + min;

    	    return randomNum;
    	}
    	
    	public static String generatePuzzle(){
    		String s = "";
    		
    		String[][] puzzle = new String[9][9];
    		for (int i=0; i<9; ++i){
    			for (int j=0; j<9; ++j){
    				puzzle[i][j]= String.valueOf(0);
    			}
    		}
    		int count = 0;
    		while (count<15){
    			int r = randInt(0,8);
    			int c = randInt(0,8);
    				boolean check = false;
    				//int num = randInt(1,9);
    				int num;
    				while (!check){
    					num = randInt(1,9);
    					puzzle[r][c]=String.valueOf(num);
    				for(int i = 0; i < 9; i++)
    		        	  if(i != r && puzzle[i][c]==String.valueOf(num))
    		        		  puzzle[r][c]=String.valueOf(0);
    		        		  check= false;
    		        
    		          for(int i = 0; i < 9; i++)
    		        	  if(i != c && puzzle[r][i]==String.valueOf(num))
    		        		  puzzle[r][c]=String.valueOf(0);
    		        		  check= false;
    		   
    		          int n = 0;
    		          int m = 0;
    		          
    		          if(r < 3)
    		        	  n=  0;
    		          if(r > 2 && r < 6)
    		        	  n=  3;
    		          if(r > 5 && r < 9)
    		        	  n=  6;
    		          if (c<3)
    		        	  m=0;
    		          if (c>2 && c<6)
    		        	  n=3;
    		          if(c>5 && c<9)
    		        	  n=6;
    		        
    		          for(int i = 0; i < 3; i++){
    		        	  for(int j = 0; j < 3; j++){
    		        		  int g = i + n;
    		        		  int h = j + m;
    		        		  if(g != r && h != c && puzzle[g][h]==String.valueOf(num))
    		        			  puzzle[r][c]=String.valueOf(0);
    		        			  check= false;
    		        	  }
    		          }
    		          check = true;
    		          ++count;
//    		        	  puzzle[r][c]=num;
    				}
    				
    	    		
    	    
    			
    		}
    		for (int i =0; i<9; ++i){
    			for (int j=0; j<9; ++j){
    				s = s+puzzle[i][j];
    			}
    		}
			//check = true;
			//puzzle[r][c]=num;
			Sudoku x = new Sudoku(9);
			if (!solve(s,x ))
				return generatePuzzle();
			System.out.println("That puzzle was solvable. Hooray!");
			printGen(s);
			
    		return s;
    	}
    	
    	public static void printGen(String x){
    		String[][]puzzle = new String[9][9];
    		int index=0;
    		while (index <81){
    		for (int i=0; i<9; ++i){
    			for (int j=0; j<9; ++j){
    				//int index = 0;
    				char c =x.charAt(index);
    				String num = "" +c;
    				puzzle[i][j]=num;
    				num="";
    				++index;
    			}
    		}
    		}
    		for (int i=0; i<9; ++i){
    			for (int j=0; j<9; ++j){
    					System.out.print(puzzle[i][j]+" ");
    					//if(j == 2 || j == 5)
    						//System.out.print(" ");
    					if(j == 8)
    						System.out.println();
    			}
    			//if(i == 2 || i == 5)
    				//System.out.println();
    		}
    	}
    	
    	public static String fileToString(String filename){
    		String x = "";
    	
    		if (filename.length()==4){
    			//System.out.println("HEY");
    			return filename;
    		}
    		else{
    		File f = new File(filename); 
    		try{
    			Scanner input = new Scanner(f);
    			while(input.hasNext()){
    				String y = input.next();
    				if(y != " ");
    				 x = x + y;
    			}
    			input.close();
    			x.replaceAll("\\s+","");
    			
    			}catch (IOException e) {
    				good = false;
    				System.out.println("Invalid puzzle file.");
    			}
    		}
    		return x;
    	}
    	
    	public static Boolean solve(String x, Sudoku sudoku){
    		good = true;
    		long starttime = System.currentTimeMillis();
    		for(int i = 0; i < x.length(); i++){
    			int j = i + 1;
    			String s = x.substring(i, j);
    			if(!s.equals("0")){
    				int r = i / sudoku.size;
    				int c = i % sudoku.size;
    				if(!sudoku.setCell(sudoku, r, c, s)){
    					System.out.println("That puzzle was unsolvable");
    					//System.out.println("setcell");
    					good = false;
    					return false;
    				}
    				sudoku.setCell(sudoku, r, c, s);
    				//System.out.println("SET");
    			}
    		}
    		if(good){
    		for(int i = 0; i < 1000; i ++){
    			if(!sudoku.check(sudoku)){
    				System.out.println("That puzzle was unsolvable");
    				System.out.println("check");
    				good = false;
    				return false;
    				//break;
    			}
    			
    			sudoku.check(sudoku);
    		}
    		/*
    		boolean diff = false;
    		for(int i = 0; i < sudoku.size; i++)
    			for(int j = 0; j < sudoku.size; j++)
    				if(sudoku.board[i][j].length() > 1)
    					diff = true;
    		if(diff)
    		*/
    			if(!sudoku.difficult(sudoku))
    				System.out.println("That puzzle was unsolvable");
    		}
    			if(good){
    		long endtime = System.currentTimeMillis();
    		long runTime = endtime - starttime;
    		//System.out.println();
    		//System.out.println("The solution is - ");	
    		//System.out.println();
    		//sudoku.printBoard(sudoku);
    		System.out.println();
    		System.out.println("This puzzle took " + runTime + " ms to solve.");
    			}
    			return true;
    	
    	}
}