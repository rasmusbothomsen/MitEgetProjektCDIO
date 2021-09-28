import java.util.Scanner;

public class Classtester {
    private static boolean endOfGame;
    private static boolean is40;
    private static boolean player1Won;
    private static boolean player2Won;

    public static void main(String[] args) {
        Dice dice = new Dice();
        Scanner scan = new Scanner(System.in);
        System.out.println("Indtast spiller 1's navn");
        String player1Name = scan.nextLine();
        System.out.println("Indtast spiller 2's navn");
        String player2Name = scan.nextLine();
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        endOfGame = false;
        is40 = false;


        while (!endOfGame) {
            if (player1.getPoint() < 40) {
                playTurn(player1, dice, scan);
            } else if (turnOver40(player1, dice)) {
                player1Won = true;
                endOfGame = true;
            }

            if (player2.getPoint() < 40) {
                playTurn(player2, dice, scan);
            } else if (turnOver40(player2, dice)) {
                player2Won = true;
                endOfGame = true;
            }
        }
        if (player1Won) {
            System.out.println("Tillykke " + player1.getName() + " Du har vundet");
        } else if (player2Won) System.out.println("Tillykke " + player2.getName() + " Du har vundet");

        System.out.println("Spil igen? y/n");
        boolean playAgain = scan.nextLine().charAt(0) == 'y';
        if (playAgain) {
            main(null);
        } else System.out.println("Tak for spillet");


    }

    public static void playTurn(Player a, Dice dice, Scanner scan) {
        int[] slag = dice.getDice();
        int[] lastThrow = {0, 0};
        boolean isPlayerTurn = true;
        while (isPlayerTurn) {
            System.out.println("Det er " + a.getName() + " tur" + "\n" + "Tryk enter for at kaste");
            pressAnyKeyToContinue();
            slag = dice.getDice();
            System.out.println("Du slog " + slag[0] + " og " + slag[1]);
            if (isSame(slag) && slag[0] == 1) {
                System.out.println("Ærgeligt du slog 2 1'ere, du mister alle dine point");
                a.setPoint(0);
            } else if (isSame(slag)) {
                System.out.println("Tillykke 2 ens, du får en ekstra tur");
                a.addPoint(slag[0] + slag[1]);
                lastThrow = slag;
            } else if (isSame(slag) && slag[0] == 6 && lastThrow[0] == 6 && lastThrow[1] == 6) {
                endOfGame = true;
            } else {
                a.addPoint(slag[0] + slag[1]);
                isPlayerTurn = false;
            }
            System.out.println("Spiller " + a.getName() + " har " + a.getPoint() + " points" + "\n");
            is40(a);
        }
    }

    private static boolean turnOver40(Player a, Dice dice) {
        int[] slag = dice.getDice();
        System.out.println("Det er spiller " + a.getName() + "s tur" + "\n" + "Slå 2 ens for at vinde, tryk enter for at slå");
        pressAnyKeyToContinue();
        System.out.println("Du slog " + slag[0] + " og " + slag[1]);
        if (slag[0] == slag[1] && slag[0] != 1) {
            return true;
        } else if (slag[0] == slag[1] && slag[0] == 1) {
            a.setPoint(0);
            return false;
        } else return false;


    }

    private static void pressAnyKeyToContinue() {
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static boolean isSame(int[] a) {
        if (a[0] == a[1]) {
            return true;

        } else return false;
    }

    public static void is40(Player a) {
        if (a.getPoint() < 40) {
            is40 = false;
        } else {
            is40 = true;
        }
    }
}
