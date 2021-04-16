package res.ressources.smtp;

import res.ressources.entities.Mail;
import res.ressources.entities.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class SMTPClient implements ISMTPCLient
{
    private int smtpPort;
    private String smtpAdress;
    private String clientName;

    private Socket clientSocket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

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
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        catch (IOException e)
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
        }
        catch (IOException e)
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
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendMail(Mail mail)
    {
        if(!connect())
        {
            System.out.println("");
            close();
        }

        pass();

        outputStream.println("EHLO " + clientName);
        outputStream.flush();

        pass();

        outputStream.println("MAIL FROM: " + mail.getFrom());
        outputStream.flush();

        pass();

        for (Person p : mail.getTo().getVictims())
        {
            outputStream.println("RCPT TO: " + p.getEmail());
            outputStream.flush();
            pass();
        }

        outputStream.println("DATA");
        outputStream.flush();

        pass();

        outputStream.println("From: " + mail.getTo().getPranker().getEmail());

        final LinkedList<Person> victims = mail.getTo().getVictims();
        final int size = victims.size();

        outputStream.print("To: ");

        for(int i = 0; i < size; ++i)
        {
            outputStream.print(victims.get(i).getEmail());

            if(i < size-1)
            {
                outputStream.print(",");
            }
        }
        outputStream.print("\n");

        outputStream.println("Subject: " + mail.getSubject() + "\n");
        outputStream.println(mail.getMessage());
        outputStream.println(".");
        outputStream.flush();

        pass();

        outputStream.println("QUIT");
        outputStream.flush();

        close();
    }

    public boolean sendTextToServ(String message, String waitedResponse)
    {
        String response;

        outputStream.println(message);
        outputStream.flush();

        try
        {
            // first line
            response = inputStream.readLine();

            // ignore the next lines
            String line;
            line = inputStream.readLine();
            while (!line.isEmpty())
            {
                System.out.println();
                line = inputStream.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        if(!response.equals(waitedResponse))
        {
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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
