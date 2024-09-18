package org.leanpoker.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            return state.currentMaxBet() +( state.allBetsSum());
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: " + e);
        }

        return 101;

    }

    public static Integer playerAction(GameState state) {
        var us = state.players().stream().filter(playerRecord -> playerRecord.name.equals("Bets for TDD"))
                .findFirst().get();
        if (us.hole_cards.stream()
                .map(hand -> hand.rank)
                .allMatch(o -> o.equals("A"))) {
            return 1000;
        }
        return null;
    }

    public static void showdown(JsonNode game) {
    }
}
