import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class Decoder {
    private static Scanner scanner = new Scanner(System.in);


    public static void decodeFile() {

        System.out.println("Enter name of the file, you want to decode");
        String fileName = scanner.nextLine();
        Path filePath = Path.of(fileName).toAbsolutePath();
        String decodedFile = "myDecodedFile.txt";
        Path decodedFilePath = Path.of(decodedFile);
        System.out.println("Enter the key for decoding");
        int key = scanner.nextInt();

        String symbols = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            while (bufferedReader.ready()) {
                symbols = bufferedReader.readLine();
            }

            char[] symbolsToChar = new char[symbols.length()];

            for (int i = 0; i < symbols.length(); i++) {
                if (Character.isLetter(symbols.charAt(i)) && Character.isLowerCase(symbols.charAt(i))) {
                    symbolsToChar[i] = (char) (((((int) (symbols.charAt(i))) - 'а' - key) % 32) + 'а');
                } else if (Character.isLetter(symbols.charAt(i)) && Character.isUpperCase(symbols.charAt(i))) {
                    symbolsToChar[i] = (char) (((((int) (symbols.charAt(i))) - 'А' - key) % 32) + 'А');
                } else {
                    symbolsToChar[i] = symbols.charAt(i);
                }
            }
            System.out.println(symbolsToChar);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decodedFile))) {
                for (int i = 0; i < symbolsToChar.length; i++) {
                    bufferedWriter.write(symbolsToChar[i]);
                }
            }

        } catch (IOException e) {
            System.out.println("Wrong path:" + filePath.toAbsolutePath());
            e.getMessage();

        }
    }
}

