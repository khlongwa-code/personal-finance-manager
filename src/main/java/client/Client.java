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
            

            while (true) {
                String userInput = null;
                if (!loggedIn) {
                    System.out.println("Enter 'Login' or 'register' to continue...");
                    userInput = scanner.nextLine();

                    if (userInput.equalsIgnoreCase("login") || userInput.equalsIgnoreCase("register")) {
                        String request = inputs.handleUserInputs(userInput);
                        writer.println(request);
                        String serverResponse = reader.readLine();
                        System.out.println(serverResponse);

                        loggedIn = true;
                    } else {
                        continue;
                    }
                }

                System.out.println("Enter your request: ");
                userInput = scanner.nextLine();
                
                String request = inputs.handleUserInputs(userInput);
                writer.println(request);
                String serverResponse = reader.readLine();
                System.out.println(serverResponse);
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}