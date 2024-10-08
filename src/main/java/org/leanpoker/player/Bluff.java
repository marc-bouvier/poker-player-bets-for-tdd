package org.leanpoker.player;

public class Bluff {
    static Integer bluff(GameState state, int randomNumber) {
        if (state.currentMaxBet() < 200 && randomNumber >= BluffingConstants.postflopBluffPercentage) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return 0;
    }
}
