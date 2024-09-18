package org.leanpoker.player;

import java.util.List;
import java.util.stream.Stream;

public class PostFlop {
    static Integer postFlopPlay(GameState state, PlayerRecord us, List<PlayerRecord.Card> communityCards) {
        Stream<String> currentCardRanks = us.hole_cards.stream()
                .map(hand -> hand.rank);

        var communityCardRanks = communityCards.stream()
                .map(hand -> hand.rank).toList();

        boolean one_or_two_pairs = currentCardRanks
                .filter(o -> !communityCardRanks.stream()
                        .filter(rank -> rank.equals(o))
                        .toList().isEmpty()).toList().size() == 1;
        if (one_or_two_pairs) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return null;
    }
}
