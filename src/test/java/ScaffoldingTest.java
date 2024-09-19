import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.leanpoker.player.GameState;
import org.leanpoker.player.Player;
import org.leanpoker.player.PlayerRecord;
import org.leanpoker.player.PlayerRecord.Card;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.leanpoker.player.Player.betRequest;

public class ScaffoldingTest {


    private final String exampleRequest = "{\n" +
            "  \"tournament_id\": \"66eab3c4fd273a00027d91df\",\n" +
            "  \"game_id\": \"66eae9100101350002fbfd88\",\n" +
            "  \"round\": 25,\n" +
            "  \"players\": [\n" +
            "    {\n" +
            "      \"name\": \"Skyjo\",\n" +
            "      \"stack\": 989,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 10,\n" +
            "      \"time_used\": 335958,\n" +
            "      \"version\": \"log requests\",\n" +
            "      \"id\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Purring Cat\",\n" +
            "      \"stack\": 1000,\n" +
            "      \"status\": \"folded\",\n" +
            "      \"bet\": 0,\n" +
            "      \"time_used\": 326956,\n" +
            "      \"version\": \"Default Java folding player\",\n" +
            "      \"id\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bets for TDD\",\n" +
            "      \"stack\": 996,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 0,\n" +
            "      \"hole_cards\": [\n" +
            "        {\n" +
            "          \"rank\": \"A\",\n" +
            "          \"suit\": \"clubs\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rank\": \"A\",\n" +
            "          \"suit\": \"diamonds\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"time_used\": 318691,\n" +
            "      \"version\": \"Default Java folding player\",\n" +
            "      \"id\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bluff and Burndown\",\n" +
            "      \"stack\": 1000,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 5,\n" +
            "      \"time_used\": 329704,\n" +
            "      \"version\": \"Default TypeScript folding player\",\n" +
            "      \"id\": 3\n" +
            "    }\n" +
            "  ],\n" +
            "  \"small_blind\": 5,\n" +
            "  \"big_blind\": 10,\n" +
            "  \"orbits\": 6,\n" +
            "  \"dealer\": 2,\n" +
            "  \"community_cards\": [],\n" +
            "  \"current_buy_in\": 10,\n" +
            "  \"pot\": 15,\n" +
            "  \"in_action\": 2,\n" +
            "  \"minimum_raise\": 5,\n" +
            "  \"bet_index\": 3\n" +
            "}";
    private final String bluffingRequest = "{\n" +
            "  \"tournament_id\": \"66eab3c4fd273a00027d91df\",\n" +
            "  \"game_id\": \"66eae9100101350002fbfd88\",\n" +
            "  \"round\": 25,\n" +
            "  \"players\": [\n" +
            "    {\n" +
            "      \"name\": \"Skyjo\",\n" +
            "      \"stack\": 989,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 10,\n" +
            "      \"time_used\": 335958,\n" +
            "      \"version\": \"log requests\",\n" +
            "      \"id\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Purring Cat\",\n" +
            "      \"stack\": 1000,\n" +
            "      \"status\": \"folded\",\n" +
            "      \"bet\": 0,\n" +
            "      \"time_used\": 326956,\n" +
            "      \"version\": \"Default Java folding player\",\n" +
            "      \"id\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bets for TDD\",\n" +
            "      \"stack\": 996,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 0,\n" +
            "      \"hole_cards\": [\n" +
            "        {\n" +
            "          \"rank\": \"7\",\n" +
            "          \"suit\": \"clubs\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rank\": \"7\",\n" +
            "          \"suit\": \"diamonds\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"time_used\": 318691,\n" +
            "      \"version\": \"Default Java folding player\",\n" +
            "      \"id\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bluff and Burndown\",\n" +
            "      \"stack\": 1000,\n" +
            "      \"status\": \"active\",\n" +
            "      \"bet\": 5,\n" +
            "      \"time_used\": 329704,\n" +
            "      \"version\": \"Default TypeScript folding player\",\n" +
            "      \"id\": 3\n" +
            "    }\n" +
            "  ],\n" +
            "  \"small_blind\": 5,\n" +
            "  \"big_blind\": 10,\n" +
            "  \"orbits\": 6,\n" +
            "  \"dealer\": 2,\n" +
            "  \"community_cards\": [],\n" +
            "  \"current_buy_in\": 10,\n" +
            "  \"pot\": 15,\n" +
            "  \"in_action\": 2,\n" +
            "  \"minimum_raise\": 5,\n" +
            "  \"bet_index\": 3\n" +
            "}";


