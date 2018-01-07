# Rock Paper Scissors Bots #

This project provides a tournament environment for
rock-paper-scissors games, bots to play the game,
and tools for creating and testing new bots.


## Features ##

* Running customizable round-robin tournaments between selected players
* Training/debugging mode
* Stat breakdown by number of games and rounds won by each bot
* Nemesis tracking
* 6 dummy bots for training and tournament play
* 8 strategic bots
* A user interface for a human to compete


## Bot Descriptions ##

### Dummy bots ###
These bots don't try to win with any intelligence
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
* **BiasBot** -- Plays the counter to a random move previously made by the opponent, thereby gaining an advantage over any player with a bias in their move frequency distribution.
* **FlatBot** -- Chooses moves randomly, but with a bias towards moves chosen less frequently, ultimately attempting to have a perfectly flat move frequency distribution.

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

Tournament Progress: 0% 10% 20% 30% 40% 50% 60% 70% 80% 90% 100%

Name                   Games Won          Rounds Won               Nemesis                Rounds Lost to Nemesis
================================================================================================================
MetaBot                 99/110 (90.0%)    71142/110000 (64.7%)   MarkovBot              33.7%
MarkovBot               86/110 (78.2%)    66264/110000 (60.2%)   ReflectiveBot          33.3%
ReflectiveBot           78/110 (70.9%)    41876/110000 (38.1%)   MetaBot                68.3%
DeBruijnDummy           70/110 (63.6%)    37569/110000 (34.2%)   MetaBot                38.9%
HistoryBot              69/110 (62.7%)    64703/110000 (58.8%)   ReflectiveBot          65.8%
RandomDummy             55/110 (50.0%)    36721/110000 (33.4%)   MetaBot                33.3%
DecayingFrequencyBot    51/110 (46.4%)    59420/110000 (54.0%)   HistoryBot             48.0%
FrequencyBot            44/110 (40.0%)    52830/110000 (48.0%)   DecayingFrequencyBot   69.0%
PatternDummy            29/110 (26.4%)    23965/110000 (21.8%)   HistoryBot             99.1%
PaperDummy              28/110 (25.5%)    23487/110000 (21.4%)   ScissorsDummy          100.0%
ScissorsDummy           21/110 (19.1%)    23170/110000 (21.1%)   RockDummy              100.0%
RockDummy               17/110 (15.5%)    22637/110000 (20.6%)   PaperDummy             100.0%
```


## Creating a New Bot ##
1. Extend the `RockPaperScissorsPlayer` class and implement the `makeMove()` method.
2. Load the player in `TournamentMain` so it is
participating in the tournament

The `Move` class contains several helper methods
available for all bots to use.