package res.ressources.config;

import org.junit.Test;

/**
 * Test the ConfigManager class
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class ConfigManagerTest
{
    @Test
    public void shouldOpenConfigFileWithoutThrowing()
    {
        ConfigManager.getInstance();
    }
}
