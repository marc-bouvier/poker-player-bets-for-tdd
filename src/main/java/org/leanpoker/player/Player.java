package org.leanpoker.player;

import com.fasterxml.jackson.databind.JsonNode;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonNode request) {
        var game_id = request.get("game_id").asText();
        GameState state = new GameState(game_id);
        System.out.println("INFO: " + state);
        return 1;

    }

    public static void showdown(JsonNode game) {
    }
}
