import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    public Deck(){
        //Pre:
        //Post: Creates a deck with 40 cards.
        cards=new ArrayList<Card>();
        for(int i=1;i<8;i++){
            cards.add(new Card(i,Suit.gold));
            cards.add(new Card(i,Suit.sword));
            cards.add(new Card(i,Suit.cup));
            cards.add(new Card(i,Suit.wand));
        }
        for(int i=10;i<13;i++){
            cards.add(new Card(i,Suit.gold));
            cards.add(new Card(i,Suit.sword));
            cards.add(new Card(i,Suit.cup));
            cards.add(new Card(i,Suit.wand));
        }
    }

    public void shuffle (){
        //Pre: deck previously initialized.
        //Post: shuffle the cards randomly.
        Random r = new Random();
        for(int l=0;l<40;l++){
            int i=r.nextInt(40);
            Card c=cards.get(i);
            Card c2=this.cards.get(l);
            cards.set(l,c);
            cards.set(i,c2);
        }
    }

    public void cut (){
        //Pre: deck previously initialized.
        //Post: cuts the deck in a random position.
        Random r = new Random();
        int l = r.nextInt(cards.size());
        List<Card> top = new ArrayList<>(cards.subList(0, l));
        List<Card> bottom = new ArrayList<>(cards.subList(l, cards.size()));

        cards.clear();
        cards.addAll(bottom);
        cards.addAll(top);
    }

    public Card pickCard (){
        //Pre: deck is previously initialized with at least one card.
        //Post: Returns first card of the deck, which is removed.
        Card c=this.cards.get(0);
        cards.remove(c);
        return c;
    }

    public boolean isEmpty(){
        //Pre: d is a deck previously initialized
        //Pos: Returns true if d is empty, false in other case
        return this.cards.isEmpty();
    }
}
