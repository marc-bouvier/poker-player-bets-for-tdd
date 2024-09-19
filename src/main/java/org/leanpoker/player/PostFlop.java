package org.leanpoker.player;

import java.util.List;
import java.util.stream.Stream;

public class PostFlop {
    static Integer postFlopPlay(GameState state, PlayerRecord us, List<PlayerRecord.Card> communityCards) {
        if ((long) communityCards.size() == 0) {
            return null;
        }
        Stream<String> currentCardRanks = us.hole_cards.stream()
                .map(hand -> hand.rank);

        var communityCardRanks = communityCards.stream()
                .map(hand -> hand.rank).toList();

        boolean two_pairs_only = currentCardRanks
                .filter(o -> !communityCardRanks.stream()
                        .filter(rank -> rank.equals(o))
                        .toList().isEmpty()).toList().size() == 2;
        if (two_pairs_only) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
