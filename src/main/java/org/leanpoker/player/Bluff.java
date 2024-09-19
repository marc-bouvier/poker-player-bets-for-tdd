package org.leanpoker.player;

public class Bluff {
    static Integer bluff(GameState state, int randomNumber) {
        if (state.orbits < 300) {
            return null;
        }
        if (randomNumber >= 80) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
