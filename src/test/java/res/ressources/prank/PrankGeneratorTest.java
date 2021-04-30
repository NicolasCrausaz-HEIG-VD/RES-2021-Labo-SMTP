package res.ressources.prank;

import org.junit.Test;
import res.ressources.config.ConfigManager;
import res.ressources.entities.Mail;
import res.ressources.pranks.PrankGenerator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrankGeneratorTest
{

    @Test
    public void shouldGeneratePrankList()
    {
        List<Mail> mails = new PrankGenerator().preparePranksMails();
        assertNotNull(mails);
    }

    @Test
    public void shouldGeneratePrankListOfConfiguredSize()
    {
        List<Mail> mails = new PrankGenerator().preparePranksMails();
        assertEquals(mails.size(), ConfigManager.getInstance().getNumberOfGroups());
    }
}
