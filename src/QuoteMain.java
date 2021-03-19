import java.util.Scanner;

/**
 * @author Rohan More
 * @author Ryan Cosner
 * @project CapstoneAssessment
 */
public class QuoteMain {

    public static void main(String[] args) {

        //Initiate Scanner and variable for scanner
        Scanner scan = new Scanner(System.in, "UTF-8");
        String command;

        //Create object in order to execute commands
        QuoteCommand util = new QuoteCommand();
        QuoteMultiMapCommand utilNew = new QuoteMultiMapCommand();
        //Display help message
        util.help();
        //Load quote file from user
        if (!util.loadFile("./quotes.txt")) {
            System.out.println("Sorry, we could not load that quote file.\nPlease use the 'load' command to load a new file!");
        } else {
            //Display a random quote
            util.generateRandomQuote();
        }

        //Loop until user quits
        //noinspection InfiniteLoopStatement
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
                    System.out.print("Would you like to partially match the author? [true/false]: ");
                    String partialMatch = scan.nextLine();
                    if (Boolean.parseBoolean(partialMatch)) {
                        System.out.println("ArrayList Search");
                        util.findQuoteByAuthor(command, true);
                        System.out.println("MultiMap Search");
                        utilNew.mmPartialAuthorSearch(command);
                    } else {
                        System.out.println("ArrayList Search");
                        util.findQuoteByAuthor(command, false);
                        System.out.println("MultiMap Search");
                        utilNew.mmExactAuthorSearch(command);
                    }
                    break;
                case "3":
                    System.out.print("Enter Phrase: ");
                    command = scan.nextLine();
                    System.out.println("ArrayList Search");
                    util.findQuoteByPhrase(command);
                    System.out.println("MultiMap Search");
                    utilNew.mmQuotePhraseSearch(command);
                    break;
                case "exit":
                case "4":
                    util.exit();
                    break;
                case "load":
                    System.out.print("Enter quote file path: ");
                    command = scan.nextLine();
                    util.loadFile(command);
                    break;
                case "help":
                    util.help();
                    break;
                default:
                    System.out.println("Sorry, that command was not found!\nTry using the help command.\n");
            }
        }
    }
}
