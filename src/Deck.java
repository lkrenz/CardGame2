import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> deck;
    private int cardsLeft;
    private static final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private static final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    // Constructor for irregular deck
    public Deck(String[] rank, String[] suit, int[] values) {
        deck = new ArrayList<>();
        cardsLeft = ranks.length * suits.length;
        int cardNum = 0;
        for (int j = 0; j < rank.length; j++) {
            for (String s : suits) {
                deck.add(new Card(s, rank[j], values[j], cardNum));
                cardNum++;
            }
        }
    }

    // Constructor for normal deck
    public Deck() {
        deck = new ArrayList<>();
        cardsLeft = 52;
        int cardNum = 0;
        for (int j = 0; j < ranks.length; j++) {
            for (String s : suits) {
                int val = j;
                if (val > 9) {
                    val = 9;
                }
                deck.add(new Card(s, ranks[j], val + 1, cardNum));
                cardNum++;
            }
        }
    }

    // Returns true if every card has been dealt
    public boolean isEmpty() {
        return cardsLeft <= 0;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }
    // Deals the car at cardLeft and subtracts cardsLeft by one
    public Card deal() {
        if (isEmpty())
            return null;
        cardsLeft--;
        return deck.get(cardsLeft);
    }

    // Shuffles the deck and resets cardsLeft to the number of cards
    public void shuffle() {
        for (int i = 0; i < deck.size(); i++) {
            int index = (int) (Math.random() * deck.size());
            deck.set(i, deck.set(index, deck.get(i)));
        }
        cardsLeft = deck.size();
    }
}
