package res.ressources.prank;

import org.junit.Test;
import res.ressources.pranks.PrankJSONParser;

import static org.junit.Assert.assertNotNull;

public class PrankJSONParserTest
{
    @Test
    public void shouldGenerate ()
    {
        assertNotNull(PrankJSONParser.getPranks());
    }
}
