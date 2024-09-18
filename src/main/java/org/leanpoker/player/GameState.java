package org.leanpoker.player;

import java.util.List;

public final class GameState {
    public String tournament_id;
    public String game_id;
    public List<PlayerRecord> players;
    public float small_blind;
    public float big_blind;
    public float orbits;
    public float dealer;
    public List<Object> community_cards;
    public float current_buy_in;
    public float pot;
    public float in_action;
    public float minimum_raise;
    public float bet_index;
    public int round;

    public int currentMaxBet() {
        return players.stream()
                .filter(playerRecord -> !playerRecord.name.equals("Bets for TDD"))
                .mapToInt(playerRecord -> Integer.parseInt(playerRecord.bet)).max().orElse(100);
    }


    public int allBetsSum() {
        return players.stream()
//                .filter(playerRecord -> !playerRecord.name.equals("Bets for TDD"))
                .mapToInt(playerRecord -> Integer.parseInt(playerRecord.bet)).sum();
    }

    @Override
    public String toString() {
        return "GameState[" +
                "tournament_id=" + tournament_id + ", " +
                "game_id=" + game_id + ", " +
                "players=" + players + ", " +
                "small_blind=" + small_blind + ", " +
                "big_blind=" + big_blind + ", " +
                "orbits=" + orbits + ", " +
                "dealer=" + dealer + ", " +
                "community_cards=" + community_cards + ", " +
                "current_buy_in=" + current_buy_in + ", " +
                "pot=" + pot + ", " +
                "in_action=" + in_action + ", " +
                "minimum_raise=" + minimum_raise + ", " +
                "bet_index=" + bet_index + ", " +
                "round=" + round + ']';
    }


}

