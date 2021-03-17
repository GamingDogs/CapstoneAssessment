import java.util.Scanner;

/**
 * @author Rohan More
 * @author Ryan
 * @project Capstone
 */
public class QuoteMain {


    public static void main(String[] args) {

        QuoteCommand util = new QuoteCommand();
        boolean loadFile = util.loadFile("./quotes.txt");
        if (loadFile)
            util.generateRandomQuote();
        util.help();

        Scanner scan = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter Option: ");
            command = scan.nextLine();
            switch (command) {
                case "1":
                    util.generateRandomQuote();
                    break;
                case "2":
                    System.out.print("Enter Author: ");
                    command = scan.nextLine();
                    System.out.print("Do you want to partial match the author? [true/false]: ");
                    String partialMatch = scan.nextLine();
                    util.findQuoteByAuthor(command, Boolean.parseBoolean(partialMatch));
                    break;
                case "3":
                    System.out.print("Enter phrase: ");
                    command = scan.nextLine();
                    util.findQuoteByPhrase(command);
                    break;
                case "4":
                case "exit":
                    util.exit();
                    break;
                case "5":
                case "load":
                    System.out.print("Enter quote file path: ");
                    command = scan.nextLine();
                    util.loadFile(command);
                    break;
                case "6":
                case "help":
                    util.help();
                    break;
            }

        }

    }


}
