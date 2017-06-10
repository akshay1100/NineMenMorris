//package game;
import java.util.*;
import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class MiniMaxGameBlack {
 
	static StringBuffer grid_main= new StringBuffer("BWxxWxxxxBxxxxxxxxxxxxx");
	static long positions_evaluated=0;
	 char player = 'W';
	int depth ;
	char mySeed = 'W' ;
	char oppSeed = 'B';
	
ArrayList<StringBuffer> hopping(int location,StringBuffer grid3,char seed)
{
	StringBuffer grid1=null;
	ArrayList<StringBuffer> list_moves= new ArrayList<StringBuffer>();
	for(int i=0;i<grid3.length();i++)
	{
		if(grid3.charAt(i)=='x')
		{
			grid1=new StringBuffer(grid3);
			grid1.setCharAt(i,seed);
			grid1.setCharAt(location,'x');
		
			if ( check_mill(i,new StringBuffer(grid1)))
			{
			 list_moves.addAll(remove_oppo_mill(new StringBuffer(grid1),seed));
			}
			else
			list_moves.add(grid1);		
		}
		
	}	

	return list_moves;
	
	}

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

//int bestScore = (seed == mySeed) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
int currentScore;
StringBuffer curr_grid=null;

if(list1.isEmpty()||depth==0)
{	//out.println("grid : "+grid1);
	
	currentScore=evaluate(new StringBuffer(grid1));	
	//out.println("dept "+depth+"  score  "+currentScore+ "  grid  "+grid1);
	//out.println(curr_grid);
	StringBuffer[] k3 = {(new StringBuffer(Integer.toString(currentScore))),curr_grid};
	//for(StringBuffer k5:k3)
	//out.println(k5);
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
//out.println(curr_grid);
StringBuffer[] k3 = {(new StringBuffer(Integer.toString((seed == mySeed) ? alpha : beta))),curr_grid};
//for(StringBuffer k5:k3)
//out.println(k5);
return k3;
}
}


ArrayList<StringBuffer> available_moves(StringBuffer grid2,char seed)
{
ArrayList<StringBuffer> list_moves= new ArrayList<StringBuffer>();
ArrayList<Integer> locations_seed=new ArrayList<Integer>();
for(int i1=0;i1<grid2.length();i1++)
{
	if(grid2.charAt(i1)==seed)
		locations_seed.add(i1);		
}
int w_peice=0,b_peice=0;
for(int i1=0;i1<grid2.length();i1++)
{
	if(grid2.charAt(i1)==seed)
		locations_seed.add(i1);		
}

if(w_peice==3||b_peice==3) //hopping
{
	for(int w:locations_seed)
	{
	list_moves.addAll((hopping(w,new StringBuffer(grid2),seed)));

	}
	return list_moves;	
	}
else
{
for(int w:locations_seed)
{
list_moves.addAll((checkmove(w,new StringBuffer(grid2),seed)));

}

return list_moves;	}	
	
	
	
/*out.println("in moves");out.println(grid2);
	
StringBuffer grid1=null;	
ArrayList<StringBuffer> list_moves= new ArrayList<StringBuffer>();
//ArrayList<Integer> all_moves=new ArrayList<Integer>();
for(int i=0;i<grid2.length();i++)
{

	//out.println("pos: "+i);
	//out.println("grid2:  "+grid2);
	if(grid2.charAt(i)=='x')
	{
		grid1=new StringBuffer(grid2);
		grid1.setCharAt(i,seed);
		//out.println("grid1: "+grid1);
		if ( check_mill(i,new StringBuffer(grid1)))
		{
		 list_moves=remove_oppo_mill(new StringBuffer(grid1),new ArrayList<StringBuffer>(list_moves));
		}
		
		list_moves.add(grid1);}
	
}
return list_moves;

*/
}

ArrayList<StringBuffer> checkmove(int location,StringBuffer grid3,char seed)
{
StringBuffer grid1=null;
ArrayList<StringBuffer> list_moves= new ArrayList<StringBuffer>();
ArrayList<Integer> nei= neighbors(location);
for(int k3:nei)
{
	
	if(grid3.charAt(k3)=='x')
	{
		grid1=new StringBuffer(grid3);
		grid1.setCharAt(k3,seed);
		grid1.setCharAt(location,'x');
		if ( check_mill(k3,new StringBuffer(grid1)))
		{
		 list_moves.addAll(remove_oppo_mill(new StringBuffer(grid1),seed));
		}
		else
		list_moves.add(grid1);}
		
	}


return list_moves;	
}

ArrayList<Integer> neighbors(int location)
{
	ArrayList<Integer> num=new ArrayList<Integer>();
	switch(location)
	{
	case 0: { num.add(1);num.add(3);num.add(9); return num;}
	case 1: { num.add(0);num.add(2);num.add(4); return num;}
	case 2: { num.add(1);num.add(5);num.add(14); return num;}
	case 3: { num.add(0);num.add(4);num.add(6);num.add(10); return num;}
	case 4: { num.add(1);num.add(3);num.add(5);num.add(7); return num;}
	case 5: { num.add(4);num.add(2);num.add(8);num.add(13); return num;}
	case 6: { num.add(3);num.add(7);num.add(11); return num;}
	case 7: { num.add(4);num.add(6);num.add(8); return num;}
	case 8: { num.add(5);num.add(7);num.add(12); return num;}
	case 9: { num.add(0);num.add(20);num.add(10); return num;}
	case 10: { num.add(3);num.add(17);num.add(11);num.add(9); return num;}
	case 11: { num.add(6);num.add(15);num.add(10); return num;}
	case 12: { num.add(8);num.add(16);num.add(13); return num;}
	case 13: { num.add(5);num.add(19);num.add(14);num.add(12); return num;}
	case 14: { num.add(13);num.add(2);num.add(22); return num;}
	case 15: { num.add(11);num.add(17); return num;}
	case 16: { num.add(12);num.add(19); return num;}
	case 17: { num.add(20);num.add(15);num.add(10);num.add(18); return num;}
	case 18: { num.add(17);num.add(21);num.add(19); return num;}
	case 19: { num.add(16);num.add(18);num.add(13);num.add(22); return num;}
	case 20: { num.add(9);num.add(17);num.add(21); return num;}
	case 21: { num.add(20);num.add(18);num.add(22); return num;}
	case 22: { num.add(21);num.add(19);num.add(14); return num;}

	default: return num;
	
	
	}
}


