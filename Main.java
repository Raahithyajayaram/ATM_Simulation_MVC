import controller.ATMController;
import view.LoginScreen;

public class Main {
    public static void main(String[] args) {
        ATMController controller = new ATMController();
        new LoginScreen(controller).setVisible(true); // launch the login GUI
    }
}
