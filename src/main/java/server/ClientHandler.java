package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


import actions.client.Actions;
import communication.Response;
import database.DataAccessInterface;
import database.DataManager;
import kong.unirest.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class ClientHandler extends Thread{
    private static Set<String> loggedInUsers = new HashSet<>();

    private Socket socket;
    private DataManager manager;
    private DataAccessInterface dai;
    private Actions action;
    private String userEmail;
    private String response;
    private String userAction;
    private String clientMessage;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true)
        ) {
            manager = new DataManager();
            dai = manager.getDAI();

            userEmail = null;
            response = null;
            userAction = null;

            while ((clientMessage = reader.readLine()) != null) {
                System.out.println(">>> Received: " + clientMessage);

                userEmail = extractUserEmail(clientMessage);

                String loginResponse = handleLoginRegister(clientMessage, userEmail);

                if (loginResponse != null) {
                    System.out.println("<< Response: " + loginResponse);
                    writer.println(loginResponse);
                    continue;
                }

                action = Actions.create(clientMessage);
                String response = handleResponse(manager, dai, clientMessage);
                System.out.println("<< Response: " + response);
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected!");
        } finally {
            try {
                socket.close();
                if (userEmail != null) {
                    loggedInUsers.remove(userEmail);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleResponse(DataManager manager, DataAccessInterface dai,
                                  String clientMsg) {
        if (!loggedInUsers.contains(userEmail)) {
            return Response.login("ERROR", "Login or signup to continue...");
        }
        return action.execute(manager, dai, clientMsg);
    }

    private String extractUserEmail(String clientMsg) {
        return new JSONObject(clientMsg).getJSONObject("data").getString("email");
    }

    private String handleLoginRegister(String clientMsg, String email) {
        String request = new JSONObject(clientMsg).getString("action");
        boolean login = request.equalsIgnoreCase("login");
        boolean register = request.equalsIgnoreCase("register");

        if (login || register) {
            boolean success = proccessLoginOrRegister(login, register, email);

            if (!success && login) {
                return Response.login("ERROR", "Login failed!");
            }

            if  (!success && register) {
                return Response.register("ERROR", "Registration failed! " + register);
            }
            loggedInUsers.add(email);
            return null;
        }
        return null;
    }

    private boolean proccessLoginOrRegister(boolean login, boolean register, String email) {

        if (loggedInUsers.isEmpty() && (login || register)) {
            return true;
        }
        return false;
    }
}