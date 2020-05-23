import Exceptii.UsernameSauParolaGresite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class PaginaLogIn extends PaginaInregistrare{

    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;


    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PaginaAutentificare.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            // Hide this current window (if this is what you want
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionare(ActionEvent actionEvent) throws UsernameSauParolaGresite {
        try {
            if(verificareUtilizator()==1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("PaginaClient.fxml"));
                AnchorPane paginaA = (AnchorPane) loader.load();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else {
                throw new UsernameSauParolaGresite();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int  verificareUtilizator() {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/user.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Username:").equals(username.getText()) && obiect.get("Parola:").equals(parola.getText())) return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
