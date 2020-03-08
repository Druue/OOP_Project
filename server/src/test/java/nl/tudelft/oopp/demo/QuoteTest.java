package nl.tudelft.oopp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.demo.models.FoodCourt;
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
        FoodCourt.Quote quote = new FoodCourt.Quote(1, quoteText, quoteAuthor);
        quoteRepository.save(quote);

        FoodCourt.Quote quote2 = quoteRepository.getOne((long) 1);
        assertEquals(quote, quote2);
    }
}
