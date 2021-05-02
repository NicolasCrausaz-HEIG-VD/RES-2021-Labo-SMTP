package res.ressources.prank;

import org.junit.Test;
import res.ressources.pranks.PrankJSONParser;

import static org.junit.Assert.assertNotNull;

/**
 * Test the PrankJSONParser class
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class PrankJSONParserTest
{
    @Test
    public void shouldGenerate ()
    {
        assertNotNull(PrankJSONParser.getPranks());
    }
}
