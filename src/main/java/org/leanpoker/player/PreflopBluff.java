package org.leanpoker.player;

public class PreflopBluff {
    static Integer bluff(GameState state, int randomNumber) {
        if ((long) state.community_cards.size() > 0) {
            return null;
        }
        if (state.orbits < 25) {
            return state.currentMaxBet();
        }
        if (randomNumber >= 80) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
