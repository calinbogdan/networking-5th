import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input text: ");
        String text = scanner.nextLine().replace(" ", "");

        System.out.println("Key: ");
        String key = scanner.nextLine();

        if (Arrays.stream(key.split(""))
                .collect(groupingBy(identity(), counting()))
                .values().stream()
                .anyMatch(count -> count > 1)) {
            System.out.println("Scuze, cheie invalida.");
            System.exit(0);
        }

        int columns = key.length();
        int lines = text.length() / columns + 1;

        String[][] matrix = new String[lines][columns];

        if (columns * lines != key.length()) {
            int diff = text.length() / key.length();
            for(int counter = 0; counter < diff; counter++) {
                text += (char) (counter + 'a');
            }
        }

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = String.valueOf(text.charAt(columns * i + j));
            }
        }

        List<String> characters = Arrays.stream(key.split(""))
                .sorted()
                .collect(toList());

        String user1 = "";
        String user2 = text;

        for (String keyCharacter : characters) {
            int index = key.indexOf(keyCharacter);

            for (int i = 0; i < lines; i++) {
                user1 += matrix[i][index];
            }
        }

        System.out.println(user1);
        System.out.println(user2);

        scanner.close();
    }
}
