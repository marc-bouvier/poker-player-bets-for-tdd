package org.leanpoker.player;

public class PreflopBluff {
    static Integer bluff(GameState state, int randomNumber) {
        if ((long) state.community_cards.size() > 0) {
            return null;
        }
        if (state.orbits < 25) {
            return state.currentMaxBet();
        }
        if (state.currentMaxBet() < 35 && randomNumber >= 90) {
            return state.currentMaxBet() + state.allBetsSum();
        }
        if (state.currentMaxBet() > 35 && state.currentMaxBet() < 105 && randomNumber >= 85) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        if (state.currentMaxBet() > 105 && state.currentMaxBet() < 145 && randomNumber >= 50) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return state.currentMaxBet();
    }
}
