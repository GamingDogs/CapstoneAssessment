import java.util.ArrayList;

/**
 * @author Rohan More
 * @author Ryan Cosner
 * @project CapstoneAssessment
 */
public interface QuoteInterface {

    /**
     * Store Author and Quotes here
     * You can't have duplicate keys in a hashmap (K -> 1)
     * So we are using a multimap which allows a collection of values for 1 key
     */
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> quotes = new ArrayList<>();
    QuoteMultiMap<String, String> quoteBook = new QuoteMultiMap<>();

    /**
     * Find a random quote and display it
     */
    void generateRandomQuote();

    /**
     * Find a certain quote based off the authors name
     * User can choose to use a partial match in order to find quotes
     * @param author - Author to search for
     * @param partialMatch - Partial Match the search
     */
    void findQuoteByAuthor(String author, boolean partialMatch);

    /**
     * Find quotes that contain a certain phrase
     * @param phrase - Phrase to search for
     */
    void findQuoteByPhrase(String phrase);

    /**
     * Load a quote file based on a certain path
     * @param path - Path of the quote file
     * @return - Return whether the function successfully executed or not
     */
    boolean loadFile(String path);

    /**
     * Display a help menu
     */
    void help();

    /**
     * Exit the program
     */
    void exit();

}
