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

public class SMTPClient implements ISMTPCLient
{
    private final int smtpPort;
    private final String smtpAdress;
    private final String clientName;

    private Socket clientSocket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    static final String CONNECTION_OK = "220";
    static final String COMMAND_OK = "250";
    static final String WRITING_MAIL = "354";
    static final String QUIT_OK = "221";

    //final static Logger LOG = Logger.getLogger(SingleThreadedServer.class.getName());

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
        // TODO: vérifier les retours de sendTextToServ -> arrêter ou réessayer?
        if (!connect())
        {
            System.out.println("Connection error occured");
            close();
        }

        pass();

        sendTextToServ("EHLO " + clientName, COMMAND_OK);
        // outputStream.println("EHLO " + clientName, COMMAND_OK);
        outputStream.flush();

        pass();

        sendTextToServ("MAIL FROM: " + mail.getFrom(), COMMAND_OK);
        outputStream.println("MAIL FROM: " + mail.getFrom());
        outputStream.flush();

        // pass();

        for (Person p : mail.getTo().getVictims())
        {
            sendTextToServ("RCPT TO: " + p.getEmail(), COMMAND_OK);
            outputStream.println("RCPT TO: " + p.getEmail());
            outputStream.flush();
            //pass();
        }

        sendTextToServ("DATA", WRITING_MAIL);
        outputStream.println("DATA");
        outputStream.flush();

        pass();

        outputStream.println("From: " + mail.getTo().getPranker().getEmail());

        final LinkedList<Person> victims = mail.getTo().getVictims();
        final int size = victims.size();

        outputStream.print("To: ");

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
        sendTextToServ(".", COMMAND_OK);
        // outputStream.println(".");
        outputStream.flush();

        // TODO: reset RSET pour éviter de fermer la connexion si on a plusieurs emails à faire

        pass();

        // outputStream.println("QUIT");
        sendTextToServ("QUIT", QUIT_OK);
        outputStream.flush();

        close();
    }

    public boolean sendTextToServ(String message, String waitedCodeResponse)
    {
        String response;
        String[] tokensSpace;

        outputStream.println(message);
        outputStream.flush();

        try
        {
            // first line
            response = inputStream.readLine();
            System.out.println(response);

            tokensSpace = response.split(" ");

            // ignore the next lines
            while (response != null && !response.isEmpty() && response.equals("\r"))
            {
                System.out.println(response);
                response = inputStream.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        if (!tokensSpace[0].equals(waitedCodeResponse))
        {
            System.out.println("Waited code received is different compare to the needed one!");
            return false;
        }

        return true;
    }

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
                line = inputStream.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
