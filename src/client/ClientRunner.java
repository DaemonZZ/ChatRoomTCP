package client;

public class ClientRunner {
    public static void main(String[] args) {
        ClientGUI view = new ClientGUI();
        new ClientGuiController(view);
    }
}
