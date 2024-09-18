package org.leanpoker.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class Player {


    static final String VERSION = "Default Java folding player";
    private static ObjectMapper mapper = new ObjectMapper();

    public static int betRequest(JsonNode request) {
        try {
            GameState state = mapper.readValue(request.toPrettyString(), GameState.class);
            // detect aces
            Integer x = playerAction(state);

            if (x != null) return x;
            System.out.println("INFO: " + state);
            return state.currentMaxBet() + (state.allBetsSum());
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: " + e);
        }

        return 101;

    }

    public static Integer playerAction(GameState state) {
        var us = state.players.stream().filter(playerRecord -> playerRecord.name.equals("Bets for TDD"))
                .findFirst().get();
        var faceCards = Set.of("A", "K", "J", "Q");
        if (us.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> faceCards.contains(o))) {
            return state.currentMaxBet() + (state.allBetsSum());

        }

        return null;
    }

    public static void showdown(JsonNode game) {
    }
}
