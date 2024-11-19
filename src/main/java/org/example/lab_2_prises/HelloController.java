package org.example.lab_2_prises;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label label1;
    @FXML
    private CheckBox chek1;
    @FXML
    private CheckBox chek2;
    @FXML
    private CheckBox chek3;
    @FXML
    private CheckBox chek4;
    @FXML
    private Button button1;

    public Vector<Prize> pr;

    public HelloController() {
        pr = new Vector<>();
    }

    public class Prize {
        private String name;
        private Vector<String> prizes = new Vector<>();
        private Vector<Integer> count = new Vector<>();

        public String getName() {
            return name;
        }

        public Vector<String> getPrizes() {
            return new Vector<>(prizes);
        }

        public Vector<Integer> getCount() {
            return new Vector<>(count);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrizes(String prizes) {
            this.prizes.add(prizes);
        }

        public void setCount(int count) {
            this.count.add(count);
        }
    }

    public void DataFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filename);
        }
        Scanner scan = new Scanner(file);
        String regex = "^[а-яА-ЯёЁ]+$";

        while (scan.hasNextLine()) {
            Prize prize = new Prize();
            prize.setName(scan.nextLine());
            while (scan.hasNext()) {
                String t = scan.nextLine();
                String[] temp = t.split(" ");
                if(temp.length==1|| !scan.hasNext()) {
                    pr.add(prize);
                    prize = new Prize();
                    prize.setName(temp[0]);
                }else {
                    prize.setPrizes(temp[0]);
                    prize.setCount(Integer.parseInt(temp[1]));
                }
            }
        }
        Initio();
    }

    public void Initio() {
        if (pr.size() > 0) chek1.setText(pr.get(0).getName());
        if (pr.size() > 1) chek2.setText(pr.get(1).getName());
        if (pr.size() > 2) chek3.setText(pr.get(2).getName());
        if (pr.size() > 3) chek4.setText(pr.get(3).getName());
    }

    @FXML
    public void initialize() {
        // Здесь можно выполнять инициализацию, но в данном случае мы вызываем Initio в DataFromFile
    }
}