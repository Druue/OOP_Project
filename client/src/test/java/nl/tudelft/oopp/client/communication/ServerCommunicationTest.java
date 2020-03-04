package nl.tudelft.oopp.client.communication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.api.HttpRequestHandler;
import org.junit.jupiter.api.Test;


public class ServerCommunicationTest {

    @Test
    public void testPing() {
        assertNotNull(HttpRequestHandler.get("ping", String.class));
    }
}
