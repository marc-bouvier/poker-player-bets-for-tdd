package org.leanpoker.player;

import java.util.List;

public class PlayerRecord {
    public String name; // : "": "Skyjo",
    public String stack; // : "": 989,
        public String status; // : "": "active",
    public String bet; // : "": 10,
        public String time_used; // : "": 335958,
    public String version; // : "": "log requests",
    public String id; // : "": 0
    public List<Card> hole_cards;

    public static class Card {
        public String rank; // ": "A",
        public String suit; // ": "clubs"
    }
}
