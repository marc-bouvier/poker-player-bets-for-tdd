package org.leanpoker.player;

import java.util.List;
import java.util.OptionalInt;

public record GameState(
        String tournament_id,
        String game_id,
        List<PlayerRecord> players,
        float small_blind,
        float big_blind,
        float orbits,
        float dealer,
        List<Object> community_cards,
        float current_buy_in,
        float pot,
        float in_action,
        float minimum_raise,
        float bet_index,
        int round

) {

    public int currentMaxBet() {
        return players.stream()
                .filter(playerRecord -> !playerRecord.name.equals("Bets for TDD"))
                .mapToInt(playerRecord -> Integer.parseInt(playerRecord.bet)).max().orElse(100);
    }

}

