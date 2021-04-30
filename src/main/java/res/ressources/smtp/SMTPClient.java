package res.ressources.smtp;

import res.ressources.config.ConfigManager;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SMTP client to send mails
 */
public class SMTPClient implements ISMTPCLient
{
    private final int smtpPort;
    private final String smtpAddress;
    private final String clientName;

    private Socket clientSocket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    private final static Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    public SMTPClient(int smtpPort, String smtpAddress, String clientName)
    {
        this.smtpPort = smtpPort;
        this.smtpAddress = smtpAddress;
        this.clientName = clientName;
    }

    /**
     * Try to connect to SMTP server
     * @return true if connected
     */
    public boolean connect()
    {
        try
        {
            clientSocket = new Socket(smtpAddress, smtpPort);
            try
            {
                inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outputStream = new PrintWriter(clientSocket.getOutputStream());
            } catch (IOException e)
            {
                LOG.log(Level.SEVERE, null, e);
                close();
                return false;
            }
        } catch (IOException e)
        {
            LOG.log(Level.SEVERE, null, e);
            close();
            return false;
        }
        return true;
    }

    /**
     * Close connection to server
     */
    public void close()
    {
        if (inputStream != null)
        {
            try
            {
                inputStream.close();
            } catch (IOException e)
            {
                LOG.log(Level.SEVERE, null, e);
            }
        }

        if (outputStream != null)
        {
            try
            {
                outputStream.close();
            } catch (Exception e)
            {
                LOG.log(Level.SEVERE, null, e);
            }
        }

        if (clientSocket != null)
        {
            try
            {
                clientSocket.close();
            } catch (IOException e)
            {
                LOG.log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void sendMail(Mail mail)
    {
        if (!connect())
        {
            System.out.println("Connection error occured");
        }
        recieveServerResponse();

        sendEmailWithConnection(mail, false);

        recieveServerResponse();
        outputStream.println("QUIT");
        outputStream.flush();
        close();
    }

    @Override
    public void sendMultipleMails(List<Mail> mails) throws InterruptedException
    {
        if (!connect())
        {
            System.out.println("Connection error occured");
            close();
        }
        recieveServerResponse();

        for (Mail m : mails)
        {
            sendEmailWithConnection(m, true);
            recieveServerResponse();
            Thread.sleep(1000);
        }

        recieveServerResponse();
        outputStream.println("QUIT");
        outputStream.flush();
        close();
    }

    /**
     * Read and log server response
     */
    private void recieveServerResponse()
    {
        try
        {
            // Log the server response
            String line;
            while (inputStream.ready() && (line = inputStream.readLine()) != null)
            {
                LOG.log(Level.INFO, line);
            }
        } catch (IOException e)
        {
            LOG.log(Level.SEVERE, null, e);
        }
    }

    /**
     * Send a mail, option to not close the connection after sending, used to send multiple emails without
     * closing the connection
     *
     * @param mail mail to send
     * @param keepConnection flag to stay connected after sending
     */
    private void sendEmailWithConnection(Mail mail, boolean keepConnection)
    {
        outputStream.println("EHLO " + clientName);
        outputStream.flush();
        LOG.log(Level.INFO, "CLIENT: EHLO " + clientName);
        recieveServerResponse();

        outputStream.println("MAIL FROM: " + mail.getFrom());
        outputStream.flush();
        LOG.log(Level.INFO, "MAIL FROM: " + mail.getFrom());
        recieveServerResponse();

        for (Person p : mail.getTo().getVictims())
        {
            outputStream.println("RCPT TO: " + p.getEmail());
            outputStream.flush();
            LOG.log(Level.INFO, "CLIENT: RCPT TO: " + p.getEmail());
            recieveServerResponse();
        }

        outputStream.println("DATA");
        outputStream.flush();
        LOG.log(Level.INFO, "CLIENT: DATA");
        recieveServerResponse();

        outputStream.println("From: " + mail.getTo().getPranker().getEmail());
        outputStream.print("To: ");

        final List<Person> victims = mail.getTo().getVictims();
        final int size = victims.size();

        for (int i = 0; i < size; ++i)
        {
            outputStream.print(victims.get(i).getEmail());

            if (i < size - 1)
            {
                outputStream.print(",");
            }
        }

        outputStream.print("\n");
        outputStream.println("Cc: " + ConfigManager.getInstance().getCopyRecipient());
        outputStream.println("Bcc: " + ConfigManager.getInstance().getBlindCopyRecipient());

        outputStream.println("Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(mail.getSubject().getBytes()) + "?=");
        outputStream.println("Content-Type: text/plain; charset=utf-8\n");
        outputStream.println(mail.getMessage());

        outputStream.println(".");
        outputStream.flush();
        LOG.log(Level.INFO, ".");
        recieveServerResponse();

        if (keepConnection)
        {
            outputStream.println("RSET");
            outputStream.flush();
            LOG.log(Level.INFO, "CLIENT: RSET");
            recieveServerResponse();
        }
    }
}
