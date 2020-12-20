package server;

import client.ClientGUI;
import client.TCPClient;
import common.Message;
import common.User;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer extends Thread {
    private int port ;
    private ServerSocket svSK;
    private JTextArea msgBox;
    public static ArrayList<User> listUser = new ArrayList<>();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public JTextArea getMsgBox() {
        return msgBox;
    }

    public void setMsgBox(JTextArea msgBox) {
        this.msgBox = msgBox;
    }


    public boolean open(){
        try {
            svSK = new ServerSocket(port);
            msgBox.append("Server is opened on port "+port+"\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void run() {
        while (true){
            Socket sk = null;
            DataInputStream cfUser=null;
            try {
                sk = svSK.accept();
                 cfUser = new DataInputStream(sk.getInputStream());
                String userName =cfUser.readUTF();
                User user = new User(userName,sk);
                listUser.add(user);
                msgBox.append("<Người dùng "+userName+" đã tham gia phòng chat>\n");
                ReadThread readThread = new ReadThread(user);
                readThread.start();
                DisconnectClientListener listener = new DisconnectClientListener(user);
                listener.start();

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }

    public void closeServer() {
        try {
            svSK.close();
            msgBox.append("Server is stopped!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TCPServer(int port , JTextArea ta) {
        this.port = port;
        this.msgBox=ta;
    }

}
class ReadThread extends Thread{
    private User user;

    public User getUser() {
        return user;
    }

    public ReadThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket sk =user.getSocket();
        try {
            while(true){
                ois = new ObjectInputStream(sk.getInputStream());
                Message ms = (Message) ois.readObject();
                ServerGUI.s.getMesBox().append(ms.getSender()+": "+ms.getContent()+"\n");
                for (User user: TCPServer.listUser
                     ) {
                    oos = new ObjectOutputStream(user.getSocket().getOutputStream());
                    oos.writeObject(ms);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class DisconnectClientListener extends Thread{
    private User user;

    public DisconnectClientListener(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        Socket sk = user.getSocket();
        try {
            DataInputStream dis = new DataInputStream(sk.getInputStream());
            int s= dis.readInt();
            if(s==0){
                TCPServer.listUser.remove(user);
                user.getSocket().close();
                ServerGUI.s.getMesBox().append("<Người dùng "+user.getName()+" đã rời phòng chat>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
