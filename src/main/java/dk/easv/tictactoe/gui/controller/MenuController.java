
package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;



public class MenuController {


    public void handleGameMode(ActionEvent event) throws IOException
    {
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        String title;
        if ("btnSingleplayer".equals(id))
            title = "TTT Singleplayer";
        else if ("btnMultiplayer".equals(id))
            title = "TTT Multiplayer";
        else
            return;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicTacView.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.show();
    }

    }

