package nl.tudelft.oopp.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.server.entities.Quote;
import nl.tudelft.oopp.server.repositories.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuoteTest {
    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    public void saveAndRetrieveQuote() {
        String quoteText = "Tell me and I forget. Teach me and I remember. Involve me and I learn.";
        String quoteAuthor = "Benjamin Franklin";
        Quote quote = new Quote(1, quoteText, quoteAuthor);
        quoteRepository.save(quote);

        Quote quote2 = quoteRepository.getOne((long) 1);
        assertEquals(quote, quote2);
    }
}
