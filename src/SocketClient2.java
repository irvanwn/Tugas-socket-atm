import java.io.*;
import java.net.*;

public class SocketClient2 {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 270;

        try (
                // Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                BufferedReader baca = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter tulis = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String response;
            String pilihan;
            while ((response = baca.readLine()) != null) {
                System.out.println(response);
                if (response.equals("Congrats! PIN Correct.")) {
                    do {
                        System.out.println("\n\nMenu:\n1. Check balance\n2. Withdraw\n3. Deposit\n4. Exit\nPIlihan");

                        pilihan = userInput.readLine();
                        tulis.println(pilihan);

                        response = baca.readLine();
                        System.out.println(response);
                        // System.out.println("\n Lakukan Transaksi Lagi? (y/n)");
                        // String repeat = scanner.nextLine();
                        // if (repeat.toLowerCase() == "n") {
                        // pilihan = "4";
                        // }
                    } while (!pilihan.equals("5"));
                    System.out.println("Terimakasih");
                }

                String pin = userInput.readLine();
                tulis.println(pin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}