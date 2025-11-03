public class Card {
    private int number;
    private Suit suit;
    public Card(int number, Suit suit) {
        // Pre: 0<number<8 AND 9<number<13 ; suit= (sword|cup|gold|wand)
        // Post: Returns the card which represents the data given.
        this.number = number;
        this.suit = suit;
    }
    public String toString(){
        //Pre:
        //Post: Shows the info of the card.
        return "[" + number + " " + suit + "]";
    }

    public int getNumber() {
        //Pre:
        //Post: Returns the value of the card; if number>=10 returns number-2
        if((number-10)>=0) return number-2;
        return number;
    }

    public Suit getSuit() {
        return suit;
    }
}
