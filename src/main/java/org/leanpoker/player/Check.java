package org.leanpoker.player;

public class Check {
    static int check(GameState state) {
        if (state.currentMaxBet() == state.ourBetSize()) {
            return 0;
        }
        return state.currentMaxBet();
    }
}
