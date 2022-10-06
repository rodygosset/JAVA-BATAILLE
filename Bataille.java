import java.util.ArrayList;
import java.util.Arrays;

public class Bataille {

    // utility functions

    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<T>(Arrays.asList(array));
    }

    public static int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    

    // create a deck of 52 cards &&
    // generate these cards in random order

    public static Carte[] shuffleDeck() {
        
        // generate an unshuffled deck

        ArrayList<Carte> unshuffledDeck = new ArrayList<Carte>();

        for(int couleurId = 0; couleurId < Carte.COULEURS.length; couleurId++) {
            for(int valeurId = 0; valeurId < Carte.VALEURS.length; valeurId++) {
                unshuffledDeck.add(new Carte(couleurId, valeurId));
            }
        }

        // shuffle it -->
        // generate a random index (between 0 and unshuffledDeck.length)
        // move the object at that index from the unshuffled deck to the shuffled one


        ArrayList<Carte> shuffledDeck = new ArrayList<Carte>();

        int index = 0;

        while(!unshuffledDeck.isEmpty()) {
            index = getRandomInt(0, unshuffledDeck.size());
            shuffledDeck.add(unshuffledDeck.remove(index));
        }

        // return the shuffled deck

        return shuffledDeck.toArray(new Carte[0]);
    }

    public static int handleBataille(Joueur p1, Joueur p2) {
        System.out.println("Bataille!");
        Carte[] p1Cards = new Carte[2];
        Carte[] p2Cards = new Carte[2];
        int comparison = 0;
        int winner = 0;
        // compare not the next cards, but those after
        for(int i = 0; i < 2; i++) {
            p1Cards[i] = p1.tirerCarte();
            p2Cards[i] = p1.tirerCarte();
            // in case one of the player's got no cards left
            if(p1Cards[i] == null) {
                // player 2 won
                winner = 2;
            } else if(p2Cards[i] == null) {
                // player 1 won
                winner = 1;
            }
            if(winner != 0) { break; }
        }
        if(winner == 0) {
            // both players still have cards, so compare them
            comparison = p1Cards[1].compareTo(p2Cards[1]);
            if(comparison == -1) {
                // player 2 won this round
                winner = 2;
            } else if(comparison == 0) {
                // equality
                winner = handleBataille(p1, p2);
            } else {
                winner = 1;
            }
        }
        // give the winner all the cards that were drawn
        if(winner == 1) {
            for(int i = 0; i < 2; i++) {
                p1.ajouterCarte(p1Cards[i]);
                p1.ajouterCarte(p2Cards[i]);
            }
        } else {
            for(int i = 0; i < 2; i++) {
                p2.ajouterCarte(p2Cards[i]);
                p2.ajouterCarte(p1Cards[i]);
            }
        }
        System.out.println("Player One's card --> " + p1Cards[1].toString());

        System.out.println("Player Two's card --> " + p2Cards[1].toString());
        return winner;
    }

    // game entry point

    public static void main(String[] args) {

        System.out.println("Initalizing game data...");

        // instatiate 2 players
        Joueur playerOne = new Joueur();
        Joueur playerTwo = new Joueur();

        // create a deck of cards and shuffle it

        ArrayList<Carte> mainDeck = arrayToArrayList(shuffleDeck());

        // System.out.println(mainDeck.toString());

        // distribute the deck

        for(int count = 0; count < mainDeck.size(); count++) {
            // alternate between player one and player two
            if(count % 2 == 0) {
                playerOne.ajouterCarte(mainDeck.remove(count));
            } else {
                playerTwo.ajouterCarte(mainDeck.remove(count));
            }
        }

        // start the game & loop until victory


        boolean over = false;

        Carte playerOneCard = null;
        Carte playerTwoCard = null;

        int comparison = 0;

        int winner = 0; // 1 == Player One, 2 == Player Two

        while(!over) {
            
            // let player one draw his card

            playerOneCard = playerOne.tirerCarte();
            System.out.println("Player One's card --> " + playerOneCard.toString());

            // let player two draw his card

            playerTwoCard = playerTwo.tirerCarte();
            System.out.println("Player Two's card --> " + playerTwoCard.toString());

            // compare the two cards

            comparison = playerOneCard.compareTo(playerTwoCard);


            // in case player Two won this round
            if(comparison == -1) {
                playerTwo.ajouterCarte(playerTwoCard);
                playerTwo.ajouterCarte(playerOneCard);
                System.out.println("-- Player Two won this round");
            } else if(comparison == 0) {
                // in case there's an equality
                int roundWinner = handleBataille(playerOne, playerTwo);
                if(roundWinner == 1) {
                    // in case player One won this round
                    playerOne.ajouterCarte(playerOneCard);
                    playerOne.ajouterCarte(playerTwoCard);
                    System.out.println("-- Player One won this round");
                } else {
                    // in case player Two won this round
                    playerTwo.ajouterCarte(playerTwoCard);
                    playerTwo.ajouterCarte(playerOneCard);
                    System.out.println("-- Player Two won this round");
                }
            } else {
                // in case player One won this round
                playerOne.ajouterCarte(playerOneCard);
                playerOne.ajouterCarte(playerTwoCard);
                System.out.println(" -- Player One won this round");
            }

            playerOneCard = playerOne.tirerCarte();
            playerTwoCard = playerTwo.tirerCarte();


            // in case Player One lost the game
            if(playerOneCard == null) {
                winner = 2;
                over = true;
            } else if(playerTwoCard == null) {
                playerOne.ajouterCarte(playerOneCard);
                // in case Player Two lost the game
                winner = 1;
                over = true;
            } else {
                System.out.println("-- Next Round --");
                // put the cards back in each player's deck
                playerOne.ajouterCarte(playerOneCard);
                playerTwo.ajouterCarte(playerTwoCard);
            }

            playerOneCard = null;
            playerTwoCard = null;
        }

        System.out.println("Winner --> player " + (winner == 1 ? "One" : "Two"));

        return;
    }
}
