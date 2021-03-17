import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Rohan More
 * @author Ryan
 * @project Capstone
 */
public class QuoteCommand implements QuoteInterface {
    @Override
    public void generateRandomQuote() {
        /**
         * Use random object to generate a random number from 0 to the size of quotes dictionary
         */
        Random rand = new Random();
        int randIndex = rand.nextInt(authors.size());
        System.out.println(quotes.get(randIndex) + "\n-- " + authors.get(randIndex) + "\n");
    }

    void binSearchAuthor(String author) {
        //todo
    }

    @Override
    public void findQuoteByAuthor(String author, boolean partialMatch) {
        /**
         * Linear search until you find a match
         * if the index of authors contains the author, print the quote and author
         */
        for (int i = 0; i < authors.size(); i++) {
            if (partialMatch) {
                if (authors.get(i).toLowerCase().contains(author.toLowerCase())) {
                    System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
                }
            } else {
                if (authors.get(i).equalsIgnoreCase(author)) {
                    System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
                }
            }
        }
    }

    @Override
    public void multiMapExactAuthorSearch(String author) {
        if (quoteBook.containsKey(author)) {
            Collection<String> values = new ArrayList<>(quoteBook.get(author));
            for (String quotes : values) {
                System.out.println(quotes + "\n-- " + author + "\n");
            }
        }
    }

    @Override
    public void multiMapPartialAuthorSearch(String author) {
        for (String authors : quoteBook.keySet()) {
            if (authors.toLowerCase().contains(author.toLowerCase())) {
                Collection<String> quotes = new ArrayList<>(quoteBook.get(authors));
                for (String quote : quotes) {
                    System.out.println(quote + "\n-- " + authors + "\n");
                }
            }
        }
    }

    @Override
    public void findQuoteByPhrase(String phrase) {
        /**
         * Linear search until you find a match
         * if the index of quotes contains the phrase, print the quote and author
         */
        for (int i = 0; i < quotes.size(); i++) {
            if (quotes.get(i).toLowerCase().contains(phrase.toLowerCase())) {
                System.out.println(quotes.get(i) + "\n-- " + authors.get(i) + "\n");
            }
        }
    }

    @Override
    public boolean loadFile(String path) {
        //Clear arraylists
        authors.clear();
        quotes.clear();

        try {
            /**
             * Initiate Scanner and variables
             * StringBuilder for loop concatenations, faster
             */
            Scanner quoteFile = new Scanner(new File(path), "UTF-8");
            String tempRead;
            String author = "";
            StringBuilder quote = new StringBuilder();

            /**
             * Loop until we reach the end of the time
             * If the string starts with -- then we know its the author
             * else continue adding it to the quote variable
             * Add author and quotes to arraylist
             */
            while (quoteFile.hasNext()) {
                tempRead = quoteFile.nextLine();

                if (tempRead.startsWith("--")) {
                    author = tempRead.replace("--", "").trim();
                    quote = new StringBuilder(quote.toString().replaceAll("(?m)^\\s+$", "").trim());

                    authors.add(author);
                    quotes.add(quote.toString());
                    quoteBook.put(author, quote.toString());

                    quote = new StringBuilder("\n");
                } else {
                    quote.append(tempRead);
                    quote.append("\n");
                }
            }

        } catch (Exception err) {
            System.out.println("Unexpected error, please load another file!");
            return false;
        }
        return true;
    }

    @Override
    public void help() {
        System.out.println("Commands\n" +
                "1: Generate Random Quote\n" +
                "2: Search by quote by author\n" +
                "3: Search author by phrase\n" +
                "4/exit: Exit program" +
                "5/load: Load a new quote file\n" +
                "6/help: Show list of commands\n");
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
