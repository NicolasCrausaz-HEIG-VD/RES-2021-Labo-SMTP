package res;

import res.ressources.config.ConfigManager;
import res.ressources.entities.Mail;
import res.ressources.pranks.PrankGenerator;
import res.ressources.smtp.SMTPClient;

import java.util.List;

public class App
{
    public static void main(String[] args) throws InterruptedException
    {
        List<Mail> mails = new PrankGenerator().preparePranksMails();
        SMTPClient client = new SMTPClient(ConfigManager.getInstance().getSMTPPort(),
                                           ConfigManager.getInstance().getSMTPServer(),
                                           ConfigManager.getInstance().getClientName());

        client.sendMultipleMails(mails);
    }
}
