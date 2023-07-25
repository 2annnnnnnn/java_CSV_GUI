import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.scene.Parent;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class Scene1con {
    
    @FXML
	private TextField file_textfield;
	@FXML
	private Button load_button;
	@FXML
	private Button browse_button;


    private Stage stage;
	private Scene scene;
	private Parent root;

    private File selected_file; 

	private static List<Employee> emplist = new ArrayList();

    public void changeScene(ActionEvent event) throws IOException{


    String filepath = this.file_textfield.getText();
       PayrollLoader x = new PayrollLoader(filepath);
       List<Employee> employees = x.load();
	   emplist = null;
	   emplist = employees;


        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
        
        
    }

    public void browse_for_file(ActionEvent e) {

        FileChooser file_chooser = new FileChooser();

		Stage main_stage = getStage();

        if ( this.selected_file != null ) {
        file_chooser.setInitialDirectory(this.selected_file.getParentFile());
        }

        File dialog_result = file_chooser.showOpenDialog(main_stage);

         if ( dialog_result != null ) {
        	 this.selected_file = dialog_result;
        	 this.file_textfield.setText(this.selected_file.getAbsolutePath());
     	}
	}

	public Stage getStage() {
		return (Stage) load_button.getScene().getWindow();
	}

	public static List<Employee> getEmplist(){
		return Scene1con.emplist;
	}

}
