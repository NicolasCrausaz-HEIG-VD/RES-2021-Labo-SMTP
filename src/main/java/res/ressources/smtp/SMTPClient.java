package res.ressources.smtp;

import res.ressources.entities.Mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
        String line = "";

        try
        {
            clientSocket = new Socket(smtpAdress, smtpPort);
            try
            {
                inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outputStream = new PrintWriter(clientSocket.getOutputStream());

                // first line
                /*line = inputStream.readLine();

                // ignore the next lines
                String nextLine;
                nextLine = inputStream.readLine();
                while (nextLine != null)
                {
                    nextLine = inputStream.readLine();
                }*/
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

        /*if(!line.equals("Connected to" + smtpAdress))
        {
            return false;
        }*/

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

        outputStream.println("EHLO " + clientName);
        outputStream.flush();

        //pass();

        outputStream.println("MAIL FROM: " + mail.getAddressEmailFrom());
        outputStream.flush();

        //pass();

        outputStream.println("RCPT TO: " + mail.getAddressEmailTo());

        //pass();

        outputStream.println("DATA");
        outputStream.flush();

        //pass();

        outputStream.println(mail.getMessage() + "<cr><lf>.</lf></cr>");
        outputStream.flush();

        //pass();

        outputStream.println(mail.getMessage() + "quit");
        outputStream.flush();


        close();

        /*String line;

        line = inputStream.readLine();
        System.out.println(line);

        String userLine;
        while (!clientSocket.isClosed() && shouldRun)
        {

            //Lire l'entée utilisateur et l'envoyer au serveur
            if ((userLine = input.readLine()) != null)
            {
                outputStream.println(userLine);
                outputStream.flush();

                // arrête le client si QUIT (après l'avoir envoyé au serv)
                if (userLine.toUpperCase().equals("QUIT"))
                {

                }
            }

            //Lit la réponse du serv
            if ((line = inputStream.readLine() + " : " + inputStream.readLine()) != null)
            {
                System.out.println(line);
            }
        }

        finally
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
        }*/
    }

    public boolean printMessage(String message, String waitedResponse)
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
            while (line != null)
            {
                line = inputStream.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isAValidEmail(String email)
    {
        /*Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(mat.matches())
        {
            return true;
        }*/

        return false;
    }

    /*public boolean connectToCompany()
    {
        String response;

        outputStream.println("EHLO " + company);
        outputStream.flush();

        try
        {
            response = inputStream.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        if(!response.equals())
        {
            return false;
        }

        return true;
    }*/
}