    // Null case
    @Test
    void betRequest_always_zero_or_greater(
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode request = mapper.readTree(exampleRequest);
        assertThat(betRequest(request)).isGreaterThanOrEqualTo(0);
    }

    // What is in the request
// Given we have a aces put more money
    @Test
    void given_we_have_a_aces_put_more_money() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode request = mapper.readTree(exampleRequest);
        assertThat(betRequest(request)).isGreaterThanOrEqualTo(25);
    }


    @Test
    void always_at_least_check() throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();


        JsonNode request = mapper.readTree(bluffingRequest);
        assertThat(betRequest(request)).isGreaterThanOrEqualTo(10);


    }


    @ParameterizedTest
    @ValueSource(strings = {"Q", "K", "A", "J", "10"})
    void raise_with_face_cards(String cardOneAndTwo) {

        var game = new GameState();
        PlayerRecord us = new PlayerRecord()
                .setName("Bets for TDD")
                .setBet(20)
                .setStack(1000).setHole_cards(List.of(
                        new Card().setRank("Q").setSuit("clubs"),
                        new Card().setRank("Q").setSuit("diamonds")
                ));
        game.setPlayers(
                List.of(us
                        , new PlayerRecord()
                                .setName("other")
                                .setBet(30)
                                .setStack(1000))
        );

        assertThat(Player.playerAction(game, 49)).isEqualTo(80);

    }


    @Test
    void raise_with_an_ace() {

        var game = new GameState();
        PlayerRecord us = new PlayerRecord()
                .setName("Bets for TDD")
                .setBet(20)
                .setStack(1000).setHole_cards(List.of(
                        new Card().setRank("7").setSuit("clubs"),
                        new Card().setRank("A").setSuit("diamonds")
                ));
        game.setPlayers(
                List.of(us
                        , new PlayerRecord()
                                .setName("other")
                                .setBet(30)
                                .setStack(1000))
        );

        assertThat(Player.playerAction(game, 49)).isEqualTo(80);

    }


    @Test
    void checks_with_k_7() {

        var game = new GameState();
        game.community_cards=List.of();
        PlayerRecord us = new PlayerRecord()
                .setName("Bets for TDD")
                .setBet(20)
                .setStack(1000).setHole_cards(List.of(
                        new Card().setRank("7").setSuit("clubs"),
                        new Card().setRank("K").setSuit("diamonds")
                ));
        game.setPlayers(
                List.of(us
                        , new PlayerRecord()
                                .setName("other")
                                .setBet(30)
                                .setStack(1000))
        );

        assertThat(Player.playerAction(game, 19)).isEqualTo(30);

    }


    @Test
    void post_flop_put_money_in_with_good_hand() {

        var game = new GameState();
        PlayerRecord us = new PlayerRecord()
                .setName("Bets for TDD")
                .setBet(20)
                .setStack(1000).setHole_cards(List.of(
                        new Card().setRank("8").setSuit("clubs"),
                        new Card().setRank("10").setSuit("diamonds")
                ));
        game.setPlayers(
                List.of(us
                        , new PlayerRecord()
                                .setName("other")
                                .setBet(30)
                                .setStack(1000)

                )
        );

        game.community_cards = List.of(
                new Card().setRank("6").setSuit("clubs"),
                new Card().setRank("10").setSuit("hearts"),
                new Card().setRank("3").setSuit("diamonds")
        );


        assertThat(Player.playerAction(game, 49)).isEqualTo(80);

    }

    @Test
    void fold_when_() {
        // 1-100
        // if lower than 50 we fold
        // if higher we raise

        var game = new GameState();
        PlayerRecord us = new PlayerRecord()
                .setName("Bets for TDD")
                .setBet(20)
                .setStack(1000).setHole_cards(List.of(
                        new Card().setRank("Q").setSuit("clubs"),
                        new Card().setRank("Q").setSuit("diamonds")
                ));
        game.setPlayers(
                List.of(us
                        , new PlayerRecord()
                                .setName("other")
                                .setBet(30)
                                .setStack(1000))
        );

        assertThat(Player.playerAction(game, 49)).isEqualTo(80);

    }

    // Pre flop


    // Post flop

    // Fix our bluffing : fold a reasonable amount of time

}
