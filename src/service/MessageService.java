package service;

import com.google.gson.Gson;
import data.Message;
import data.ReturnType;
import data.User;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageService {
    public static ReturnType sendMessage(Message message, User user) {
        try (Socket s = new Socket(user.getAddress(), user.getPort())) {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(new Gson().toJson(message));
            oos.close();
            return ReturnType.SUCCESS;
        } catch (Exception ex) {
            return ReturnType.ERROR;
        }
    }
}
