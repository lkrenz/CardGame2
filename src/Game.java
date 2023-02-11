/**
 * Liam Krenz 2/10/2023
 */


import java.util.Scanner;
public class Game {

    private final Player  player,
                          dealer;
    private final Deck deck;
    private final GameView window;

    // Constructs a new game without parameters
    public Game(){
        this.player = new Player();
        this.dealer = new Player();
        this.window = new GameView(player, dealer);
        this.deck = new Deck();
    }

    // Runs through rounds of blackjack until either the dealer or player has won 5 rounds
    public void run() {
        // Waits for the user to press enter to close the instructions screen
        Scanner s = new Scanner(System.in);
        s.nextLine();
        while (player.getPoints() < 5 && dealer.getPoints() < 5) {
            playRound();
        }
        // Once the player has won or lost, the window displays a certain screen
        if (player.getPoints() > dealer.getPoints())
            window.setWinner("Player");
        else
            window.setWinner("Dealer");
        window.repaint();
    }

    // Keeps making moves until either the player or dealer is out
    public void playRound() {
        reset();
        initialDeal();
        window.repaint();
        while (player.getTotal() < 21 && dealer.getTotal() < 21 && !player.isOut()) {
            makeMoves();
        }
        if (player.getTotal() > 21) {
            dealer.addPoints(1);
        }
        else if (dealer.getTotal() > 21) {
            player.addPoints(1);
        }
        else if (player.getTotal() > dealer.getTotal()) {
            player.addPoints(1);
        }
        else if (dealer.getTotal() > player.getTotal()) {
            dealer.addPoints(1);
        }
    }

    // Makes the moves for the player and dealer
    public void makeMoves() {
        makePlayerMove();
        makeDealerMove();
    }

    // Asks the player to hit or stay and then carries out the selected action
    public void makePlayerMove() {
        Scanner s = new Scanner(System.in);
        window.repaint();
        System.out.println("Press enter to begin your turn");
        System.out.println("Enter 1 to hit or 2 to stay");
        if (s.nextInt() == 1) {
            Card card = deck.deal();
            player.addCard(card);
            window.repaint();
        }
        else {
            // If the player stays, the round ends
            player.setOut(true);
        }
    }

    // Adds a card to the dealer's hand if their total is under 17
    public void makeDealerMove() {
        if (dealer.getTotal() < 17)
            dealer.addCard(deck.deal());
    }

    // Resets both players and shuffles the deck
    public void reset() {
        player.reset();
        dealer.reset();
        deck.shuffle();
    }

    // Adds two cards to each player's hand at the start of a round
    public void initialDeal() {
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}