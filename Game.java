import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Board board = new Board();
    private int turn;
    private List<Player> players = new ArrayList<>();

    public void PlayGame(){
        //Pre: Game has already been initialized
        //Post: Starts and finishes a complete Game.
        Scanner sc = new Scanner(System.in);
        deck = new Deck();
        deck.shuffle();
        deck.cut();
        gameStart(sc);
        board.dealCards(deck, players);
        Player p = fiveGold();
        if (p == null)
            System.out.println("Nobody has 5 of gold, so Player 1 (" + players.get(0).getName() + ") will start");
        else {
            turn = players.indexOf(p);
            System.out.println("Player " + p.getName() + " has 5 of gold, so will start the game");
        }
        while (!gameEnd()){
            this.play();
        }
        ranking();
        sc.close();
    }

    public void gameStart(Scanner sc) {
        //Pre: sc!=null
        //Post: Adds the number of Players chosen into the game.
        int n;
        while (true) {
            System.out.println("Choose number of total players (1-4):");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1 && n <= 4) break;
            }
            sc.nextLine(); //Clean buffer
            System.out.println("Invalid number. Try again.");
        }
        int n1;
        while (true) {
            System.out.println("Choose number of human players (1-" + n + "):");
            if (sc.hasNextInt()) {
                n1 = sc.nextInt();
                if (n1 >= 1 && n1 <= n) break;
            }
            sc.nextLine(); //Clean buffer
            System.out.println("Invalid number of human players. Try again.");
        }
        sc.nextLine(); //Clean buffer
        players.clear();
        for (int i = 0; i < n1; i++) {
            System.out.println("Enter name for player " + (i + 1) + ":");
            String playerName = sc.nextLine().trim();
            if (playerName.isEmpty()) {
                playerName = "Player " + (i + 1);
            }
            players.add(new Human(playerName));
        }
        for (int i = 0; i < (n - n1); i++) {
            players.add(new CPU("CPU " + (i + 1)));
        }
        System.out.println("Players:");
        for (Player p : players) {
            System.out.println(p.getName());
        }
    }

    public boolean gameEnd() {
        //Pre:
        //Post: If a players has no cards return true, else false
        for (Player p : players) {
            if (p.finalized()) return true;
        }
        return false;

    }

    public void play() {
        //Pre:
        //Pos: Makes a full turn of a player of a game
        Player p = players.get(turn);
        board.showBoard();
        if (!p.canPlay(board)) {
            System.out.println("Player " + p.getName() + " can't play and must draw a card from the deck");
            if (deck.isEmpty()) System.out.println("...but there is not cards left on the deck");
            else {
                p.getCard(deck.pickCard());
                System.out.println(p.getName() +" has drawn a card");
            }
        } else {
            Card c = p.play(board);
            System.out.println("Player "+p.getName()+" has put card "+c.toString());
            board.putCard(c);
        }
        turn++;
        if(turn==players.size()) turn=0;
    }

    public Player fiveGold() {
        //Pre: Game started.
        //Pos: Returns player who has five of gold, or null if nobody has it.
        for (Player p : players) {
            if (p.hasfiveGold()) return p;
        }
        return null;
    }
    public void ranking(){
        //Pre: Game has to be finished
        //Pos: Shows the ranking of players in order descending by the amount of cards they have

        players.sort(Comparator.comparing(Player::numCards));
        int i = 1;
        System.out.println("Ranking:");
        for(Player p : players){
            System.out.println(i + ". "  + p.getName()+ " ("+ p.numCards()+" Cards)");
            i++;
            }
        }
    }




