package com.example.tugasbesar.Kasir;

import com.example.tugasbesar.Barang.BarangMain;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.nio.channels.spi.AbstractSelectionKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarangController {
    public TableView tvDaftar;
    public TextField tfID;
    public TextField tfNama;
    public  TextField tfHarga;
    public  TextField tfCariId;
    public Button btnCari;
    public Button btnTambah;
    public Button btnEdit;
    public Button btnHapus;
    public Button btnKeluar;

    private ObservableList<daftarBarang> listBarang = FXCollections.observableArrayList();
    Random random = new Random();
    @FXML
    private void initialize() {
        TableColumn idBarang = new TableColumn("ID Barang");
        TableColumn namaBrg = new TableColumn("Nama Barang");
        TableColumn HrgBrg = new TableColumn("Harga Barang");

        tvDaftar.getColumns().addAll(idBarang, namaBrg, HrgBrg);
        tvDaftar.setItems((listBarang));

        idBarang.setCellValueFactory(new PropertyValueFactory<>("idBarang"));
        namaBrg.setCellValueFactory(new PropertyValueFactory<>("namaBrg"));
        HrgBrg.setCellValueFactory(new PropertyValueFactory<>("HrgBrg"));
    }

    public void OnActionTambah (ActionEvent actionEvent){
        if (tfID.getText()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kolom ID harus diisi");
            alert.show();
        }else if (tfNama.getText()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kolom Nama harus diisi");
            alert.show();
        } else if (tfHarga.getText()==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kolom harga harus diisi");
            alert.show();
        }else {
            String idBarang = (String) tfID.getText();
            String namaBrg = (String) tfNama.getText();
            String HrgBrg = (String) tfHarga.getText();

            listBarang.add(new daftarBarang(idBarang, namaBrg, HrgBrg));

            clear();

        }
    }

    public void OnActionKeluar (ActionEvent actionEvent){
        System.exit(0);
    }

    public void clear(){
        tfID.clear();
        tfNama.clear();
        tfHarga.clear();
    }

   public void OnActionEdit (ActionEvent actionEvent){
        daftarBarang Selectedbarang = (daftarBarang) tvDaftar.getSelectionModel().getSelectedItem();

        if (Selectedbarang != null){
            tfID.setText(Selectedbarang.getIdBarang());
            tfNama.setText(Selectedbarang.getNamaBrg());
            tfHarga.setText(Selectedbarang.getHrgBrg());

            Selectedbarang.setIdBarang(tfID.getText());
            Selectedbarang.setNamaBrg(tfNama.getText());
            Selectedbarang.setHrgBrg(tfHarga.getText());

            tvDaftar.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Pilih Item untuk di edit");
            alert.show();
        }
   }
    public void OnActionHapus (ActionEvent actionEvent) {
        int selectedItem = tvDaftar.getSelectionModel().getSelectedIndex();
        tvDaftar.getItems().remove(selectedItem);
    }

    public void valId(KeyEvent keyEvent){
        if(!tfID.getText().matches("^[0-9]+$")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input ID tidak boleh huruf dan simbol");
            alert.show();
            tfID.deletePreviousChar();
        }
    }

    public void valHarga(KeyEvent keyEvent) {
        if (!tfHarga.getText().matches("^[0-9]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input Harga tidak boleh huruf dan simbol");
            alert.show();
            tfHarga.deletePreviousChar();
        }
    }

    public void valNama (KeyEvent keyEvent) {
        if (!tfNama.getText().matches("^[a-zA-Z' ]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nama tidak boleh input angka dan simbol");
            alert.show();
            tfNama.deletePreviousChar();
        }
    }

    public void OnActionCari(ActionEvent actionEvent) {
        String idToSearch = tfCariId.getText();

        ObservableList<daftarBarang> filteredList = FXCollections.observableArrayList();

        for (daftarBarang barang : listBarang) {
            if (barang.getIdBarang().equals(idToSearch)) {
                filteredList.add(barang);
            }
        }

        tvDaftar.getItems().clear();
        tvDaftar.setItems(filteredList);
        
        if (filteredList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No item found with the specified ID.");
            alert.show();
        }
    }

    public static class daftarBarang{
        private final SimpleStringProperty idBarang, namaBrg, HrgBrg;
        public daftarBarang(String idBarang, String namaBrg, String HrgBrg){
            this.idBarang = new SimpleStringProperty(idBarang);
            this.namaBrg = new SimpleStringProperty(namaBrg);
            this.HrgBrg = new SimpleStringProperty(HrgBrg);
        }
        public String getIdBarang() {

            return idBarang.get();
        }

        public SimpleStringProperty idBarangProperty() {

            return idBarang;
        }

        public void setIdBarang(String idBarang) {

            this.idBarang.set(idBarang);
        }

        public String getNamaBrg() {

            return namaBrg.get();
        }

        public SimpleStringProperty namaBrgProperty() {

            return namaBrg;
        }

        public void setNamaBrg(String namaBrg) {

            this.namaBrg.set(namaBrg);
        }

        public String getHrgBrg() {

            return HrgBrg.get();
        }

        public SimpleStringProperty hrgBrgProperty() {

            return HrgBrg;
        }

        public void setHrgBrg(String hrgBrg) {

            this.HrgBrg.set(hrgBrg);
        }


    }

}

