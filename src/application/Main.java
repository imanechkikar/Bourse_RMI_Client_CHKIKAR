package application;
	
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	  private Stage stage;
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        
	             try {
	            stage = primaryStage;
	            stage.setTitle("RMI Client");
	          
	            
	                ClientController acceuil = (ClientController) replaceSceneContent("Client.fxml");
	                acceuil.setApp(this);
	            
	            primaryStage.show();
	        } catch (Exception ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
  	    
	  private Initializable replaceSceneContent(String fxml) throws Exception {
	        FXMLLoader loader = new FXMLLoader();
	        InputStream in = Main.class.getResourceAsStream(fxml);
	        loader.setBuilderFactory(new JavaFXBuilderFactory());
	        loader.setLocation(Main.class.getResource(fxml));
	        AnchorPane page;
	        try {
	            page = (AnchorPane) loader.load(in);
	        } finally {
	            in.close();
	        } 
	        Scene scene = new Scene(page,836, 700);
	        stage.setScene(scene);
	        stage.sizeToScene();
	        return (Initializable) loader.getController();
	    }
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	
	
}
