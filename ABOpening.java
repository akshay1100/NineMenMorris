//package game;

import java.util.*;
import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class ABOpening {
 
	static StringBuffer grid_main= new StringBuffer("xxxxxxxxxxxxxxxxxxxxxxx");
	static long positions_evaluated=0;
	 char player = 'W';
	 
	 
	int depth ;
	char mySeed = 'W' ;
	char oppSeed = 'B';
	


void nxt_move(StringBuffer grid,char player, int depth)
{
	int value=0;
	System.out.println("Current Board Postion: "+grid);
	print_grid(grid);
	StringBuffer[] n1= minimax(depth,player,new StringBuffer(grid),Integer.MIN_VALUE, Integer.MAX_VALUE);
	grid=n1[1];	
	value=Integer.parseInt(n1[0].toString());
	
	out.println("\n\nNext move is: \n");
	System.out.println("New Board Position: "+grid);
	print_grid(grid);
	grid_main=new StringBuffer(grid);
	out.println("\nBest value for move is: "+value);
	out.println("Positions evaluated: "+positions_evaluated);
	
	
}

StringBuffer[] minimax(int depth, char seed,StringBuffer grid1,int alpha,int beta)
{


ArrayList<StringBuffer> list1 =available_moves(new StringBuffer(grid1),seed);

int currentScore;
StringBuffer curr_grid=null;

if(list1.isEmpty()||depth==0)
{	
	currentScore=evaluate(new StringBuffer(grid1));	
	StringBuffer[] k3 = {(new StringBuffer(Integer.toString(currentScore))),curr_grid};
	return k3;
}
else
{
for(StringBuffer k1:list1)
{
	
	if(seed==mySeed)
	{
		currentScore=Integer.parseInt((minimax(depth-1,oppSeed,new StringBuffer(k1),alpha,beta)[0]).toString());
		if(currentScore > alpha)
		{
			alpha = currentScore;
			curr_grid=new StringBuffer(k1);
			
		}
		 
	}
	else
	{
		 currentScore = Integer.parseInt((minimax(depth-1, mySeed,new StringBuffer(k1),alpha,beta)[0]).toString());
         if (currentScore < beta) {
            beta = currentScore;
            curr_grid=new StringBuffer(k1);
		
	}
	
}	
	 if (alpha >= beta) break;	
}
StringBuffer[] k3 = {(new StringBuffer(Integer.toString((seed == mySeed) ? alpha : beta))),curr_grid};
return k3;

}

}


ArrayList<StringBuffer> available_moves(StringBuffer grid2,char seed)
{
	//out.println("in moves");//out.println(grid2);
	
StringBuffer grid1=null;	
ArrayList<StringBuffer> list_moves= new ArrayList<StringBuffer>();
//ArrayList<Integer> all_moves=new ArrayList<Integer>();
for(int i=0;i<grid2.length();i++)
{
 //out.println("check grid: "); print_grid(grid2);
	//out.println("pos: "+i);
	//out.println("grid2:  "+grid2);
	if(grid2.charAt(i)=='x')
	{
		grid1=new StringBuffer(grid2);
		grid1.setCharAt(i,seed);
		//out.println("grid1: ");
		//print_grid(grid1);
		if ( check_mill(i,new StringBuffer(grid1)))
		{
		 list_moves=remove_oppo_mill(new StringBuffer(grid1),new ArrayList<StringBuffer>(list_moves),seed);
		}
		else
		list_moves.add(grid1);
		//out.println("avaliable moves")
		//for(StringBuffer k3:list_moves)
		//	print_grid(k3);
		
		
	}
	
}
return list_moves;
}


int evaluate(StringBuffer grid6)
{
	int score =0 ;
	positions_evaluated=positions_evaluated+1;

	StringBuffer grid1=grid6;
	
	int w_peice=0,b_peice=0;

	for(int i1=0;i1<grid1.length();i1++)
	{
		if(grid1.charAt(i1)=='W')
			w_peice++;	
		if(grid1.charAt(i1)=='B')
			b_peice++;	
	}
	
	score+=(w_peice-b_peice);
/*	score += evaluateLine (grid1.charAt(0),grid1.charAt(1),grid1.charAt(2));
	score += evaluateLine (grid1.charAt(3),grid1.charAt(4),grid1.charAt(5));
	score += evaluateLine (grid1.charAt(6),grid1.charAt(7),grid1.charAt(8));
	score += evaluateLine (grid1.charAt(17),grid1.charAt(18),grid1.charAt(19));
	score += evaluateLine (grid1.charAt(20),grid1.charAt(21),grid1.charAt(22));
	score += evaluateLine (grid1.charAt(9),grid1.charAt(10),grid1.charAt(11));
	score += evaluateLine (grid1.charAt(12),grid1.charAt(13),grid1.charAt(14));
	//vertical lines
	score += evaluateLine (grid1.charAt(0),grid1.charAt(9),grid1.charAt(20));
	score += evaluateLine (grid1.charAt(3),grid1.charAt(10),grid1.charAt(17));
	score += evaluateLine (grid1.charAt(6),grid1.charAt(11),grid1.charAt(15));
	score += evaluateLine (grid1.charAt(1),grid1.charAt(4),grid1.charAt(7));
	score += evaluateLine (grid1.charAt(8),grid1.charAt(12),grid1.charAt(16));
	score += evaluateLine (grid1.charAt(5),grid1.charAt(13),grid1.charAt(19));
	score += evaluateLine (grid1.charAt(2),grid1.charAt(14),grid1.charAt(22));
	
	//diagoanl lines
	score += evaluateLine (grid1.charAt(0),grid1.charAt(3),grid1.charAt(6));
	score += evaluateLine (grid1.charAt(20),grid1.charAt(17),grid1.charAt(15));
	score += evaluateLine (grid1.charAt(8),grid1.charAt(5),grid1.charAt(2));
	score += evaluateLine (grid1.charAt(16),grid1.charAt(19),grid1.charAt(22));
	
	
	*/
	
	return score;
}

int evaluateLine(char q2, char q3, char q4)

{
	
	//out.println(q2+" "+q3+" "+q4);
	int score =0 ;
	
	if(q2==mySeed)
		score=1;
	else if(q2==oppSeed)
	score=-1;
	
	if(q3==mySeed)
	{ if(score ==1)
		score=10;
	else if(score == -1)
	{//out.println(q2+""+q3+""+q4+"  :"+ "0");
		return 0; }
	else 
		score = 1;
	}
	else if (q3==oppSeed)
	{ if(score == -1)
		score=-10;
	else if(score == 1)
	{//////out.println(q2+""+q3+""+q4+"  :"+"0");
		return 0;}
	else 
		score = -1;
	}
		
	
    if (q4 == mySeed) {
        if (score > 0) { 
           score *= 10;
        } else if (score < 0) { 
        	//out.println(q2+""+q3+""+q4+"  :"+"0");
           return 0;
        } else { 
           score = 1;
        }
     } else if (q4 == oppSeed) {
        if (score < 0) { 
           score *= 10;
        } else if (score > 1) { 
        	//out.println(q2+""+q3+""+q4+"  :"+"0");
           return 0;
        } else { 
           score = -1;
        }
     }
	
	//out.println(q2+""+q3+""+q4+"  :"+score);
return score;
}

ArrayList<StringBuffer> remove_oppo_mill(StringBuffer grid1,ArrayList<StringBuffer> list,char seed)
{
	char opposeed;
	if(seed=='W')
		opposeed='B';
	else
		opposeed='W';
	StringBuffer grid_temp=null;
	for(int i=0;i<grid1.length();i++)
	{
		if(grid1.charAt(i)==opposeed)
		{
			if(!check_mill(i,new StringBuffer(grid1)))
			{
				grid_temp=new StringBuffer(grid1);
				grid_temp.setCharAt(i, 'x');
				list.add(grid_temp);
			}	
		}
	}
	
	return list;
}

void print_grid(StringBuffer grid)
{
 	out.println("\n"+grid.charAt(0)+"      "+grid.charAt(1)+"      "+grid.charAt(2));
 	out.println("  "+grid.charAt(3)+"    "+grid.charAt(4)+"    "+grid.charAt(5)+"  ");
 	out.println("    "+grid.charAt(6)+"  "+grid.charAt(7)+"  "+grid.charAt(8)+"    ");
 	out.println(grid.charAt(9)+" "+grid.charAt(10)+" "+grid.charAt(11)+"     "+grid.charAt(12)+" "+grid.charAt(13)+" "+grid.charAt(14)); 	
 	out.println("    "+grid.charAt(15)+"  "+"N"+"  "+grid.charAt(16)+"    ");
 	out.println("  "+grid.charAt(17)+"    "+grid.charAt(18)+"    "+grid.charAt(19)+"  ");
 	out.println(grid.charAt(20)+"      "+grid.charAt(21)+"      "+grid.charAt(22));
 	}

boolean check_mill(int loc, StringBuffer grid1)
{

//some code goes here, the initialization

char c1=grid1.charAt(loc);
switch(loc)
{
case 0: {
	if((grid1.charAt(1)==c1 && grid1.charAt(2)==c1)||(grid1.charAt(3)==c1 && grid1.charAt(6)==c1)||(grid1.charAt(9)==c1 && grid1.charAt(20)==c1))
		return true;
	else 
		return false;}

case 1: {
	if((grid1.charAt(0)==c1 && grid1.charAt(2)==c1)||(grid1.charAt(4)==c1 && grid1.charAt(7)==c1))
		return true;
	else 
		return false;}

case 2: {
	if((grid1.charAt(1)==c1 && grid1.charAt(0)==c1)||(grid1.charAt(5)==c1 && grid1.charAt(8)==c1)||(grid1.charAt(14)==c1 && grid1.charAt(22)==c1))
		return true;
	else 
		return false;}

case 3: {
	if((grid1.charAt(0)==c1 && grid1.charAt(6)==c1)||(grid1.charAt(4)==c1 && grid1.charAt(5)==c1)||(grid1.charAt(10)==c1 && grid1.charAt(17)==c1))
		return true;
	else 
		return false;}

case 4: {
	if((grid1.charAt(1)==c1 && grid1.charAt(7)==c1)||(grid1.charAt(3)==c1 && grid1.charAt(5)==c1))
		return true;
	else 
		return false;}

case 5: {
	if((grid1.charAt(3)==c1 && grid1.charAt(4)==c1)||(grid1.charAt(13)==c1 && grid1.charAt(19)==c1)||(grid1.charAt(2)==c1 && grid1.charAt(8)==c1))
		return true;
	else 
		return false;}

case 6: {
	if((grid1.charAt(0)==c1 && grid1.charAt(3)==c1)||(grid1.charAt(7)==c1 && grid1.charAt(8)==c1)||(grid1.charAt(11)==c1 && grid1.charAt(15)==c1))
		return true;
	else 
		return false;}

case 7: {
	if((grid1.charAt(1)==c1 && grid1.charAt(4)==c1)||(grid1.charAt(6)==c1 && grid1.charAt(8)==c1))
		return true;
	else 
		return false;}

case 8: {
	if((grid1.charAt(5)==c1 && grid1.charAt(2)==c1)||(grid1.charAt(7)==c1 && grid1.charAt(6)==c1)||(grid1.charAt(12)==c1 && grid1.charAt(16)==c1))
		return true;
	else 
		return false;}

case 9: {
	if((grid1.charAt(0)==c1 && grid1.charAt(20)==c1)||(grid1.charAt(10)==c1 && grid1.charAt(11)==c1))
		return true;
	else 
		return false;}

case 10: {
	if((grid1.charAt(9)==c1 && grid1.charAt(11)==c1)||(grid1.charAt(17)==c1 && grid1.charAt(3)==c1))
		return true;
	else 
		return false;}

case 11: {
	if((grid1.charAt(6)==c1 && grid1.charAt(15)==c1)||(grid1.charAt(9)==c1 && grid1.charAt(10)==c1))
		return true;
	else 
		return false;}

case 12: {
	if((grid1.charAt(8)==c1 && grid1.charAt(16)==c1)||(grid1.charAt(13)==c1 && grid1.charAt(14)==c1))
		return true;
	else 
		return false;}

case 13: {
	if((grid1.charAt(12)==c1 && grid1.charAt(14)==c1)||(grid1.charAt(5)==c1 && grid1.charAt(19)==c1))
		return true;
	else 
		return false;}

case 14: {
	if((grid1.charAt(12)==c1 && grid1.charAt(13)==c1)||(grid1.charAt(2)==c1 && grid1.charAt(22)==c1))
		return true;
	else 
		return false;}

case 15: {
	if((grid1.charAt(6)==c1 && grid1.charAt(11)==c1)||(grid1.charAt(17)==c1 && grid1.charAt(20)==c1))
		return true;
	else 
		return false;}

case 16: {
	if((grid1.charAt(8)==c1 && grid1.charAt(12)==c1)||(grid1.charAt(19)==c1 && grid1.charAt(22)==c1))
		return true;
	else 
		return false;}

case 17: {
	if((grid1.charAt(3)==c1 && grid1.charAt(10)==c1)||(grid1.charAt(18)==c1 && grid1.charAt(19)==c1)||(grid1.charAt(15)==c1 && grid1.charAt(20)==c1))
		return true;
	else 
		return false;}

case 18: {
	if((grid1.charAt(17)==c1 && grid1.charAt(19)==c1))
		return true;
	else 
		return false;}

case 19: {
	if((grid1.charAt(16)==c1 && grid1.charAt(22)==c1)||(grid1.charAt(18)==c1 && grid1.charAt(17)==c1)||(grid1.charAt(5)==c1 && grid1.charAt(13)==c1))
		return true;
	else 
		return false;}

case 20: {
	if((grid1.charAt(21)==c1 && grid1.charAt(22)==c1)||(grid1.charAt(15)==c1 && grid1.charAt(17)==c1)||(grid1.charAt(0)==c1 && grid1.charAt(9)==c1))
		return true;
	else 
		return false;}

case 21: {
	if((grid1.charAt(22)==c1 && grid1.charAt(20)==c1))
		return true;
	else 
		return false;}

case 22: {
	if((grid1.charAt(21)==c1 && grid1.charAt(20)==c1)||(grid1.charAt(2)==c1 && grid1.charAt(14)==c1)||(grid1.charAt(16)==c1 && grid1.charAt(19)==c1))
		return true;
	else 
		return false;}
default : return false;

}




}

public static void main(String[] args)
	{
	out.println(grid_main);
	Scanner sc2;
	try {
	// sc2 = new Scanner(new File("board1.txt"));
	 sc2 = new Scanner(new File(args[0]));
	 while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine()).useDelimiter(" ");
			while (s2.hasNext()) {
				grid_main = new StringBuffer(s2.next());}
		}}
	catch (FileNotFoundException e) {
		e.printStackTrace();  
	}
	
	
	
	
	
	try{
		//FileOutputStream fout=new FileOutputStream("board2.txt");
FileOutputStream fout=new FileOutputStream(args[1]);		
//sc2 = new Scanner(new File(args[1]));
		PrintStream pw=new PrintStream(fout);
	System.setOut(pw);}
	catch(IOException e1) {
		System.out.println("Error during reading/writing");
	}
	
		ABOpening obj1=new ABOpening();
		out.println("Welcome to Nine Men Morris !!! \n");
		//obj1.player =args[2].charAt(0);
		obj1.player ='W';
		//obj1.depth = 4;
		obj1.depth = Integer.parseInt(args[2]);
		//obj1.print_grid(new StringBuffer(grid_main));
	/*	for(int k23=0;k23<6;k23++)
		{
		obj1.nxt_move(new StringBuffer(grid_main),'W');
		obj1.nxt_move(new StringBuffer(grid_main),'B');
		} */
		obj1.nxt_move(new StringBuffer(grid_main),obj1.player,obj1.depth);
		
	}
	
	
	
}
