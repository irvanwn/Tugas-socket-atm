import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient2 {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 270;

        try (
                Scanner scan = new Scanner(System.in);
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                BufferedReader baca = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter tulis = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String response;
            String pilihan;
            while ((response = baca.readLine()) != null) {
                System.out.println(response);
                if (response.equals("PinBenar")) {
                    do {
                        System.out.println("\n\nMenu:\n1. Check balance\n2. Withdraw\n3. Deposit\n4. Exit\nPIlihan");

                        pilihan = userInput.readLine();
                        tulis.println(pilihan);

                        response = baca.readLine();
                        System.out.println(response);
                        if (response.equals("Transaksi")) {
                            System.out.print("Masukan Jumlah uang : ");

                            pilihan = userInput.readLine();
                            tulis.println(pilihan);

                            response = baca.readLine();
                            System.out.println(response);

                            System.out.println("Lakukan transaksi Lain?");
                            String repeat = scan.nextLine();
                            if (!repeat.equalsIgnoreCase("Y")) {
                                tulis.println("4");
                                break;
                            }
                        } else if (response.equals("Saldo")) {
                            response = baca.readLine();
                            System.out.println(response);

                            System.out.println("Lakukan transaksi Lain? (y/n)");
                            String repeat = scan.nextLine();
                            if (!repeat.equalsIgnoreCase("Y")) {
                                tulis.println("4");
                                break;
                            }
                        }

                    } while (!pilihan.equals("4"));

                }

                String pin = userInput.readLine();
                tulis.println(pin);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}