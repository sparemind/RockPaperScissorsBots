# Rock Paper Scissors Bots #

This project provides a tournament environment for
rock-paper-scissors games, bots to play the game,
and tools for creating and testing new bots.


## Features ##

* Running customizable round-robin tournaments between selected players
* Training/debugging mode
* Stat breakdown by number of games and rounds won by each bot
* 6 dummy bots for training and tournament play
* 6 strategic bots
* A user interface for a human to compete


## Bot Descriptions ##

### Dummy bots: ###
These bots don't try to win with any intelligence,
and just play simple, exploitable strategies.
By succeeding against these bots, competitive bots
can gain an overall advantage in the tournament.
* **RandomDummy** -- Always plays random moves. Used as a baseline.
* **RockDummy** -- Always plays rock.
* **PaperDummy** -- Always plays paper.
* **ScissorsDummy** -- Always plays scissors.
* **PatternDummy** -- Creates a random pattern of moves before the game begins, then plays it repeatably for the entirety of the game.
* **DeBruijnDummy** -- Plays moves according to a fixed de Bruijn sequence consisting of 13 difference sequences 81 moves long (de Bruijn sequences of length 4).

### Strategic bots ###
These bots utilize single, intelligent strategies.
* **FrequencyBot** -- Plays the counter to the opponent's most frequent move.
* **DecayingFrequencyBot** -- Plays the counter to the opponent's most frequent move. Move frequencies decay over time so more recent moves are weighted higher
* **HistoryBot** -- Analyzes the opponents move history to find the longest move sequence that matches the most recent move sequence. Then looks at what they played next and plays the counter to it.
* **MarkovBot** -- Tracks the opponent's previous moves as a Markov model, predicting what their most likely next move is based on what patterns they've played most frequently in the past and what moves they've most recently played.
* **ReflectiveBot** -- Same as HistoryBot, but also runs the same history analysis on its own move history to consider what an opponent would play if they are also doing history analysis. Keeps track of how normal history analysis or second guessing its own history would have performed against what the opponent chose, and uses the best.
* **MetaBot** -- Same as HistoryBot, but also keeps track of how different "shifts" to its chosen move would have performed against what the opponent chose, and applies the best.


## Tournament Format ##

Tournaments consist of round-robin play wherein
every player plays a match against every other
player. A match consists of a set number of
games, with each game lasting a set number of
rounds.


Victors are determined as the player with the
most games won. Ties are broken by number of
individual rounds won.

## Example Tournament Output ##
```
Playing tournament with:
	1000 round long games
	10 game long matches
	12 competitors

Name                   Games Won         Rounds Won
=================================================================
MetaBot                 98/110 (89.0%)    71182/110000 (64.7%)
MarkovBot               80/110 (72.7%)    66241/110000 (60.2%)
ReflectiveBot           77/110 (70.0%)    41584/110000 (37.8%)
HistoryBot              71/110 (64.5%)    64761/110000 (58.8%)
DeBruijnDummy           65/110 (59.0%)    37446/110000 (34.0%)
RandomDummy             55/110 (50.0%)    36995/110000 (33.6%)
DecayingFrequencyBot    53/110 (48.1%)    59759/110000 (54.3%)
FrequencyBot            42/110 (38.1%)    53029/110000 (48.2%)
PatternDummy            39/110 (35.4%)    28247/110000 (25.6%)
PaperDummy              27/110 (24.5%)    22057/110000 (20.0%)
ScissorsDummy           19/110 (17.2%)    22951/110000 (20.8%)
RockDummy               17/110 (15.4%)    23391/110000 (21.2%)
```


## Creating a New Bot ##
1. Implement the `RockPaperScissorsPlayer` interface
2. Load the player in `TournamentMain` so it is
participating in the tournament

The `Move` class contains several helper methods
available for all bots to use.