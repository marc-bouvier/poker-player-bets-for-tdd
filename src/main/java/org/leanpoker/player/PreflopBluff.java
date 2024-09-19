package org.leanpoker.player;

public class PreflopBluff {
    static Integer bluff(GameState state, int randomNumber) {
        if ((long) state.community_cards.size() > 0) {
            return null;
        }
        if (state.currentMaxBet() > state.ourBetSize() && state.orbits < 25) {
            return 0;
        }
        if (state.currentMaxBet() < 35 && randomNumber >= 90) {
            return state.currentMaxBet() + state.allBetsSum();
        }
        if (state.currentMaxBet() > 35 && state.currentMaxBet() < 105 && randomNumber >= 85) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        if (state.currentMaxBet() > 100 && state.currentMaxBet() < 205 && randomNumber >= 50) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        // fold bad hands
        if (state.currentMaxBet() > state.ourBetSize()) {
            return 0;
        }
        return state.currentMaxBet();
    }
}
