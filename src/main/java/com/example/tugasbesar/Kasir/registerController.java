package com.example.tugasbesar.Kasir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class registerController {
    public TextField tfUser;
    public ComboBox cbGender;
    public TextField tfUmur;
    public TextField tfNomor;
    public PasswordField pfPass;
    public PasswordField pfPasCon;

    public Button btnRegister;

    private Stage stage;
    private Parent root;

    File database = new File("C:\\Users\\super\\OneDrive\\Documents\\Intelij\\TugasBesar\\src\\main\\java\\com\\example\\tugasbesar\\databaseUser\\User.txt");
    ObservableList<String> jeniskelamin = FXCollections.observableArrayList("Laki-laki","Perempuan");
    public int Line;

    @FXML
    private void initialize(){

        cbGender.setItems(jeniskelamin);
    }

    public void CountLines(){
        Line=1;
        try {
            RandomAccessFile randomfile = new RandomAccessFile(database, "rw");
            for (int i = 0; randomfile.readLine()!=null; i++){
                Line++;
            }  System.out.println("Number of lines: "+ Line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void TambahData(String username, String password, String nomortlp, Object gender, String umur) {
        try {
            RandomAccessFile randomfile = new RandomAccessFile(database, "rw");

            for (int i = 0; i < Line; i++){
                randomfile.readLine();
            }

            randomfile.writeBytes(username);
            randomfile.writeBytes("," + gender);
            randomfile.writeBytes("," + nomortlp);
            randomfile.writeBytes("," + umur);
            randomfile.writeBytes("," + password +"\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void OnActionLogin(ActionEvent actionEvent) throws IOException{
        if(tfUser.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom username tidak boleh kososng");
            alert.show();
        }else if(cbGender.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom gender harus dipilih");
            alert.show();
        }else if(tfUmur.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom age tidak boleh kososng");
            alert.show();
        }else if(tfNomor.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom phone number tidak boleh kososng");
            alert.show();
        }else if(pfPass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom password tidak boleh kososng");
            alert.show();
//        }else if(!pfPasCon.getText().equals(pfPass.getText())) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION,"password tidak sama");
//            alert.show();
        } else {
            CountLines();
            TambahData(tfUser.getText(), pfPass.getText(), tfNomor.getText(), cbGender.getValue(), tfUmur.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginKasir.fxml")));
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Telah Berhasil Daftar");

            Scene scene = new Scene(root);
            stage = (Stage) btnRegister.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void valNomorTelepon(KeyEvent keyEvent){
        if (!tfNomor.getText().matches("^[0-9]+$")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"nomor telepon hanya boleh berisi angka");
            alert.show();
            tfNomor.deletePreviousChar();
        }
    }

    public  void valUmur(KeyEvent keyEvent){
        if (!tfUmur.getText().matches("^[0-9]+$")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Umur hanya boleh berisi angka");
            alert.show();
            tfUmur.deletePreviousChar();
        }
    }
}
