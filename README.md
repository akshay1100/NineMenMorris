# NineMenMorris

Programming Project
Artificial Intelligence

Steps to execute the code:

1)	javac <filename.java>
2)	java <filename> <inputfile> <outputfile> <depth>

¬	Output:
	Output is generated in the <output.txt> file

Comparison b/w MiniMax and Alpha-Beta Pruning:
1)	Opening Game
a)	MiniMax
Current Board Postion: xxxxxxBBxWWWWBBxWxxBxxx
Next move is: 
New Board Position: xxxxxxBBWWWWWxBxWxxBxxx
Best value for move is: 1
Positions evaluated: 50853
b)	Alpha-Beta
Current Board Postion: xxxxxxBBxWWWWBBxWxxBxxx
Next move is: 
New Board Position: xxxxxxBBWWWWWxBxWxxBxxx
Best value for move is: 1
Positions evaluated: 2162



2) Mid Game
	a) MiniMax
Current Board Postion: xWxxxxBBxWWBBxWxxBxxWxx
Next move is: 
New Board Position: WxxxxxxBxWWBBxWxxBxxWxx
Best value for move is: -4
Positions evaluated: 19335

	b) Alpha-Beta
Current Board Postion: xWxxxxBBxWWBBxWxxBxxWxx
Next move is: 
New Board Position: WxxxxxxBxWWBBxWxxBxxWxx
Best value for move is: -4
Positions evaluated: 803

Static function vs Improved Static function
a)	Current Board Postion: xxxxxxxxxxxxxxxxxxxxxxx (open game)
New Board Position: xxxxxxxxxxxxxxxxxxxxxxW (new Static estimate)
New Board Position: Wxxxxxxxxxxxxxxxxxxxxx (given Static estimate)

b)	Current Board Postion: xxBxxxWxxxxxxxWxxxBxxxx (open game)
New Board Position: xxBxxxWxWxxxxxWxxxBxxxx (new Static estimate)
New Board Position: WxBxxxWxxxxxxxWxxxBxxxx (given Static estimate)

c)	Current Board Postion: xxxxxWxBxWxBBxWxxBxxWxx (mid game – (black to move))
New Board Position: xxxxxWxBBWxBxxWxxBxxWxx (new Static estimate)
New Board Position: xxxxBWxxxWxBBxWxxBxxWxx (given Static estimate)




Improved Static Function:
¬	An improved static function has been included in program. The new static function generates not only different results but also better results. The difference between the two static functions can be identified in the above comparison.

¬	The static function, provides good results usually especially when the depth is relatively low thus saving computing time and memory.

¬	New static functions not only delivers good results but also reduces the total positions considered for the output evaluation.

¬	The new static function performs better than the given static function because new comparison for mill formation in horizontal, vertical & diagonal positions have been added.

¬	For the mid game static function, mill formation has been added plus logic for blocking the opponent moves has also been added to the given static estimate, which improves the estimate.



