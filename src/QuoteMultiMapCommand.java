import java.util.*;

/**
 * @author Rohan More
 * @project CapstoneAssessment
 */
public abstract class QuoteMultiMapCommand implements QuoteInterface {

    public void multiMapExactAuthorSearch(String author) {
        /**
         * If the multimap contains the author then proceed
         * Get all the values of the multimap
         * Loop through collection of quotes and display them
         */
        if (quoteBook.containsKey(author)) {
            Collection<String> values = new ArrayList<>(quoteBook.get(author));
            for (String quotes : values) {
                System.out.println(quotes + "\n-- " + author + "\n");
            }
        }
    }

    public void multiMapPartialAuthorSearch(String author) {
        /**
         * Get the key and loop through them
         * If the current author contains the users author
         * then get and display the quotes
         */
        for (String authors : quoteBook.keySet()) {
            if (authors.toLowerCase().contains(author.toLowerCase())) {
                Collection<String> quotes = new ArrayList<>(quoteBook.get(authors));
                for (String quote : quotes) {
                    System.out.println(quote + "\n-- " + authors + "\n");
                }
            }
        }
    }

    //Get random quote and author from Multimap
    void generateRandomQuoteMultiMap() {
        Set<String> keySet = quoteBook.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        Random rand = new Random();
        int randIndex = rand.nextInt(keyList.size());
        String randomKey = keyList.get(randIndex);

        Collection<String> randValue = quoteBook.get(randomKey);
        int randomIndexValue = rand.nextInt(randValue.size());

        System.out.println(randValue.toArray()[randomIndexValue] + "\n-- " + randomKey + "\n");

    }


}
