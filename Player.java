import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private String name;
    protected List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public boolean canPlay(Board b) {
        //Pre: b!= null
        //Post: Returns true if the player is able to put a card in b, else false.
        int i = 0;
        while (i < cards.size()) {
            if (b.isAble(cards.get(i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    public int numCards() {
        //Pre:
        //Post: Returns the number of cards of the player.
        return this.cards.size();
    }

    public boolean finalized() {
        //Pre:
        //Post: Returns true if the player has no cards left, else false.
        return this.cards.isEmpty();
    }

    public void showCards() {
        //Pre:
        //Post: It shows the cards from player
        System.out.println("-----------------------------------------------------");
        System.out.println("Cards from " + name + ":");
        String stringcards = "";
        for (int i = 0; i < cards.size(); i++) {
            stringcards += " (" + (i + 1) + ")" + cards.get(i).toString();
        }
        System.out.println(stringcards);
        System.out.println("-----------------------------------------------------");
    }

    public void getCard(Card c) {
        //Pre: Player has to be initialized
        //Post: Player receives card c
        cards.add(c);
    }

    public String getName() {
        return name;
    }

    public boolean hasfiveGold() {
        //Pre:
        //Post: Returns true if player has 5 of gold card in his deck, false in other case
        for (Card c : cards) {
            if (c.getSuit().equals(Suit.gold) && c.getNumber() == 5) return true;
        }
        return false;
    }

    public abstract Card play(Board b);
}
