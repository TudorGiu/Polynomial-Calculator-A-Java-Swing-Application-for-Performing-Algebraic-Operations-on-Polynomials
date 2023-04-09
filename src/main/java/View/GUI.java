package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class    GUI extends javax.swing.JFrame {

    class JPanelGradient extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(255, 11, 99);
            Color color2 = new Color(250, 88, 60);
            GradientPaint gp = new GradientPaint(0,0,color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    // custom button class
    class MyJButton extends JButton{
        public MyJButton(String s){
            super(s);
            this.setBackground(new Color(98,54,255));
            this.setFont(new Font("Calibri", Font.BOLD, 25));
            this.setForeground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        }
    }


    public static void main(String args[]){
        new GUI();
    }

    private GUI(){
        JFrame mainFrame = new JFrame();

        ImageIcon icon = new ImageIcon("D:\\PT\\PT2023_30424_Giuroiu_Tudor_1\\polynomialCalculator\\logo.png");
        mainFrame.setIconImage(icon.getImage());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Polynomial calculator");

        mainFrame.setSize(700,700);
        mainFrame.setLocationRelativeTo(null);

        JPanelGradient mainPanel = new JPanelGradient();
        mainPanel.setBorder(new EmptyBorder(10,30,10,30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 2));

        JLabel firstPolyText = new JLabel("Enter the first polynomial:",SwingConstants.CENTER);
        firstPolyText.setFont(new Font("Calibri", Font.PLAIN, 20));
        firstPolyText.setPreferredSize(new Dimension(10,1));
        upperPanel.add(firstPolyText);

        JLabel secondPolyText = new JLabel("Enter the 2nd polynomial:", SwingConstants.CENTER);
        secondPolyText.setFont(new Font("Calibri", Font.PLAIN, 20));
        secondPolyText.setPreferredSize(new Dimension(10,1));
        upperPanel.add(secondPolyText);


        JTextField firstPolyTextField = new JTextField();
        upperPanel.add(firstPolyTextField);

        JTextField secondPolyTextField = new JTextField();
        upperPanel.add(secondPolyTextField);

        mainPanel.add(upperPanel);



        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 1));

        JLabel outputText = new JLabel("Output:", SwingConstants.CENTER);
        outputText.setFont(new Font("Calibri", Font.BOLD, 60));
        middlePanel.add(outputText);

        JLabel realOutput = new JLabel("-", SwingConstants.CENTER);
        realOutput.setFont(new Font("Calibri", Font.BOLD, 20));
        middlePanel.add(realOutput);

        mainPanel.add(middlePanel);



        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 2));

        MyJButton additionButton = new MyJButton("Addition");
        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performAddition(firstPolyTextField.getText(), secondPolyTextField.getText()));
            }
        });
        bottomPanel.add(additionButton);

        MyJButton subtractionButton = new MyJButton("Subtraction");
        subtractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performSubtraction(firstPolyTextField.getText(), secondPolyTextField.getText()));
            }
        });
        bottomPanel.add(subtractionButton);

        MyJButton multiplicationButton = new MyJButton("Multiplication");
        multiplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performMultiplication(firstPolyTextField.getText(), secondPolyTextField.getText()));
            }
        });
        bottomPanel.add(multiplicationButton);

        MyJButton divisionButton = new MyJButton("Division");
        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performDivision(firstPolyTextField.getText(), secondPolyTextField.getText()));
            }
        });
        bottomPanel.add(divisionButton);

        MyJButton differentiationButton = new MyJButton("Differentiation");
        differentiationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performDifferentiation(firstPolyTextField.getText()));
            }
        });
        bottomPanel.add(differentiationButton);

        MyJButton integrationButton = new MyJButton("Integration");
        integrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realOutput.setText(Controller.performIntegration(firstPolyTextField.getText()));
            }
        });
        bottomPanel.add(integrationButton);

        mainPanel.add(bottomPanel);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
}
