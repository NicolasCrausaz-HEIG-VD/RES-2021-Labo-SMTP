package res.ressources.smtp;

import res.ressources.entities.Mail;
import res.ressources.entities.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMTPClient implements ISMTPCLient
{
    private final int smtpPort;
    private final String smtpAdress;
    private final String clientName;

    private Socket clientSocket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    final static Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    public SMTPClient(int smtpPort, String smtpAdress, String clientName)
    {
        this.smtpPort = smtpPort;
        this.smtpAdress = smtpAdress;
        this.clientName = clientName;
    }

    public boolean connect()
    {
        try
        {
            clientSocket = new Socket(smtpAdress, smtpPort);
            try
            {
                inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outputStream = new PrintWriter(clientSocket.getOutputStream());
            } catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void close()
    {
        try
        {
            inputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        if (outputStream != null)
        {
            outputStream.close();
        }

        try
        {
            clientSocket.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendMail(Mail mail)
    {
        if (!connect())
        {
            System.out.println("Connection error occured");
            close();
        }

        sendEmailWithConnection(mail, false);

        close();
    }

    /*
    @Override
    public void sendMultipleMails(List<Mail> mails)
    {
        if (!connect())
        {
            System.out.println("Connection error occured");
            close();
        }



        for (Mail m : mails)
        {
            sendEmailWithConnection(m, true);
        }

        close();
    }
*/

    void pass()
    {
        try
        {
            // ignore the next lines
            String line;
            line = inputStream.readLine();
            while (line != null && !line.isEmpty() && line.equals("\r"))
            {
                System.out.println(line);
                LOG.log(Level.INFO, "test: {0}", line);
                line = inputStream.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void sendEmailWithConnection(Mail mail, boolean keepConnection) throws RuntimeException
    {
        pass();
        outputStream.println("EHLO " + clientName);
        outputStream.flush();
        pass();

        outputStream.println("MAIL FROM: " + mail.getFrom());
        outputStream.flush();

        for (Person p : mail.getTo().getVictims())
        {
            outputStream.println("RCPT TO: " + p.getEmail());
            outputStream.flush();
        }

        outputStream.println("DATA");
        outputStream.flush();

        //pass();

        outputStream.println("From: " + mail.getTo().getPranker().getEmail());
        outputStream.print("To: ");

        final LinkedList<Person> victims = mail.getTo().getVictims();
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

        outputStream.println("Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(mail.getSubject().getBytes()) + "?=");
        outputStream.println("Content-Type: text/plain; charset=utf-8\n");
        outputStream.println(mail.getMessage());

        outputStream.println(".");
        outputStream.flush();

        if (keepConnection)
        {
            // TODO: Faire RSET
        } else
        {
            pass();
            outputStream.println("QUIT");
            outputStream.flush();
        }
    }
}
