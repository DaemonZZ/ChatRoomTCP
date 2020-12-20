package server;

public class SeverRunner {
    public static void main(String[] args) {
        ServerGUI view = new ServerGUI();
        new ServerGuiController(view);
    }
}
