package org.leanpoker.player;

public class Bluff {
    static Integer bluff(GameState state, int randomNumber) {
        if (randomNumber >= 20) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
