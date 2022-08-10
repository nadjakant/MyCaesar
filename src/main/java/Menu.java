import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("Please choose one of the options:\n 0- stop the session\n 1- encode the file\n 2- decode the file\n 3- brute force decoding");
        int option = scanner.nextInt();

        switch (option) {
            case 0:
                System.out.println("Well, till next time then");
                System.exit(0);
                break;
            case 1:
                Encoder.encodeFile();
                break;
            case 2:
                Decoder.decodeFile();
                break;
            case 3:
                BruteForce.bruteforce();
                break;
            default:
                System.out.println("there is no such an option, sorry");
        }
    }
}
