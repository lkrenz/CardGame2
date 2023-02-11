// Creates the front end for the game

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameView extends JFrame {
    private final Image background;
    private final Player  player,
                    dealer;
    private final Image[] cardImages;
    private boolean printInstructions;
    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600;
    private String winner;

    // Instantiates the front end, and assigns instance variables
    public GameView(Player player, Player dealer){
        this.player = player;
        this.dealer = dealer;
        this.winner = null;
        this.background = new ImageIcon("Resources/Background.png").getImage();
        printInstructions = true;
        this.cardImages = new Image[53];
        // Pulls the card images from memory to fill the array
        for (int i = 0; i < 52; i++)
        {
            cardImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();
        }
        cardImages[52] = new ImageIcon("Resources/back.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Draws the UI
    public void paint(Graphics g) {
        // Shows the instructions when first called
        if (printInstructions) {
            g.drawImage(new ImageIcon("Resources/instructions.png").getImage(), -5, 11, WINDOW_WIDTH + 10, WINDOW_HEIGHT, this);
            this.printInstructions = false;
        }
        else if (winner != null) {
            // If the game has a winner, it displays the corresponding image
            if (winner.equals("Dealer"))
                g.drawImage(new ImageIcon("Resources/Player lose.png").getImage(), -5, 11, WINDOW_WIDTH + 10, WINDOW_HEIGHT, this);
            else
                g.drawImage(new ImageIcon("Resources/Player win.png").getImage(), -5, 11, WINDOW_WIDTH + 10, WINDOW_HEIGHT, this);
            drawScore(g, 350, 385, 50);
        }
        else {
            // Draws the normal background along with player and dealer hands, the score, and the players card total
            g.drawImage(background, -5, 11, WINDOW_WIDTH + 10, WINDOW_HEIGHT, this);
            drawCards(g);
            drawScore(g, 700, 270, 57);
            drawCardTotal(g);
        }
    }

    // Writes the total of the player's hand
    public void drawCardTotal(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString(player.getTotal() + "", 700, 522);
    }

    // Displays the rounds won by each player given certain x and y coordinates
    public void drawScore(Graphics g, int x, int y, int separation) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString(player.getPoints() + "", x, y);
        g.drawString(dealer.getPoints() + "", x, y + separation);
    }

    // Calls both player's draw card methods
    public void drawCards(Graphics g) {
        drawDealerCards(g);
        drawPlayerCards(g);
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    // Draws the player's hand in the lower part of the screen
    public void drawPlayerCards(Graphics g) {
        ArrayList<Card> hand = player.getHand();
        int initialX = (WINDOW_WIDTH / 2) - (hand.size() * 25);
        for (int i = 0; i < hand.size(); i++) {
            g.drawImage(cardImages[hand.get(i).getCardNum()], initialX + 25 * i, 450, 75, 112, this);
        }
    }

    // Draws the dealer's first card face up and the rest face down at the top of the screen
    public void drawDealerCards(Graphics g) {
        ArrayList<Card> hand = dealer.getHand();
        int initialX = (WINDOW_WIDTH / 2) - (hand.size() * 25);
        g.drawImage(cardImages[hand.get(0).getCardNum()], initialX, 150, 75, 112, this);
        for (int i = 1; i < hand.size(); i++) {
            g.drawImage(cardImages[52], initialX + 25 * i, 150, 75,112, this);
        }
    }
}
