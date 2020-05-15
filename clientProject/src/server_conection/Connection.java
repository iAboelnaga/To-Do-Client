/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_conection;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aboelnaga
 */
public class Connection {
    
    private static final String IP = "127.0.0.1";
    private static final int PORT = 5005;
    private static Socket socket;

    public static Socket getSocketConnection() throws IOException {
        if (socket == null||socket.isClosed()) {
            synchronized (Connection.class) {

                socket = new Socket(IP, PORT);
            }
        }

        return socket;
    }
}
