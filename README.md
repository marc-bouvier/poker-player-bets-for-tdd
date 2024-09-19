poker-player-java
=================

Java client skeleton for Lean Poker 
For more information visit: http://leanpoker.org

This is a gradle project that can be imported into any IDE. To run the player execute :

`./gradlew run`



Quick Fix: Adjust bluffing percentage to 20%
Quick Fix: Do not bluff for the first 300 hands dealt
Quick Fix: Do not bluff if the hand is a multiple of 5


TODO:
1. make sure bluffing is working properly
2. isolate preflop from flop and move bluffing into these functions
3. fix our betting strategy
4. fix our bluffing, we're doing it too much (see preflop for example)

Preflop Strategy
Pot Size < 35 (5 + 10 + 10 + 10)
1. Raise first opening chance with any q/k/a, any broadway, any pair (47% of hands)
   Pot Size >= 35 (5 + 10 + 20) and < 105
2. raise back with any face card or any pair (18% of hands) + bluff >>>15%<<< * 47% of the time (25% total)
   Pot Size >= 105 (5 + 10 + 25 + (25 + 40)) or (40 + 65) and < 145
3. next raise is with AT+/66+ (9% of hands), and bluff with 15% * 47% * >>>50%<<< (3.5% of hands) (12.5% total)
   Pot Size >= 145
3. all-in with AJ+/99+, fold the rest (6.3% of hands)

Postflop (7 cards total, or by river)
Frequency of Poker Hands:
Flush: 6%
Straight: 5%
Three of a kind: 5%
Two Pair: 23.5%
--- treat as air? ---
One Pair: 44%
No Pair: 17%

