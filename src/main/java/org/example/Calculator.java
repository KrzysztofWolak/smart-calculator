package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JLabel screen;
    private boolean start;
    private String lastCommand;
    private double result;


    public Calculator() {
        JFrame calcFrame = new JFrame();

        start = true;
        lastCommand = "=";
        screen = new JLabel("0",JLabel.RIGHT);
        screen.setFont(new Font("arial", Font.BOLD, 40));

        JPanel keyPad = new JPanel();
        keyPad.setLayout(new GridLayout(5,4));
        String buttons[]= {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+" };

        for(int i = 0; i< buttons.length;i++){
            keyPad.add(addButton(buttons[i]));
        }

        JButton clear = new JButton("CLEAR");
        clear.addActionListener(calcListener);
        keyPad.add(clear);



        calcFrame.add(screen,BorderLayout.NORTH);
        calcFrame.add(keyPad, BorderLayout.CENTER);
        calcFrame.setLocationRelativeTo(null);
        calcFrame.setSize(380,450);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setVisible(true);
    }



    public  JButton addButton(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("arial", Font.ITALIC, 20));
        button.addActionListener(calcListener);
        return button;
    }
    public void insertNumber(String str) {
        if(start) {
            screen.setText("");
            screen.setText(screen.getText() + str);
            start = false;
        } else {
            screen.setText(screen.getText() + str);
        }
    }

    public void setScreen() {
        screen.setText("0");
    }

    public void calculate(String str) {
        System.out.println("calculate" + str);
        double num = Double.parseDouble(screen.getText());
        if (lastCommand.equals("=")) {
            result = num;
            screen.setText(str);
        }
        if (lastCommand.equals("+")) result += num;
        if (lastCommand.equals("-")) result -= num;
        if (lastCommand.equals("*")) result *= num;
        if (lastCommand.equals("/")) result /= num;
        if(str.equals("=")) screen.setText("" + result);
        lastCommand = str;
        start = true;


    }

    ActionListener calcListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton) e.getSource()).getText();
            System.out.println(str);
            if ("/*+-=".indexOf(str) >= 0) {
                calculate(str);
            } else if (((JButton) e.getSource()).getText().indexOf("CLEAR") >=0 ) setScreen();
            else {
                insertNumber(str);
            }
        }

    } ;
}