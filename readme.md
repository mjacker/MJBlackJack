#BlackJack project
This project is about creating a simple version of BlackJack game applying concepts from a java course.

# Task Flow Queue
[ ] Global asking if want to continue next round
    - if yes: continue.
    - if no: all players left the table.
[] Aplying interfaces to menu.
[x] can play next round if player has enough coins.
[x] Join the table, left the table.
[x] Fix count score for all players, need implements bust.
[x] implements n players.
[x] implements n rounds in the game.
[x] player auto choose randomly between hit and stand.
[x] Delete press enter to start.

# Features list to implement in.
- After game over, ask new game or exit the game.
- [optional] try to implement interfaces.
- Before start the game choose how many players will play the game, preferably [1-3] players.
- set global config for round wait in miliseconds.

# Ideas to improve.
- In the first Table view, only show the cards (hidden and not hidden) from dealer, for table view after each player plays, show those cards in table view.

# Testing
- set a random choose hit/stand, over rounds. (add config to change it faster).
[ ] Need update testing for current n players and rounds.

# Known Bugs found to fix.
[] A player can start the game win two pairs of `Ax` and `Ax`, as consequence the player will lost the round before starting (A = 11)
Ax + Ax = 22 -> busted.
    - A solution could be implement double values for `A` as `1` or `11`.
[] Printed messgae 'Press enter to next player continue..." after las player playing shoud not be displayed.
[] Name imput player no visible, input name is empty.
[fixed] - reset hand cards before a new round.
[fixed] - popCard taking random 32 value get error when less then 32 card (collition by chance) in deck.
[fixed] Error when number of players input are: `0`, `pato`.
[x] Negative bet when not more coins.

