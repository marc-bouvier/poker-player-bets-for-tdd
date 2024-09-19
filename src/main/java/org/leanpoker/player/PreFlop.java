package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PreFlop {
    static Integer preFlop(GameState state, PlayerRecord teamBetForTdd, Set<String> faceCards) {
        if ((long) state.community_cards.size() > 0) {
            return null;
        }

        // play good hands
        // any Ace
        Integer currentBet = null;
        if (state.currentMaxBet() < 105 && teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }
        // any King
        if (state.currentMaxBet() < 35 && teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("K")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }
        // any Queen
        if (state.currentMaxBet() < 35 && teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("Q")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }

        // any facecard
        if (state.currentMaxBet() < 110 && teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> faceCards.contains(o))) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }
        // only continue with great hands
        boolean anyQueen = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("Q")).toList().isEmpty();
        boolean anyKing = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("K")).toList().isEmpty();
        boolean anyAce = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().isEmpty();
        if (anyQueen && anyAce || anyKing && anyAce) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }
        boolean queens = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("Q")).toList().isEmpty();
        boolean kings = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("K")).toList().isEmpty();
        boolean aces = !teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().isEmpty();
        if (aces || kings || queens) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());
        }
        return currentBet;
    }
}
