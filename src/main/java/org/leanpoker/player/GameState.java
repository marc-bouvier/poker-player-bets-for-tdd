package org.leanpoker.player;

import java.util.List;

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


}

