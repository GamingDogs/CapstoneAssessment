import java.util.ArrayList;

/**
 * @author Rohan More
 * @author Ryan
 * @project Capstone
 */
public interface QuoteInterface {

    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> quotes = new ArrayList<>();
    QuoteMultiMap<String, String> quoteBook = new QuoteMultiMap<>();

    void generateRandomQuote();

    void findQuoteByAuthor(String author, boolean partialMatch);

    void findQuoteByPhrase(String phrase);

    boolean loadFile(String path);

    void help();

    void exit();

}
