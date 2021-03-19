import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Rohan More
 * @author Ryan Cosner
 * @project CapstoneAssessment
 */
public class QuoteMultiMapCommand extends QuoteCommand {

    void mmGenerateRandomQuote() {
        //Time and counting variables
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();

        //Get the keys and values as Sets and Lists
        Set<String> keySet = quoteBook.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        //Get a random key
        int randomIndex = new Random().nextInt(keyList.size());
        String randomKey = keyList.get(randomIndex);

        //Get a random value from the collection of quotes
        Collection<String> randomValue = quoteBook.get(randomKey);
        int random = new Random().nextInt(randomValue.size());

        //Display the quote and author
        System.out.println(randomValue.toArray()[random] + "\n-- " + randomKey + "\n");

        //get current time to calculate execution time
        end = System.nanoTime();
        //calculate execution time in milliseconds
        duration = (end - start) / 1000000;
        System.out.printf("It took %dms to find a random quote!\n\n", duration);
    }

    public void mmExactAuthorSearch(String author) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();
        System.out.println("--------------------------------------------");
        /**
         * Because we want an exact value, we can check if the key is in the multimap
         * if it is, then we can get all the values from the key in a collection
         * loop through the values of the collection and display the quotes from the author
        */
        if (quoteBook.containsKey(author)) {
            //Collection<String> values = book.get(author).stream().collect(Collectors.toList());
            Collection<String> values = new ArrayList<>(quoteBook.get(author));
            for (String quote : values) {
                System.out.println(quote + "\n-- " + author + "\n");
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

    public void mmPartialAuthorSearch(String author) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();
        System.out.println("--------------------------------------------");
        /**
         * since the user wants to partial match, we have to use a linear search
         * Linear search the quoteBook
        */
        for (String authors : quoteBook.keySet()) {
            if (authors.toLowerCase().contains(author.toLowerCase())) {
                //Collection<String> quotes = book.get(author).stream().collect(Collectors.toList());
                Collection<String> quotes = new ArrayList<>(quoteBook.get(authors));
                for (String quote : quotes)  {
                    System.out.println(quote + "\n-- " + authors + "\n");
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

    public void mmQuotePhraseSearch(String phrase) {
        //Time and counting variables
        int count = 0;
        long duration;
        long start, end;

        //get current time to calculate execution time
        start = System.nanoTime();
        System.out.println("--------------------------------------------");
        for (String author : quoteBook.keySet()) {
            //Collection<String> quotes = book.get(author).stream().collect(Collectors.toList());
            Collection<String> quotes = new ArrayList<>(quoteBook.get(author));
            for (String quote : quotes)  {
                if (quote.toLowerCase().contains(phrase.toLowerCase())) {
                    System.out.println(quote + "\n-- " + author + "\n");
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
}
