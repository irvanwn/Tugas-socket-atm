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
    private FunctionOpt functionOpt;

    public CheckPin(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.functionOpt = new FunctionOpt();
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
            tulis.println("PinBenar");

            while (!(pilihanServer = baca.readLine()).equals("4")) {
                Integer nominal;
                System.out.println(pilihanServer);
                Integer pilihanInt = Integer.parseInt(pilihanServer);
                System.out.println(pilihanInt);
                switch (pilihanInt) {
                    // case 1:
                    // System.out.println("menu 0");
                    // tulis.println("menu 0");
                    // break;
                    case 1:
                        System.out.println("menu Cek Saldo");
                        tulis.println("Saldo");
                        tulis.println("Saldo anda : " + functionOpt.cekSaldo());

                        break;
                    case 2:
                        tulis.println("Transaksi");
                        pilihanServer = baca.readLine();
                        nominal = Integer.parseInt(pilihanServer);
                        Double sisaSaldo = functionOpt.TarikTunai(nominal);
                        if (sisaSaldo < 0) {
                            tulis.println("Saldo Tidak cukup, anda miskin");
                        } else {
                            tulis.println("Sisa Saldo anda : " + sisaSaldo);
                        }
                        break;
                    case 3:
                        tulis.println("Transaksi");
                        pilihanServer = baca.readLine();
                        nominal = Integer.parseInt(pilihanServer);
                        tulis.println("Saldo anda : " + functionOpt.SetorTunai(nominal));
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
