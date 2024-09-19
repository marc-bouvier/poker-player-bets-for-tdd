package org.leanpoker.player;

import java.util.List;
import java.util.stream.Stream;

public class PostFlop {
    static Integer postFlopPlay(GameState state, PlayerRecord us, List<PlayerRecord.Card> communityCards) {
        if ((long) communityCards.size() == 0) {
            return null;
        }
        List<String> currentCardRanks = us.hole_cards.stream()
                .map(hand -> hand.rank).toList();

        var communityCardRanks = communityCards.stream()
                .map(hand -> hand.rank).toList();

        boolean two_pairs_only = currentCardRanks
                .stream()
                .filter(o -> !communityCardRanks.stream()
                        .filter(rank -> rank.equals(o))
                        .toList().isEmpty()).toList().size() == 2;
        if (two_pairs_only) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        boolean big_single_pair = currentCardRanks
                .stream()
                .filter(rank -> rank.equals("10") || rank.equals("J") || rank.equals("Q") || rank.equals("K") || rank.equals("A"))
                .filter(o -> !communityCardRanks.stream()
                        .filter(rank -> rank.equals(o))
                        .toList().isEmpty()).toList().size() == 1;
        if (big_single_pair) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
