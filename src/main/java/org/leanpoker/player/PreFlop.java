package org.leanpoker.player;

import java.util.Set;

public class PreFlop {
    static Integer preFlop(GameState state, PlayerRecord teamBetForTdd, Set<String> faceCards) {
        if ((long) state.community_cards.size() > 0) {
            return null;
        }

        // any Ace
        Integer currentBet = null;
        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }
        // any King
        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("K")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }
        // any Queen
        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("Q")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }

        // any facecard
        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> faceCards.contains(o))) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }

        return currentBet;
    }
}
