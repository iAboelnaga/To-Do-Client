/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import javafx.application.Platform;
import org.json.JSONException;
import org.json.JSONObject;
import serverEntity.User;
import serverGUI.ToDoServer;

/**
 *
 * @author Aboelnaga
 */
public class Client {
    private int id;
    private String clientName;
    private BufferedReader in;
    private PrintStream ps;
    private Socket s;
    private static Vector<Client> clientVector = new Vector<>();
    
    
    public Client(int id, String clientName, RequesManager httpRequestHandler) {
        this.id = id;
        this.clientName = clientName;
        this.s = httpRequestHandler.getS();
        this.in = httpRequestHandler.getBufferReader();
        this.ps = httpRequestHandler.getPrintStream();
    }
    public Client(int id, String clientName) {
        this.id = id;
        this.clientName = clientName;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getClientName() {
        return clientName;
    }
    
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public static Vector<Client> getclientVector() {
        return clientVector;
    }
public BufferedReader getIn() {
        return in;
    }

    public PrintStream getPs() {
        return ps;
    }

    public Socket getS() {
        return s;
    }
    /*
    public static void notifyCollaborator(ArrayList<Notifications> notifications) {
        
        //TODO
    }*/
    
    public static ArrayList<User> getOnlineUser(ArrayList<User> friends) {
        ArrayList<User> online = new ArrayList<>();
        clientVector.forEach((client) -> {
            friends.forEach((frined) -> {
                if (frined.getId() == client.id) {
                    online.add(new User(client.id, client.clientName));
                }
                
            });
        });
        return online;
        
    }
      public static boolean isInVector(int id) {
        for (Client client : clientVector) {
            if (client.getId() == id) {
                return true;
            }
        }
        return false;

    }
    /*
    private static String toNotifcationJson(Notifications notification) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(notification);
        
    }*/

    private static String toUserJson(User user) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(user);
        
    }
    /*
      private static String toTODOJson(ToDoList list) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(list);
       
    }*/

    /*
    public static void notify(Notifications notification) {
        
        for (Client client : clientVector) {
            if (client.getId() == notification.getToUserId()) {
                client.ps.println(REQUEST.NOTIFICATION);
                System.out.println("notify: " + notification.getToUserName());
                client.ps.println(toNotifcationJson(notification));
                // to notifiy user end of data
                client.ps.println(REQUEST.END);
            }
        }
    }*/
/*
    public static void addCollabortor(int userId, int todoId, ArrayList<User> allColl) {
        
        for (Client client : clientVector) {
            for (User collId : allColl) {    
              if(client.getId() != userId)  {
                if (client.getId() == collId.getId()) {
                    client.ps.println(REQUEST.NEWCOLLABORATOR);
                    System.out.println("addCOll id: " + collId.getId());
                    client.ps.println(toUserJson(collId));
                    // to notifiy user end of data
                    client.ps.println(REQUEST.END);
                }
              }
            }  
        }
    }*/
    /*
    public static void addSharedListToNewCollabortor(int userId, ToDoList list) {
        
        for (Client client : clientVector) {   
              if(client.getId() == userId)  {       
                    client.ps.println(REQUEST.SHAREDTODO);
                    System.out.println("addSharedList id: " + list.getId());
                    client.ps.println(toTODOJson(list));
                    // to notifiy user end of data
                    client.ps.println(REQUEST.END);
                }
        }
    }*/
/*
    public static void addTask(int userId, int todoId, ArrayList<Integer> allColl) {
        
        for (Client client : clientVector) {
            for (int collId : allColl) {                
                if (client.getId() != collId) {
                    client.ps.println(REQUEST.TODO);
                    System.out.println("addCOll id: " + collId);
               // client.ps.println(toNotifcationJson(notification));
                    // to notifiy user end of data
                    client.ps.println(REQUEST.END);
                }
            }
        }
    }*/
/*
    public static void addFriend(User userObj, User friendObj) {
//
//        for (Client client : clientVector) {
//            if (client.getId() == ) {
//                client.ps.println(REQUEST.NOTIFICATION);
//                System.out.println("notify: "+ notification.getToUserName());
//                client.ps.println(toNotifcationJson(notification));
//                // to notifiy user end of data
//                client.ps.println(REQUEST.END);
//            }
//        }
    }*/

    
    public static void removeClient(int userId) {
        for (int i = 0; i < clientVector.size(); i++) {
            if (clientVector.get(i).getId() == userId) {
                clientVector.remove(i);
            }
        }
    }
    
    public static void addClient(Client client) {
        if (client != null) {
            clientVector.add(client);
        }
        Platform.runLater(() -> {
            //ToDoServer.controller.onlineUsers_id.setText(clientVector.size() + "");
        });
    }
    /*
    public static void notifiyFriends(User user, ArrayList<User> friends, String friendStatus) throws JSONException {
        //friend status (REQUEST.ONLINE - REQUEST.OFFLINE)
        JSONObject userAsJson = user.getUserAsJson();

        System.out.println(userAsJson.getInt("ID"));
        for (User u : friends) {
            for (int i = 0; i < clientVector.size(); i++) {
                Client client = clientVector.get(i);
                if (client.getId() == u.getId()) {
                    client.ps.println(friendStatus);
                    client.ps.println(userAsJson);
                    client.ps.println(REQUEST.END);
                }
            }
        }
    }*/
    /*
    public static void notifyUsetWithFriendRequest(Notifications notification) {
        for (Client client : clientVector) {
            if (client.getId() == notification.getToUserId()) {
                client.ps.println(REQUEST.NOTIFICATION);
                client.ps.println(toNotifcationJson(notification));
                client.ps.println(REQUEST.END);
           }
        }
    }*/
    
    public static Client getClientById(int id) {
        for (Client client : clientVector) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
}
