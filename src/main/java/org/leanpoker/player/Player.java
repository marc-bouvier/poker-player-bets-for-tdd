package org.leanpoker.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class Player {


    static final String VERSION = "Default Java folding player";
    private static ObjectMapper mapper = new ObjectMapper();

    public static int betRequest(JsonNode request) {
        try {
            GameState state = mapper.readValue(request.toPrettyString(), GameState.class);
            var randomNumber = new Random().nextInt(100);
            Integer x = playerAction(state, randomNumber);

            if (x != null) return x;
//            System.out.println("INFO: " + state);
            return state.currentMaxBet() + (state.allBetsSum());
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: " + e);
        }

        return 101;

    }

    public static Integer playerAction(GameState state, int randomNumber) {


        var teamBetForTdd = state.players.stream()
                .filter(playerRecord -> playerRecord.name.equals("Bets for TDD"))
                .findFirst().get();
        var faceCards = Set.of("A", "K", "J", "Q", "10");
//        var communityCards = Set.of("10","9", "8", "7", "6", "5", "4", "3", "2");
//

        var communityCards = state.community_cards;

        Integer currentBet = preFlop(state, teamBetForTdd, faceCards);
        if (currentBet != null) return currentBet;

        // post flop


        Integer state1 = postFlopPlay(state, teamBetForTdd, communityCards);
        if (state1 != null) return state1;


// bluffing

        if (randomNumber >= 50) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return state.currentMaxBet(); // check
    }

    private static Integer preFlop(GameState state, PlayerRecord teamBetForTdd, Set<String> faceCards) {
        Integer currentBet = null;
        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().size() == 1) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }

        if (teamBetForTdd.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> faceCards.contains(o))) {
            currentBet = state.currentMaxBet() + (state.allBetsSum());

        }
        return currentBet;
    }

    private static Integer postFlopPlay(GameState state, PlayerRecord us, List<PlayerRecord.Card> communityCards) {
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

    public static void showdown(JsonNode game) {
    }
}
