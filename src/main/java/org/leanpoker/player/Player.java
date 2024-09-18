package org.leanpoker.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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


        var us = state.players.stream().filter(playerRecord -> playerRecord.name.equals("Bets for TDD"))
                .findFirst().get();
        var faceCards = Set.of("A", "K", "J", "Q", "10");
//        var communityCards = Set.of("10","9", "8", "7", "6", "5", "4", "3", "2");
//

        var communityCards = state.community_cards;
        if (us.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> o.equals("A")).toList().size() == 1) {
            return state.currentMaxBet() + (state.allBetsSum());

        }

        if (us.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> faceCards.contains(o))) {
            return state.currentMaxBet() + (state.allBetsSum());

        }


        if (us.hole_cards.stream()
                .map(hand -> hand.rank)
                .filter(o -> communityCards.stream()
                        .filter(card -> card.rank.equals(o))
                        .toList().size()>0).toList().size() == 1) {
            return state.currentMaxBet() + (state.allBetsSum());
        }

//        community


        if (randomNumber >= 50) {
            return state.currentMaxBet() + (state.allBetsSum());
        }
        return state.currentMaxBet(); // check
    }

    public static void showdown(JsonNode game) {
    }
}
