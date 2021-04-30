package res.ressources.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class maps the config file to be accessible by the program
 */
public class ConfigManager
{
    private static ConfigManager instance;
    private final static String PROPERTIES_PATH = System.getProperty("user.dir") + "/configs/config.properties";
    private Properties loadedProperties;

    /**
     * Load configuration file
     */
    private ConfigManager()
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

    /**
     * Using Singleton design pattern
     *
     * @return Instance of ConfigManager
     */
    public static ConfigManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Configured SMTP port
     *
     * @return SMTP port
     */
    public int getSMTPPort()
    {
        return Integer.parseInt(loadedProperties.getProperty("SMTP_DEFAULT_PORT"));
    }

    /**
     * @return SMTP server address
     */
    public String getSMTPServer()
    {
        return loadedProperties.getProperty("SMTP_DEFAULT_SERVER");
    }

    /**
     * Number of groups / emails that will be send
     *
     * @return Number of groups
     */
    public int getNumberOfGroups()
    {
        return Integer.parseInt(loadedProperties.getProperty("NUMBER_OF_GROUPS"));
    }

    /**
     * @return Client name
     */
    public String getClientName()
    {
        return loadedProperties.getProperty("CLIENT_NAME");
    }

    /**
     * The "real sender" of the mails
     * @return hidden sender
     */
    public String getHiddenSender()
    {
        return loadedProperties.getProperty("MAIL_HIDDEN_SENDER");
    }

    /**
     * CC receipient for every email
     *
     * @return Mail address
     */
    public String getCopyRecipient()
    {
        return loadedProperties.getProperty("MAIL_CC");
    }

    /**
     * BCC receipient for every email
     *
     * @return Mail address
     */
    public String getBlindCopyRecipient()
    {
        return loadedProperties.getProperty("MAIL_BCC");
    }

    /**
     * Path to emails file
     *
     * @return
     */
    public String getEmailFile()
    {
        return loadedProperties.getProperty("EMAILS_FILEPATH");
    }

    /**
     * Path to pranks file
     *
     * @return
     */
    public String getPranksFile()
    {
        return loadedProperties.getProperty("PRANKS_FILEPATH");
    }
}
