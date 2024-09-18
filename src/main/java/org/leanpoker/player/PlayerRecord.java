package org.leanpoker.player;

import java.util.List;

public class PlayerRecord {
    public String name; // : "": "Skyjo",
    public int stack; // : "": 989,
        public String status; // : "": "active",
    public int bet; // : "": 10,
        public String time_used; // : "": 335958,
    public String version; // : "": "log requests",
    public String id; // : "": 0
    public List<Card> hole_cards;

    public static class Card {
        /** 2-10 and J,Q,K,A*/
        public String rank; // ": "A",
        public String suit; // ": "clubs"

        public Card setRank(String rank) {
            this.rank = rank;
            return this;
        }

        public Card setSuit(String suit) {
            this.suit = suit;
            return this;
        }
    }

    public PlayerRecord setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerRecord setStack(int stack) {
        this.stack = stack;
        return this;
    }

    public PlayerRecord setStatus(String status) {
        this.status = status;
        return this;
    }

    public PlayerRecord setBet(int bet) {
        this.bet = bet;
        return this;
    }

    public PlayerRecord setTime_used(String time_used) {
        this.time_used = time_used;
        return this;
    }

    public PlayerRecord setVersion(String version) {
        this.version = version;
        return this;
    }

    public PlayerRecord setId(String id) {
        this.id = id;
        return this;
    }

    public PlayerRecord setHole_cards(List<Card> hole_cards) {
        this.hole_cards = hole_cards;
        return this;
    }
}
