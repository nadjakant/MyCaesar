import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class Encoder {
    private static Scanner scanner = new Scanner(System.in);

    public static void encodeFile() {
        System.out.println("Enter name of the file, you want to encode");
        String fileName = scanner.nextLine();
        Path filePath = Path.of(fileName);
        String encodedFile = "myEncodedFile.txt";
        Path encodedFilePath = Path.of(encodedFile);
        System.out.println("Enter the key for encoding");
        int key = scanner.nextInt();

        String symbols = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            while (bufferedReader.ready()) {
                symbols = bufferedReader.readLine();
            }

            char[] symbolsToChar = new char[symbols.length()];

            for (int i = 0; i < symbols.length(); i++) {
                if (Character.isLetter(symbols.charAt(i)) && Character.isUpperCase(symbols.charAt(i))) {
                    symbolsToChar[i] = (char) (((((int) (symbols.charAt(i))) - 'А' + key) % 32) + 'А');
                } else if (Character.isLetter(symbols.charAt(i)) && Character.isLowerCase(symbols.charAt(i))) {
                    symbolsToChar[i] = (char) (((((int) (symbols.charAt(i))) - 'а' + key) % 32) + 'а');
                } else {
                    symbolsToChar[i] = symbols.charAt(i);
                }
            }
            System.out.println(symbolsToChar);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encodedFile))) {
                for (int i = 0; i < symbolsToChar.length; i++) {
                    bufferedWriter.write(symbolsToChar[i]);
                }
                System.out.println("you'll find encoded text in:" + encodedFilePath.toAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("Wrong path:" + filePath.toAbsolutePath());
            e.printStackTrace();
        }
    }


}