int evaluate(StringBuffer grid6)
{
	int score =0 ;
	positions_evaluated=positions_evaluated+1;
	StringBuffer grid1=grid6;
	int w_peice=0,b_peice=0,w_moves=0,b_moves=0;
	ArrayList<StringBuffer> w_pos= available_moves(new StringBuffer(grid1),'W');
	ArrayList<StringBuffer> b_pos= available_moves(new StringBuffer(grid1),'B');
	//out.println("in ava: "+grid1+" " +grid1.charAt(0)+" "+grid1.charAt(1)+" "+grid1.charAt(2) );
	//for white and black peices

	for(int i1=0;i1<grid1.length();i1++)
	{
		if(grid1.charAt(i1)=='W')
			w_peice++;	
		if(grid1.charAt(i1)=='B')
			b_peice++;	
	}
	w_moves=w_pos.size();
	b_moves=b_pos.size();

	if(b_peice<=2)
		return 1000000;
	else if(w_peice<=2)
		return -1000000;
	else if(b_moves==0)
		return 1000000;
	else
		return (1000*(w_peice-b_peice)-b_peice);

	

	
	
/*	for(int i1=0;i1<grid1.length();i1++)
	{
		if(grid1.charAt(i1)=='W')
			w_peice++;	
		if(grid1.charAt(i1)=='B')
			b_peice++;	
	}
	if(w_peice<3)
		return 1000000;
	if(b_peice<3)
		return -1000000;
	
	w_moves=w_pos.size();
	b_moves=b_pos.size();
	
	if(w_moves==0)
		return -1000000;
	if(b_moves==0)
		return 1000000;
	
	score+=w_peice * 100 - b_peice*100;
	score+=w_moves * 2 - b_moves * 2;
	
	*/
	/*

	score += evaluateLine (grid1.charAt(0),grid1.charAt(1),grid1.charAt(2));
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
	
	
//	*/
	
	//return score;
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
	{out.println(q2+""+q3+""+q4+"  :"+ "0");
		return 0; }
	else 
		score = 1;
	}
	else if (q3==oppSeed)
	{ if(score == -1)
		score=-10;
	else if(score == 1)
	{out.println(q2+""+q3+""+q4+"  :"+"0");
		return 0;}
	else 
		score = -1;
	}
		
	
    if (q4 == mySeed) {
        if (score > 0) { 
           score *= 10;
        } else if (score < 0) { 
        	out.println(q2+""+q3+""+q4+"  :"+"0");
           return 0;
        } else { 
           score = 1;
        }
     } else if (q4 == oppSeed) {
        if (score < 0) { 
           score *= 10;
        } else if (score > 1) { 
        	out.println(q2+""+q3+""+q4+"  :"+"0");
           return 0;
        } else { 
           score = -1;
        }
     }
	
	out.println(q2+""+q3+""+q4+"  :"+score);
return score;
}

ArrayList<StringBuffer> remove_oppo_mill(StringBuffer grid1,char seed)
{
	char opposeed;
	if(seed=='W')
		opposeed='B';
	else
		opposeed='W';
	
	ArrayList<StringBuffer> list=new ArrayList<StringBuffer>();
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
 	out.println(grid.charAt(9)+" "+grid.charAt(10)+" "+grid.charAt(11)+"     "+grid.charAt(12)+"  "+grid.charAt(13)+" "+grid.charAt(14)); 	
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
	Scanner sc2;
	try {
	 //sc2 = new Scanner(new File("board_1.txt"));
	 sc2 = new Scanner(new File(args[0]));
	 while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine()).useDelimiter(" ");
			while (s2.hasNext()) {
				grid_main = new StringBuffer(s2.next());}
		}}
	catch (FileNotFoundException e) {
		e.printStackTrace();  
	}
	System.out.println("String is:"+grid_main);
	try{
		//FileOutputStream fout=new FileOutputStream("board_2.txt");
FileOutputStream fout=new FileOutputStream(args[1]);		
PrintStream pw=new PrintStream(fout);
	System.setOut(pw);}
	catch(IOException e1) {
		System.out.println("Error during reading/writing");
	}
		MiniMaxGameBlack obj1=new MiniMaxGameBlack();
		out.println("Welcome to Nine Men Morris !!! \n");
		//obj1.player =args[2].charAt(0);
		obj1.player ='B';
		//obj1.depth = 4;
		obj1.depth = Integer.parseInt(args[2]);
		//obj1.print_grid(new StringBuffer(grid_main));
		//obj1.print_grid(new StringBuffer(grid));
		//obj1.nxt_move();
		obj1.nxt_move(new StringBuffer(grid_main),obj1.player,obj1.depth);
	}
	
	
	
}
