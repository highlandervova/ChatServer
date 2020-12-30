package main;

import com.google.gson.Gson;
import data.Message;
import data.User;
import service.MessageService;
import user.UserHolder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message m = new Gson().fromJson((String) ois.readObject(), Message.class);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            if (m.getMessage().startsWith("reg")) {
                if (UserHolder.getRegisteredUsers().containsKey(m.getNameFrom().toLowerCase())) {
                    oos.writeObject("Already registered;");
                } else {
                    UserHolder.getRegisteredUsers().put(
                            m.getNameFrom().toLowerCase(),
                            new User(
                                    m.getMessage().substring(m.getMessage().indexOf(' ')+1, m.getMessage().lastIndexOf(' ')),
                                    Integer.valueOf(m.getMessage().substring(m.getMessage().lastIndexOf(' ')+1)),
                                    m.getNameFrom().toLowerCase()));
                    oos.writeObject("Success!!!");
                }
            } else {
                if (UserHolder.getRegisteredUsers().containsKey(m.getNameFrom().toLowerCase())) {
                    if (!m.getNameTo().isEmpty()) {
                        if (UserHolder.getRegisteredUsers().containsKey(m.getNameTo().toLowerCase())) {
                            //sending private message
                            MessageService.sendMessage(m, UserHolder.getRegisteredUsers().get(m.getNameTo().toLowerCase()));
                            oos.writeObject("Success");
                        } else {
                            oos.writeObject("User not registered;");
                        }
                    } else {
                        //sending public message to all users
                        for (User u : UserHolder.getRegisteredUsers().values()) {
                            MessageService.sendMessage(m, u);
                        }
                    }
                    oos.writeObject("Message sent;");
                } else {
                    oos.writeObject("Please register first;");
                }
            }
        }
    }
}
