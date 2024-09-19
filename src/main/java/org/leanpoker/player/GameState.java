package org.leanpoker.player;

import java.util.List;

public final class GameState {
    public String tournament_id;
    public String game_id;
    public List<PlayerRecord> players;
    public float small_blind;
    public float big_blind;
    public int orbits;
    public float dealer;
    public List<PlayerRecord.Card> community_cards;
    public float current_buy_in;
    public float pot;
    public float in_action;
    public float minimum_raise;
    public float bet_index;
    public int round;

    public int currentMaxBet() {
        return players.stream()
                .filter(playerRecord -> !playerRecord.name.equals("Bets for TDD"))
                .mapToInt(playerRecord -> playerRecord.bet).max().orElse(100);
    }


    public int allBetsSum() {
        return players.stream()
//                .filter(playerRecord -> !playerRecord.name.equals("Bets for TDD"))
                .mapToInt(playerRecord -> playerRecord.bet).sum();
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

    public GameState setTournament_id(String tournament_id) {
        this.tournament_id = tournament_id;
        return this;
    }

    public GameState setGame_id(String game_id) {
        this.game_id = game_id;
        return this;
    }

    public GameState setPlayers(List<PlayerRecord> players) {
        this.players = players;
        return this;
    }

    public GameState setSmall_blind(float small_blind) {
        this.small_blind = small_blind;
        return this;
    }

    public GameState setBig_blind(float big_blind) {
        this.big_blind = big_blind;
        return this;
    }

    public GameState setOrbits(int orbits) {
        this.orbits = orbits;
        return this;
    }

    public GameState setDealer(float dealer) {
        this.dealer = dealer;
        return this;
    }

    public GameState setCommunity_cards(List<PlayerRecord.Card> community_cards) {
        this.community_cards = community_cards;
        return this;
    }

    public GameState setCurrent_buy_in(float current_buy_in) {
        this.current_buy_in = current_buy_in;
        return this;
    }

    public GameState setPot(float pot) {
        this.pot = pot;
        return this;
    }

    public GameState setIn_action(float in_action) {
        this.in_action = in_action;
        return this;
    }

    public GameState setMinimum_raise(float minimum_raise) {
        this.minimum_raise = minimum_raise;
        return this;
    }

    public GameState setBet_index(float bet_index) {
        this.bet_index = bet_index;
        return this;
    }

    public GameState setRound(int round) {
        this.round = round;
        return this;
    }
}

