package ftp;

public class FTPRunner {
    public FTPRunner() {
    }

    public static void main(String[] args) {
        FtpGUI view  = new FtpGUI();
        new FtpController(view);
    }
}
