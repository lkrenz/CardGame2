// Class for the individual cards in the deck

public class Card {
    private String suit;
    private String rank;
    private int value;
    private final int cardNum;

    // Constructs a card with given suit, rank, value, and card number
    public Card(String suit, String rank, int value, int cardNum) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.cardNum = cardNum;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString()
    {
        return rank + " of " + suit;
        }
}
