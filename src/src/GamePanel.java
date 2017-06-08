
package src;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The GamePanel Class
 * @author: MAbdurrahman
 * @date:  8 June 2017
 * @version:  1.0.0
 */
public class GamePanel extends JPanel {
    //Instance Variable
    private final CardPanel cardPanel;
    private final JButton higherButton;
    private final JButton lowerButton;
    private final JButton newGameButton;
    
    /**
     * GamePanel Constructor - Creates an instance of the GamePanel. The GamePanel lays out
     * the JPanel.  That is, a CardPanel occupies the center position (CardPanel is a sub-
     * class of the JPanel).  On the bottom is a JPanel that holds the three buttons.  Also,
     * the CardPanel listens for the ActionEvents from the buttons and does the real work
     * for the program.
     * @param Void
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GamePanel() {
        setBackground(new Color(156, 95, 42));
        setLayout(new BorderLayout(3, 3));
        
        cardPanel = new CardPanel();
        add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(156, 95, 42));
        add(buttonPanel, BorderLayout.SOUTH);

        higherButton = new JButton("  Higher  ");
        higherButton.setBackground(Color.red);
        higherButton.setSize(60, 25);
        higherButton.setFont(new Font("Teen Light", Font.BOLD, 18));
        higherButton.addActionListener(cardPanel);
        buttonPanel.add(higherButton);

        lowerButton = new JButton("  Lower  ");
        lowerButton.setBackground(Color.yellow);
        lowerButton.setSize(60, 25);
        lowerButton.setFont(new Font("Teen Light", Font.BOLD, 18));
        lowerButton.addActionListener(cardPanel);
        buttonPanel.add(lowerButton);

        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.green);
        newGameButton.setSize(50, 25);
        newGameButton.setFont(new Font("Teen Light", Font.BOLD, 18));
        newGameButton.addActionListener(cardPanel);
        buttonPanel.add(newGameButton);

       setBorder(BorderFactory.createStrokeBorder(new BasicStroke(12), Color.decode("#9C5F2A")));

    }//end of GamePanel Constructor
    /**
     * The CardPanel Class is a nested class, displays the cards, maintains the status of the
     * game, and responds to the ActionEvents.
     */
    private class CardPanel extends JPanel implements ActionListener {
        /** Instance Variables */
        Deck deck;//The deck of cards for the game
        Hand hand;
        String message;//The displayed message displayed, which states the status of the game		
        Font bigFont;//The font used to display the message
        Image cardImages;//The image of all cards of cardDeck.png
        boolean gameInProgress;//Monitors the status of the game
        
