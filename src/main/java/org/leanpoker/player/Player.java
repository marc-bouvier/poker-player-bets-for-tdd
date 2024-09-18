package org.leanpoker.player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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


        var teamBetForTdd = state.players.stream()
                .filter(playerRecord -> playerRecord.name.equals("Bets for TDD"))
                .findFirst().get();
        var faceCards = Set.of("A", "K", "J", "Q", "10");
//        var communityCards = Set.of("10","9", "8", "7", "6", "5", "4", "3", "2");
//

        var communityCards = state.community_cards;

        Integer currentBet = PreFlop.preFlop(state, teamBetForTdd, faceCards);
        if (currentBet != null) return currentBet;

        // post flop
        Integer state1 = PostFlop.postFlopPlay(state, teamBetForTdd, communityCards);
        if (state1 != null) return state1;

        // bluffing

        Integer state2 = Bluff.bluff(state, randomNumber);
        if (state2 != null) return state2;

        return Check.check(state); // check
    }

    public static void showdown(JsonNode game) {
    }
}
