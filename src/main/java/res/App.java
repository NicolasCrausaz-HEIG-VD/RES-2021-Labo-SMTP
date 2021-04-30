package res;

import res.ressources.config.ConfigManager;
import res.ressources.entities.Mail;
import res.ressources.pranks.PrankGenerator;
import res.ressources.smtp.SMTPClient;

import java.util.LinkedList;

public class App
{
    public static void main(String[] args) throws InterruptedException
    {
        PrankGenerator pg = new PrankGenerator("test@test.ch", ConfigManager.getInstance().getNumberOfGroups());
        LinkedList<Mail> mails = pg.preparePranksMails();
        SMTPClient client = new SMTPClient(ConfigManager.getInstance().getSMTPPort(), ConfigManager.getInstance().getSMTPServer(), "RobotApp");

        client.sendMultipleMails(mails);
    }
}
