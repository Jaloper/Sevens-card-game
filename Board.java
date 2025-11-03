import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;

public class Board {
    private HashMap<Suit,Deque<Card>> row=new HashMap<>();
    public void dealCards (Deck d, List<Player> players){
        //Pre: Board is != null
        //Pos: It deals 10 cards for each of the players
        for (int i=0;i<10;i++){
             for(Player p:players){
                 p.getCard(d.pickCard());
             }
        }
    }
    public void showBoard(){
        //Pre: Board is != null
        //Pos: It shows the cards that are placed on the board.
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for(Suit s:row.keySet()){
           Deque<Card> deque=row.get(s);
            List<Card> sortedCards = new ArrayList<>(deque);
            sortedCards.sort(Comparator.comparingInt(Card::getNumber));
            String totalrow=" + ";
           for(Card c:sortedCards){
               totalrow+=c.toString()+" + ";
           }
            System.out.println(totalrow);
           System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
    public void putCard(Card c){
        //Pre: Board is != null, and the card c is able to be added on the board
        //Pos: It adds card c to its position on the board
        if(c.getNumber()==5){
            Deque<Card> deque= new ArrayDeque<>();
            deque.add(c);
            row.put(c.getSuit(),deque);
        }
        else{
            Deque<Card> deque=this.row.get(c.getSuit());
           if(isFirst(deque,c)) deque.addFirst(c);
           else if(isLast(deque,c)) deque.addLast(c);
        }
    }
    public boolean isAble(Card c){
        //Pre: Board is != null
        //Pos: Returns true if the card c can be put on the board, else false
        if(c.getNumber()==5) return true;
        Deque<Card> deque=this.row.get(c.getSuit());
        if (isFirst(deque,c) || isLast(deque,c)) return true;
        return false;
    }
    private boolean isFirst(Deque<Card> deque, Card c){
        //Pre: Board is != null
        //Pos: The card could be added at the first position in the Deque.
        if(deque==null) return false;
        return (c.getNumber()-deque.getFirst().getNumber()==1);
    }
    private boolean isLast(Deque<Card> deque, Card c){
        //Pre: Board is != null
        //Pos: The card could be added at the last position in the Deque.
        if(deque==null) return false;
        return (deque.getLast().getNumber()-c.getNumber()==1);
    }
}
