import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Rohan More
 * @author Ryan Cosner
 * @project CapstoneAssessment
 */
public class QuoteCommand implements QuoteInterface {

    @Override
    public void generateRandomQuote() {
        //Time variables
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();
        /**
         * Use the random object
         * Use the object to generate a number between 0 and size of arraylist
         * Since we use two arraylists, the index for the quote and author will match
         */
        Random rand = new Random();
        int randIndex = rand.nextInt(authors.size());
        System.out.println("--------------------------------------------");
        System.out.println(quotes.get(randIndex) + "\n-- " + authors.get(randIndex) + "\n");

        //get current time to calculate execution time
        end = System.nanoTime();
        //calculate execution time in milliseconds
        duration = (end - start) / 1000000;
        System.out.printf("It took %dms to find a random quote!\n", duration);
        System.out.println("--------------------------------------------\n");
    }

    @Override
    public void findQuoteByAuthor(String author, boolean partialMatch) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();

        System.out.println("--------------------------------------------");
        /**
         * If the user wants a partial match use contains, else use equals
         * Linear search until you find a match
         * if the index of authors contains the author, print the quote and author
         */
        if (partialMatch) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).toLowerCase().contains(author.toLowerCase())) {
                    //Add to if statement to ignore character accents
                    // || Normalizer.normalize(author, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase().contains(authors.get(i)))
                    System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
                    count++;
                }
            }
        } else {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).equalsIgnoreCase(author)) {
                    System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
                    count++;
                }
            }
        }
        //get current time to calculate execution time
        end = System.nanoTime();
        //calculate execution time in milliseconds
        duration = (end - start) / 1000000;
        System.out.printf("It took %dms to find %d quotes from that author!", duration, count);
        System.out.println("\n--------------------------------------------\n");
    }

    @Override
    public void findQuoteByPhrase(String phrase) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();
        System.out.println("--------------------------------------------");
        /**
         * Linear search until you find a match
         * if the index of quotes contains the phrase, print the quote and author
         */
        for (int i = 0; i < quotes.size(); i++) {
            if (quotes.get(i).toLowerCase().contains(phrase.toLowerCase())) {
                System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
                count++;
            }
        }
        //get current time to calculate execution time
        end = System.nanoTime();
        //calculate execution time in milliseconds
        duration = (end - start) / 1000000;
        System.out.printf("It took %dms to find %d quotes from that author!", duration, count);
        System.out.println("\n--------------------------------------------\n");
    }

    @Override
    public boolean loadFile(String path) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();

        //Clear arraylists and multimap
        quoteBook.clear();
        authors.clear();
        quotes.clear();

        //Try to read the file
        System.out.println("Loading quotes file...");
        try {
            /*
             * Initiate Scanner with file path
             * Using charset UTF-8 because of apostrophe errors
             */
            Scanner quotesFile = new Scanner(new File(path), "UTF-8");
            StringBuilder quote = new StringBuilder();
            String author;
            String tempRead;

            while (quotesFile.hasNext()) {
                //Store the line into a temporary string
                tempRead = quotesFile.nextLine();
                /*
                 * If the start of the line is "--" then we know its the author
                 * If it does not start with "--" then we are adding it onto the current quote string
                 */
                if (tempRead.startsWith("--")) {
                    /*
                     * Remove leading and ending lines/spaces from the author and quote
                     * Replace apostrophe with single quote from the quote and authors name
                     */
                    author = tempRead.replace("--", "").trim();
                    //author = author.replaceAll("’", "'");
                    quote = new StringBuilder(quote.toString().replaceAll("’", "'"));
                    quote = new StringBuilder(quote.toString().replaceAll("(?m)^\\s+$", "").trim());

                    //Add the final quote and author to the multimap and arraylists
                    quoteBook.put(author, quote.toString());
                    authors.add(author);
                    quotes.add(quote.toString());
                    quote = new StringBuilder("\n");
                    count++;
                } else {
                    quote.append(tempRead);
                    quote.append("\n");
                }
            }
            //close quote file and add quotes and authors to their respective lists
            quotesFile.close();
            //get current time to calculate execution time
            end = System.nanoTime();
        } catch (Exception err) {
            //Print an error message is something goes wrong
            System.out.println("Error loading!" + "\n" + err.getMessage() + "\n");
            return false;
        }
        duration = (end - start) / 1000000;
        System.out.printf("It took %dms to load %d quotes from the file!\n\n", duration, quoteBook.size());
        return true;
    }

    @Override
    public void help() {
        //Print the commands available to the user
        System.out.println("Commands\n" +
                "1 - Display a random quote\n" +
                "2 - Display all quotes by a certain author\n" +
                "3 - Display quotes from a certain phrase or word\n" +
                "4/exit - Exit the program\n" +
                "load - Loads a new quote file\n" +
                "help - display the help menu\n");
    }

    @Override
    public void exit() {
        //Exit the program
        System.out.println("Exiting Program...");
        System.exit(0);
    }
}