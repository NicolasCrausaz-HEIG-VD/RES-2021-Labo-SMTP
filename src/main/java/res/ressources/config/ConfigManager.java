package res.ressources.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager
{
    private final static String PROPERTIES_PATH = System.getProperty("user.dir") + "/configs/config.properties";
    private Properties loadedProperties;

    public ConfigManager()
    {
        try (InputStream input = new FileInputStream(PROPERTIES_PATH))
        {
            loadedProperties = new Properties();
            loadedProperties.load(input);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public int getSMTPPort()
    {
        return Integer.parseInt(loadedProperties.getProperty("SMTP_DEFAULT_PORT"));
    }

    public String getSMTPServer()
    {
        return loadedProperties.getProperty("SMTP_DEFAULT_SERVER");
    }

    public int getNumberOfGroups()
    {
        return Integer.parseInt(loadedProperties.getProperty("NUMBER_OF_GROUPS"));
    }

    public String getCopyRecipient()
    {
        return loadedProperties.getProperty("MAIL_CC");
    }

    public String getBlindCopyRecipient()
    {
        return loadedProperties.getProperty("MAIL_BCC");
    }
}
