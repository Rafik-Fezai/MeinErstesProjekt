import Controller_Package.Controller;
import Model_Package.Model;
import View_Package.Login_View;


public class HauptProgramm {

//Start der Anwendung
    public static void main(String[] args) {

                Login_View loginView = new Login_View();
                Model model = new Model();
                Controller controller = new Controller(loginView,model);

    }
}
