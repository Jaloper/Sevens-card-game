import java.util.ArrayList;
import java.util.List;

public class CPU extends Player {
    public CPU(String name) {
        super(name);
    }
    public Card play(Board b) {
        //Pre: CPU!=null
        //Pos: Returns the smartest Card to play for the CPU
        List<Card> cardsAble = new ArrayList<Card>();
        for (Card card : cards) {
            if(b.isAble(card)) cardsAble.add(card);
        }
        List<Card> cardsSuited = new ArrayList<Card>();
        Suit s=maxCardsfromSuit(cardsAble);
        for (Card card : cardsAble) {
            if(card.getSuit().equals(s)) cardsSuited.add(card);
        }
        Card c=farthestCard(cardsSuited);
        cards.remove(c);
        return c;
    }

    private int numCardsSuit(Suit s,List<Card> cardsAble){
        //Pre: Receives 1 of the 4 suits
        //Pos: Returns the number of cards of that suit in the deck

        int i=0;
        for (Card card : cardsAble) {
            if (card.getSuit() == s) {
                i++;
            }
        }
        return i;
    }
    private Suit maxCardsfromSuit(List<Card> cardsAble){
        //Pre:
        //Pos: Returns the suit with the most amount of cards

        int i=0;
        Suit suit=null;
        for(Suit s:Suit.values()){
            if(numCardsSuit(s,cardsAble)>i){
                suit=s;
                i=numCardsSuit(s,cardsAble);
            }
        }
        return suit;
    }
    private Card farthestCard(List<Card> card){
        //Pre: Receives list of cards which is not empty
        //Pos: Returns the farthest card from number 5 of the list received
        Card card1=card.get(0);
        for(Card c:card){
            if(Math.abs(c.getNumber()-5)>Math.abs(card1.getNumber()-5)){
                card1=c;
            }
        }
        return card1;
    }
}
