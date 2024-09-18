package org.leanpoker.player;

public class Check {
    static int check(GameState state) {
        return state.currentMaxBet();
    }
}
