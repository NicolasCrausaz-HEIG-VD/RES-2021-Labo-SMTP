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

    private int SMTP_DEFAULT_PORT;
    private String SMTP_DEFAULT_SERVER;
    private int NUMBER_OF_GROUPS;
    private String CLIENT_NAME;
    private String MAIL_HIDDEN_SENDER;
    private String MAIL_CC;
    private String MAIL_BCC;

    /**
     * Load configuration file,
     * Configuration required values will be verified
     *
     * @throws RuntimeException if required values are missing
     */
    private ConfigManager() throws RuntimeException
    {
        try (InputStream input = new FileInputStream(PROPERTIES_PATH))
        {
            Properties loadedProperties = new Properties();
            loadedProperties.load(input);

            if (!loadedProperties.getProperty("SMTP_DEFAULT_PORT").equals(""))
            {
                SMTP_DEFAULT_PORT = Integer.parseInt(loadedProperties.getProperty("SMTP_DEFAULT_PORT"));
            } else
            {
                throw new RuntimeException("SMTP_DEFAULT_PORT should be specified");
            }

            if (!loadedProperties.getProperty("SMTP_DEFAULT_SERVER").equals(""))
            {
                SMTP_DEFAULT_SERVER = loadedProperties.getProperty("SMTP_DEFAULT_SERVER");
            } else
            {
                throw new RuntimeException("SMTP_DEFAULT_SERVER should be specified");
            }

            if (!loadedProperties.getProperty("NUMBER_OF_GROUPS").equals(""))
            {
                NUMBER_OF_GROUPS = Integer.parseInt(loadedProperties.getProperty("NUMBER_OF_GROUPS"));
            } else
            {
                throw new RuntimeException("NUMBER_OF_GROUPS should be specified");
            }

            if (!loadedProperties.getProperty("CLIENT_NAME").equals(""))
            {
                CLIENT_NAME = loadedProperties.getProperty("CLIENT_NAME");
            } else
            {
                throw new RuntimeException("CLIENT_NAME should be specified");
            }

            if (!loadedProperties.getProperty("MAIL_HIDDEN_SENDER").equals(""))
            {
                MAIL_HIDDEN_SENDER = loadedProperties.getProperty("MAIL_HIDDEN_SENDER");
            } else
            {
                throw new RuntimeException("MAIL_HIDDEN_SENDER should be specified");
            }

            MAIL_CC = loadedProperties.getProperty("MAIL_CC");
            MAIL_BCC = loadedProperties.getProperty("MAIL_BCC");


        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Get instance of configuration
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
        return SMTP_DEFAULT_PORT;
    }

    /**
     * @return SMTP server address
     */
    public String getSMTPServer()
    {
        return SMTP_DEFAULT_SERVER;
    }

    /**
     * Number of groups / emails that will be send
     *
     * @return Number of groups
     */
    public int getNumberOfGroups()
    {
        return NUMBER_OF_GROUPS;
    }

    /**
     * @return Client name
     */
    public String getClientName()
    {
        return CLIENT_NAME;
    }

    /**
     * The "real sender" of the mails
     *
     * @return hidden sender
     */
    public String getHiddenSender()
    {
        return MAIL_HIDDEN_SENDER;
    }

    /**
     * CC receipient for every email
     *
     * @return Mail address
     */
    public String getCopyRecipient()
    {
        return MAIL_CC;
    }

    /**
     * BCC receipient for every email
     *
     * @return Mail address
     */
    public String getBlindCopyRecipient()
    {
        return MAIL_BCC;
    }
}
