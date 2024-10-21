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

                userAction = new JSONObject(clientMessage).getString("action");

                if (userAction.equals("login") || userAction.equals("register")) {
                    action = Actions.create(clientMessage);
                    response = handleResponse(manager, dai, clientMessage);
                    userEmail = extractUserEmail(clientMessage);
                    
                    writer.println(response);
                    break;
                } else {
                    writer.println(Response.error("ERROR", "Login or signup to continue"));
                }
            }

            while ((clientMessage = reader.readLine()) != null) {
                System.out.println(">>> Received: " + clientMessage);

                userAction = new JSONObject(clientMessage).getString("action");

                if (userEmail != null && userAction.equals("login")) {
                    writer.println(Response.login("ERROR", "You're already logged in!"));
                    continue;
                }

                if (userEmail != null && userAction.equals("register")) {
                    writer.println(Response.login("ERROR", "Logout to create a new account"));
                    continue;
                }

                action = Actions.create(clientMessage);
                response = handleResponse(manager, dai, clientMessage);
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected!");
        }
    }

    private String handleResponse(DataManager manager, DataAccessInterface dai,
                                  String clientMessage) {
        return action.execute(manager, dai, clientMessage);
    }

    private String extractUserEmail(String clientMessage) {

        return new JSONObject(clientMessage).getJSONObject("data").getString("email");
    }
}