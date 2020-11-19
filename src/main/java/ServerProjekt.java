import sun.security.pkcs11.Secmod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProjekt {

    static ServerSocket welcomeSocket;

    static {
        try {
            welcomeSocket = new ServerSocket(22222);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Socket connectionSocket;

    static {
        try {
            connectionSocket = welcomeSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ServerProjekt() {
    }

    public static String[] sendToKlient() throws IOException {
        String clientSentence;
        String[] clientResponse;
        String mailTilDb;
        String passTilDb;



        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        clientSentence = inFromClient.readLine();
        clientResponse = clientSentence.split("&");
        String[] mail1;
        mail1 = clientResponse[0].split("=");
        mailTilDb = mail1[1];
        String[] pass1;
        pass1 = clientResponse[1].split("=");
        passTilDb = pass1[1];
        System.out.println("FRA KLIENT:" + clientSentence + '\n');
        System.out.println("Starter konverting af input" + '\n');
        //  System.out.println("FRA SERVER: " + serverSentence);


        //connectionSocket.setKeepAlive(true);
        // connectionSocket.getKeepAlive();

        //}
        String[] returnString = new String[]{mailTilDb, passTilDb};
        return returnString;
    }

    public static String sendFromServer(String outToServer) throws IOException {

        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        outToServer = DBSearch.X ;
        //outToClient.writeBytes(outToServer);
        outToClient.writeBytes(outToServer  + " " + DBSearch.mailTilKlient+ '\n');

        //outToClient.writeChars(outToServer);

        return outToServer;
    }
}


