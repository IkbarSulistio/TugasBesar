package com.example.tugasbesar.Barang;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class BarangMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public class Barang{
        private final StringProperty id;
        private final StringProperty nama;
        private final StringProperty harga;
        private final StringProperty jumlah;

        public Barang (String id, String nama, String jumlah, StringProperty harga){
            this.id = new SimpleStringProperty(id);
            this.nama = new SimpleStringProperty(nama);
            this.harga = harga;
            this.jumlah = new SimpleStringProperty(jumlah);
        }

        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public StringProperty idProperty() {
            return id;
        }

        public String getNama() {
            return nama.get();
        }

        public void setNama(String nama) {
            this.nama.set(nama);
        }

        public StringProperty namaProperty() {
            return nama;
        }

        public String getJumlah() {
            return jumlah.get();
        }

        public void setJumlah(String jumlah) {
            this.jumlah.set(jumlah);
        }

        public StringProperty jumlahProperty() {
            return jumlah;
        }
    }
}
