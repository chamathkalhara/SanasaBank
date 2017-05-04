/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.other;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Kalhara
 */
public class Validations {

    static String text;
    private static int count;
    private static int size;

    public static void nicNumberValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() > 9) {
            String newText = text.substring(0, 9);
            textField.setText(newText);

        }
    }

    public static void balanceValidation(JTextField textField) {
        String value = textField.getText();
        int length = value.length();
        char charAt=0;
        if(length!=0){
            charAt = value.charAt(length-1);
        }
        String temp = String.valueOf(charAt);
        if (charAt != '.' && !Character.isDigit(charAt)) {
            text = value.replaceAll("[\\D]", "");
            textField.setText(text);

        }

        if (charAt == '.') {
            Validations.count = 1;
            Validations.size = value.length();
        }
        if (Validations.count == 1 && length >= Validations.size + 2) {
            String newText = String.format("%.2f", Double.parseDouble(value));
            textField.setText(newText);
        }
    }

    public static void tpNumberValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() >= 10) {
            String newText = text.substring(0, 10);
            String codeNum = newText.substring(0, 3);
            String restNum = newText.substring(3);
            textField.setText(codeNum + "-" + restNum);
        }
    }

//    public static void passwordValidation(JPasswordField passwordField) {
//        char[] password = passwordField.getPassword();
//        text = password.toString();
//        if (text.length() > 9) {
//            String newText = text.substring(0, 9);
//            passwordField.setText(newText);
//
//        }
//    }

    public static void numberFormat(JLabel label) {
        text = label.getText();
        String newText = String.format("%.2f", Double.parseDouble(text));
        label.setText(newText);
    }
    
    public static void numberFormat2(JTextField textField) {
        text = textField.getText();
        String newText = String.format("%.2f", Double.parseDouble(text));
        textField.setText(newText);
    }

    public static void accountNumberValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() > 5) {
            String newText = text.substring(0, 5);
            textField.setText(newText);
        }
    }

    public static void rateValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() > 2) {
            String newText = text.substring(0, 2);
            textField.setText(newText);

        }
    }
    
    public static void noteCountValidation(JTextField textField) {
        text = textField.getText().replaceAll("[\\D]", "");
        textField.setText(text);
        if (text.length() > 3) {
            String newText = text.substring(0, 3);
            textField.setText(newText);

        }
    }
}
