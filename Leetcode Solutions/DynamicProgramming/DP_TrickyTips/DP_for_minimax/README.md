## 5)Use DP for minimax/game tree: 

for certain games with minimax tree applied, some nodes in tree are shared with different subtrees. So cache for these nodes is necessary. Usually we use HashMap or 2D arrays to cache them.

For minimax tree/game tree:
    a.the value of a leaf is the payoff to the first player when the game terminates in the position represented by this leaf.
    b.the value of an internal vertex at an even level is the maximum of the values of its children, and the value of an internal vertex at an odd level is the minimum of the values of its children. (because the opposite will perform wisely to maximize his score)

(see [Q464 can I win](Q464CanIWin.java), [Q486 predict the winner](Q486PredictTheWinner.java)) 