        /**
         * CardPanel Constructor - Creates an instance of the CardPanel, loads the images, sets 
         * the foreground and background colors, creates the fonts, and starts the first game.  Also,
         * it sets the "preferred size" for the CardPanel.
         */
        @SuppressWarnings("OverridableMethodCallInConstructor")
        private CardPanel() {
            loadImage();
            setBackground(new Color(65, 110, 12));
            setForeground(new Color(237, 237, 69));
            bigFont = new Font("Teen Light", Font.BOLD, 28);
            doNewGame();
            
        }//end of CardPanel Constructor
        /**
         * loadImage Method - Loads the image from the file "cardDeck.png". If the file is
         * found, then the cardImages will refer to the image. If not, cardImage will be null
         * @param Void
         */
        private void loadImage() {
           ClassLoader classLoader = this.getClass().getClassLoader();
           URL imageURL = classLoader.getResource("\\img\\cardDeck.png");
           if (imageURL != null) {
               cardImages = Toolkit.getDefaultToolkit().createImage(imageURL);
           }
           
        }//end of loadImage Method
        /**
         * doHigher Method - The actionPerformed Method calls this method when
         * the user clicks "Higher" button. The user's prediction is checked.
         * The game ends, if the user's prediction is wrong or if the user has
         * made three correction pre- dictions.
         * @param Void
         */
        void doHigher() {
            if (gameInProgress == false) {
                /*If the game has ended, it was an error to click "Higher".  Therefore, set
			  up an error message and abort processing.*/
                message = "Click \"New Game\" to start a new game!";
                Toolkit.getDefaultToolkit().beep();
                repaint();
                return;
            }
            hand.addCard(deck.deal());//Deal a card to the hand
            int cardCount = hand.getCardCount();
            Card thisCard = hand.getCard(cardCount - 1);//the card just dealt
            Card prevCard = hand.getCard(cardCount - 2);//the previous card
            if (thisCard.getRank() < prevCard.getRank()) {
                gameInProgress = false;
                message = "Too bad!  You lose.";
            } else if (thisCard.getRank() == prevCard.getRank()) {
                gameInProgress = false;
                message = "Too bad!  You lose on ties.";
            } else if (cardCount == 4) {
                gameInProgress = false;
                message = "You win!  You made three correct guesses.";
            } else {
                message = "You got it right!  Try for " + cardCount + ".";
            }
            repaint();

        }//end of the doHigher Method
        /**
         * doLower Method - The actionPerformed Method calls this method when
         * the user clicks "Lower" button. The user's prediction is checked. The
         * game ends, if the user's prediction is wrong or if the user has made
         * three correction pre- dictions.
         * @param Void
         */
        void doLower() {
            if (gameInProgress == false) {
                /*If the game has ended, it was an error to click "Lower".  Therefore, set
			  up an error message and abort processing.*/
                message = "Click \"New Game\" to start a new game!";
                Toolkit.getDefaultToolkit().beep();
                repaint();
                return;
            }
            hand.addCard(deck.deal());//Deal a card to the hand
            int cardCount = hand.getCardCount();
            Card thisCard = hand.getCard(cardCount - 1);//the card just dealt
            Card prevCard = hand.getCard(cardCount - 2);//the previous card
            if (thisCard.getRank() > prevCard.getRank()) {
                gameInProgress = false;
                message = "Too bad!  You lose.";
            } else if (thisCard.getRank() == prevCard.getRank()) {
                gameInProgress = false;
                message = "Too bad!  You lose on ties.";
            } else if (cardCount == 4) {
                gameInProgress = false;
                message = "You win!  You made three correct guesses.";
            } else {
                message = "You got it right!  Try for " + cardCount + ".";
            }
            repaint();

        }//end of doLower Method
        /**
         * doNewGame Method - The nested class CardPanel Constructor and the
         * actionPerformed Method calls this method, if the user clicks the "New
         * Game" button to start a new game.
         * @param Void
         */
        void doNewGame() {
            if (gameInProgress) {
                /*If the game is ongoing, it is an error to try to start a new game.*/
                message = "The game is ongoing!  You have to finish this game.";
                Toolkit.getDefaultToolkit().beep();
                repaint();
                return;
            }
            deck = new Deck();//Create a deck and hand this game
            hand = new Hand();
            deck.shuffle();
            hand.addCard(deck.deal());//Deal the first card
            message = "Is the next card higher or lower?";
            gameInProgress = true;
            repaint();

        }//end of doNewGame Method
        /**
         * paintComponent Method - Draws the message at the bottom of the panel,
         * and it draws all the dealt cards on the canvas. If the game is in
         * progress, an extra card is drawn face down representing the card to
         * be dealt next.
         * @param Void
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (cardImages == null) {
                g.drawString("Error:  Can't get card images!", 15, 30);
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            g.setFont(bigFont);
            g.drawString(message, 15, 325);
            int cardCount = hand.getCardCount();
            for (int i = 0; i < cardCount; i++) {
                drawCard(g, hand.getCard(i), 15 + i * (15 + 140), 15);
                if (gameInProgress) {
                    drawCard(g, null, 20 + cardCount * (15 + 140), 15);
                }
            }
        }//end of paintComponent Method
        /**
         * actionPerformed Method - Defines the actionPerformed method of the ActionListener
         * Interface, and responds when the user clicks a button by calling the appropriate
         * method. The buttons are created and listening is set up in the constructor of the
         * GamePanel.
         * @param ActionEvent - the event of clicking on the buttons
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            switch (command) {
                case "  Higher  ":
                    doHigher();
                    break;
                case "  Lower  ":
                    doLower();
                    break;
                case "New Game":
                    doNewGame();
                    break;
                default:
                    break;
            }
        }// end actionPerformed Method
        /**
         * drawCard Method - Draw a card in a rectangle with its upper left corner
         * at a specified point (X, Y). Drawing the card requires the image file
         * "cardDeck.png".
         * @param Graphics - The graphics context
         * @param Card - The card to be drawn. If the value is null, then a face-down card
         * is drawn.
         * @param Int - The X coordinate of the upper left corner of the card.
         * @param Int - The Y coordinate of the upper left corner of the card.
         */
        public void drawCard(Graphics g, Card card, int x, int y) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                                    RenderingHints.VALUE_RENDER_QUALITY);
            int coordX;//X coordinate of the upper left corner of the card inside cardsImage
            int coordY;//Y coordinate of the upper left corner of the card inside cardsImage

            if (card == null) {
                /** Coordinates for the face down card */
                coordX = 2 * 74;
                coordY = 4 * 115;
            } else {
                coordX = (card.getRank() - 1) * 74;
                switch (card.getSuit()) {
                    /** The row of Clubs in the cardsImage */
                    case 0:
                        coordY = 0;
                        break;
                    /** The row of Diamonds in the cardsImage */
                    case 1:
                        coordY = 115;
                        break;
                    /** The row of Hearts in the cardsImage */
                    case 2:
                        coordY = 2 * 115;
                        break;
                    /** The row of Spades in the cardsImage */
                    default:
                        coordY = 3 * 115;
                        break;
                        
                }//end of switch condition	
            }
            g2d.drawImage(cardImages, x, y, (x + 140), (y + 215), coordX, coordY, coordX + 73, coordY + 115, (ImageObserver) this);
            
        }//end of drawCard Method   
    }//end of nested CardPanel Class
    /**
     * main Method - Contains the command line arguments.  In this application it opens a window 
     * that displays a High Low Game.
     * @param String[] - the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("High Low Game");
        /** The following line of code creates a new ImageIcon for the JFrame */
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/img/HiLo.png")));
        GamePanel gamePanel = new GamePanel();
        window.setContentPane(gamePanel);
        window.setSize(800, 450);
        window.setResizable(false);
        window.setLocation(300, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }//end of main Method
}//end of class HighLowWithImages
