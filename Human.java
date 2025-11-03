import java.util.Scanner;

public class Human extends Player {
    public Human(String name) {
        super(name);
    }

    @Override
    public Card play(Board b) {
        //Pre:
        //Pos: Returns the card the player wants to put only if that is possible
        Scanner sc = new Scanner(System.in);
        Card c;
        while (true) {
            System.out.println("Choose the card you would like to play (by index): ");
            this.showCards();
            if (sc.hasNextInt()) {
                int n = sc.nextInt() - 1;
                if (n >= 0 && n < cards.size()) {
                    if (b.isAble(cards.get(n))) {
                        c=cards.get(n);
                        break;
                    }
                    else System.out.println("Card is not able to be put on the Board");
                } else System.out.println("Invalid number. It doesn´t exists a card with that index.");
            }
            sc.nextLine();//Clean buffer
        }
        cards.remove(c);
        return c;
    }
}
