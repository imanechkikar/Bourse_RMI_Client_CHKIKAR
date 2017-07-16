package application;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import glsid.org.Entities.Ordre;
import glsid.org.Entities.Societe;
import glsid.org.Service.Rmi.BourseRmiRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController implements Initializable {

	
	private Main application;
	private Societe societe;
	BourseRmiRemote proxy;
	@FXML
	private Button  btnSearch;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private Label txtCode;
	
	@FXML
	private Label txtNom;
	
	@FXML
	private Label txtTotal;
	
	@FXML
	private TableView<Ordre> tableOrdre;
	@FXML
	private TableColumn<Ordre,Long> colId;
	@FXML
	private TableColumn<Ordre,Date> colDate;
	@FXML
	private TableColumn<Ordre,Long> colNb;
	@FXML
	private TableColumn<Ordre,Double> colPrix;
	
	private ObservableList<Ordre> listOrdres = FXCollections.observableArrayList();
	
	@FXML
	public void SearchSociete(ActionEvent event) throws RemoteException{
		
		String code=txtSearch.getText();
         societe=  proxy.getSociete(code); 
        // listOrdres
         List<Ordre> list= proxy.getOrders(code);
         listOrdres.addAll(list);
         
         System.out.println(societe);
         txtCode.setText(societe.getCodeSociete());
         txtNom.setText(societe.getNomSociete());
         
         double total= proxy.GetTotauxBySociete(code);       
         txtTotal.setText(String.valueOf(total));
		
	}
	
	
	
	
	
	
	
	
	
	public void setApp(Main application){
        this.application=application;
        colId.setCellValueFactory(new PropertyValueFactory<Ordre, Long>("idOrdre"));
        colDate.setCellValueFactory(new PropertyValueFactory<Ordre, Date>("dateOrd"));
        colNb.setCellValueFactory(new PropertyValueFactory<Ordre, Long>("nbAction"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Ordre, Double>("prixAction"));
       tableOrdre.setItems(listOrdres);
    }

    /**
     * Initializes the controller class.
     */
  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			proxy=(BourseRmiRemote) Naming.lookup("rmi://localhost:1099/BK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
