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

import javafx.scene.input.MouseEvent;


public class Scene2con {
    //Tab1
    @FXML
	private TextField textfield;
	@FXML
	private Button search_button;
    @FXML
    private Label av;
    @FXML
    private Label med;

    @FXML 
    private TableView<Employee> TopE;
	@FXML
	private TableColumn<Employee, String> name;
	@FXML
	private TableColumn<Employee, Double> sal;

    @FXML 
    private TableView<Employee> BotE;
	@FXML
	private TableColumn<Employee, String> botname;
	@FXML
	private TableColumn<Employee, Double> botsal;

    private List<Employee> employees = new ArrayList();

    //Tab2
    @FXML
    private TextField transidfield;
    @FXML
    private TextField firstnamefield;
    @FXML
    private TextField lastnamefield;
    @FXML
    private Button searchbut;
    @FXML
    private Button searchbut2;

    @FXML 
    private TableView<Job> emptable;
	@FXML
	private TableColumn<Job, Double> salaryemp;
    @FXML
	private TableColumn<Job, String> titleemp;
	@FXML
	private TableColumn<Job, String> Depemp;


    @FXML
    public void search(ActionEvent event) throws IOException{


        TopE.getItems().clear();
        BotE.getItems().clear();

        this.employees = Scene1con.getEmplist();
        String jobtitle = this.textfield.getText();
        PayrollSearcher searched = new PayrollSearcher(this.employees);



        List<Employee> t = searched.topEarnersByPosition(jobtitle,5);
        
        List<Employee> empingrid = TopE.getItems();
        empingrid.addAll(t);

        PropertyValueFactory<Employee, String> name_getter = new PropertyValueFactory<>("FullName");
        PropertyValueFactory<Employee, Double> sal_getter = new PropertyValueFactory<>("TotalPay");
        name.setCellValueFactory(name_getter);
        sal.setCellValueFactory(sal_getter);


        List<Employee> b = searched.bottomEarnersByPosition(jobtitle,5);
        
        List<Employee> em = BotE.getItems();
        em.addAll(b);

        PropertyValueFactory<Employee, String> name_get = new PropertyValueFactory<>("FullName");
        PropertyValueFactory<Employee, Double> sal_get = new PropertyValueFactory<>("TotalPay");
        botname.setCellValueFactory(name_get);
        botsal.setCellValueFactory(sal_get);


        Double average = searched.avgSalaryForJob(jobtitle);
        av.setText("Average: " + average);

        Double median = searched.medianSalaryForPosition(jobtitle);
        med.setText("Median: " + median);

    }

    @FXML
    public void searchbyid(ActionEvent e) throws IOException{

        emptable.getItems().clear();
        firstnamefield.clear();
        lastnamefield.clear();


        this.employees = Scene1con.getEmplist();
        String idtext = this.transidfield.getText();
        PayrollSearcher searched = new PayrollSearcher(this.employees);

        List<Job> idd = searched.findJobsByID(idtext);

        List<Job> em = emptable.getItems();
        em.addAll(idd);

        PropertyValueFactory<Job, Double> salget = new PropertyValueFactory<>("Pay");
        PropertyValueFactory<Job, String> titleget = new PropertyValueFactory<>("Title");
        PropertyValueFactory<Job, String> depget = new PropertyValueFactory<>("Department");

        titleemp.setCellValueFactory(titleget);
        salaryemp.setCellValueFactory(salget);
        Depemp.setCellValueFactory(depget);

    }

    public void searchbyname(ActionEvent e) throws IOException{
        
        emptable.getItems().clear();
        transidfield.clear();


        this.employees = Scene1con.getEmplist();
        String idtext = this.transidfield.getText();
        PayrollSearcher searched = new PayrollSearcher(this.employees);

        String firstn = this.firstnamefield.getText();
        String lastn = this.lastnamefield.getText();
        List<Job> namesearch = searched.findJobsByName(firstn,lastn);
        
        List<Job> n = emptable.getItems();
        n.addAll(namesearch);

        PropertyValueFactory<Job, Double> salgett = new PropertyValueFactory<>("Pay");
        PropertyValueFactory<Job, String> titlegett = new PropertyValueFactory<>("Title");
        PropertyValueFactory<Job, String> depgett = new PropertyValueFactory<>("Department");

        titleemp.setCellValueFactory(titlegett);
        salaryemp.setCellValueFactory(salgett);
        Depemp.setCellValueFactory(depgett);
    }




    
}
