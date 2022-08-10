import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForce {
    private static Scanner scanner = new Scanner(System.in);

    public static void bruteforce() {
        System.out.println("Enter name of the file, you want to decode");
        String fileName = scanner.nextLine();
        Path filePath = Path.of(fileName).toAbsolutePath();
        String initialString = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            while (bufferedReader.ready()) {
                initialString = bufferedReader.readLine();
            }
            char[] symbols = initialString.toCharArray();
            for (int key = 1; key < 27; key++) {
                for (int i = 0; i < symbols.length; i++) {
                    if (symbols[i] == ' ')
                        continue;
                    else {
                        if (symbols[i] >= 'А' && symbols[i] <= 'Я') {
                            symbols[i] = (char) (symbols[i] - key);
                            if (symbols[i] < 'А') {
                                symbols[i] = (char) (symbols[i] + 33);
                            }
                        } else {
                            symbols[i] = (char) (symbols[i] - key);
                            if (symbols[i] < 'a') {
                                symbols[i] = (char) (symbols[i] + 33);
                            }
                        }
                    }
                }
                System.out.println("Key = " + key + " Decrypted String : " + String.valueOf(symbols));
                symbols = initialString.toCharArray();
            }

        } catch (IOException e) {
            System.out.println("Wrong path:" + filePath.toAbsolutePath());
            e.getMessage();
            bruteforce();
        }

    }
}
