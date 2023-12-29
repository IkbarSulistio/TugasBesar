package com.example.tugasbesar.Kasir;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class LoginController {
    public PasswordField fieldPass;
    public TextField fieldUser;
    public Button btnLogin;

    public Label goRegister;

    private Stage stage;
    private Parent root;
    File database = new File("C:\\Users\\super\\OneDrive\\Documents\\Intelij\\TugasBesar\\src\\main\\java\\com\\example\\tugasbesar\\databaseUser\\User.txt");

    public int line;

    public void Lines(){
        line = 1;
        try{
            RandomAccessFile randomfile = new RandomAccessFile(database, "rw");

            for (int i = 0; randomfile.readLine()!=null; i++){
                line++;
            }

            System.out.println("Number of lines: " + line);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void moveLogin() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InterfaceBarang.fxml")));

        Scene scene = new Scene(root);

        stage = (Stage) goRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void login(String username, String password){
        boolean ceklgn = false;
        try {
            RandomAccessFile randomfile = new RandomAccessFile(database, "rw");
            System.out.println(line);
            String Ln;
            while ((Ln = randomfile.readLine()) != null) {
                String[] dtbs = Ln.split(",");
                if (username.equals(dtbs[0].trim()) && password.equals(dtbs[4].trim())) {
                    moveLogin();
                    ceklgn = true;
                    break;
                }
            }
        }catch (FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File tidak ditemukan");
            alert.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        if (ceklgn != true){
            fieldPass.clear();
            fieldUser.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username atau Password salah atau tidak terdaftar");
            alert.show();
        }
    }

    public void  onActionLogin(ActionEvent actionEvent) throws IOException{

        Lines();
        login(fieldUser.getText(), fieldPass.getText());

        if (fieldUser.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom username tidak boleh kososng");
            alert.show();
        } else if (fieldPass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kolom password tidak boleh kososng");
            alert.show();
        }
    }

    public void onRegister(MouseEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));

        Scene scene = new Scene(root);
        stage = (Stage) goRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

