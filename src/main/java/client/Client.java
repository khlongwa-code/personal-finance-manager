package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) {
        String host = "localhost";
        int portNumber = 5000;
        Scanner scanner = new Scanner(System.in);
        UserInputs inputs = new UserInputs();
        boolean loggedIn = false;


        try (Socket socket = new Socket(host, portNumber)) {
            System.out.println("Connected to the server");

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            do {
                System.out.println("Login or register to continue...");
                String userInput = scanner.nextLine();

                String request = inputs.handleLoginRegister(userInput);
                writer.println(request);
                String serverResponse = reader.readLine();

                if (serverResponse.contains("OK")) {
                    loggedIn = true;
                }

                System.out.println(serverResponse);

            } while (!loggedIn);

            while (true) {
                System.out.println("What do you want to do?: ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("login") || userInput.equalsIgnoreCase("register")) {
                    System.out.println("Forbiddin action, logout first!");
                    continue;
                }

                String request = inputs.handleUSerActions(userInput);

                writer.println(request);
                String serverResponse = reader.readLine();
                System.out.println(serverResponse);
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}