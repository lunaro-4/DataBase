package com.example.dbtest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DbController {

    ObservableList<Liquer> liqList = FXCollections.observableArrayList();
    ObservableList<String> categoryList = FXCollections.observableArrayList();
    private AlcoCategory category= AlcoCategory.NonAlcohol;
    SQL sql = new SQL();

    @FXML
    private Label debugText;

    @FXML
    private ChoiceBox<String> categoryChoice;

    @FXML
    private TextField labelTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private TextField exposureTxt;

    @FXML
    private TextField strengthTxt;

    @FXML
    private TextField sugarTxt;


    @FXML
    private TableView<Liquer> mainTable;

    @FXML
    private TableColumn<Label, String> countryCol;

    @FXML
    private TableColumn<Label, String> categoryCol;

    @FXML
    private TableColumn<Label, Integer> exposureCol;

    @FXML
    private TableColumn<Label, String> labelCol;

    @FXML
    private TableColumn<Label, Integer> sugarCol;

    @FXML
    private TableColumn<Label, Float> strengthCol;


    @FXML
    protected void dbFetch() {
//        onetouch();
            liqList.clear();
        try {
            liqList.addAll(sql.loadLiquer());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void dbPush(){
        try {
            if (sql.pushToDb(
                    labelTxt.getText(),
                    countryTxt.getText(),
                    categoryChoice.getValue(),
                    Integer.parseInt(exposureTxt.getText()),
                    Float.parseFloat(strengthTxt.getText()),
                    Integer.parseInt(sugarTxt.getText())))
                debugText.setText("Push Successful!");
            else
                debugText.setText("Push Failed!");

        } catch (SQLException e) {
            debugText.setText(e.getMessage());
        }
    }

    @FXML
    protected void initialize(){
        choiceInit();
        tableInit();
    }

    private void onetouch() {
        String url = "jdbc:postgresql://10.10.104.136:5432/Geometry?user=postgres&password=123";
        try {
            Connection connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void choiceInit(){
        for (AlcoCategory ac:
             category.getDeclaringClass().getEnumConstants()) {
           categoryList.add(ac.name());
        }
        categoryChoice.setItems(categoryList);
    }



    private void tableInit(){
        mainTable.setItems(liqList);
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        exposureCol.setCellValueFactory(new PropertyValueFactory<>("exposure"));
        sugarCol.setCellValueFactory(new PropertyValueFactory<>("sugar"));
        strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
    }


}