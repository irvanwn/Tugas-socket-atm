import java.io.*;
import java.net.*;

public class SocketServer2 {
    public static void main(String[] args) {
        final int PORT = 270;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client connection...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new CheckPin(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Koneksi Errror");
        }
    }
}

class CheckPin implements Runnable {
    private Socket clientSocket;

    public CheckPin(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader baca = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter tulis = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String correctPin = "1234";
            String pin;
            String pilihanServer;

            do {
                tulis.println("Enter PIN:");
                pin = baca.readLine();
            } while (!pin.equals(correctPin));
            tulis.println("Congrats! PIN Correct.");

            while (!(pilihanServer = baca.readLine()).equals("5")) {
                System.out.println(pilihanServer);
                Integer pilihanInt = Integer.parseInt(pilihanServer);
                System.out.println(pilihanInt);
                switch (pilihanInt) {
                    // case 1:
                    // System.out.println("menu 0");
                    // tulis.println("menu 0");
                    // break;
                    case 1:
                        System.out.println("menu 1");
                        tulis.println("menu 1");
                        break;
                    case 2:
                        System.out.println("menu 2");
                        tulis.println("menu 2");
                        break;
                    case 3:
                        System.out.println("menu 3");
                        tulis.println("menu 3");
                        break;
                    case 4:
                        System.out.println("menu 4");
                        tulis.println("menu 4");
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
