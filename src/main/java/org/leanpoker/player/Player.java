package org.leanpoker.player;

import com.fasterxml.jackson.databind.JsonNode;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonNode request) {
        var game_id = request.get("game_id").asText();
        GameState state = new GameState("gam1e_id");
        System.out.println(request);
        return 1;

    }

    public static void showdown(JsonNode game) {
    }
}
