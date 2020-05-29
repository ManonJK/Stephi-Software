package Vue;

import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion {

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton seConnecterButton;
    private JPanel Panel;
    private JLabel TitleLabel;

    public Connexion(){
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String email = textField1.getText();
                String password = new String(passwordField1.getPassword());
                if (Controller.Connexion(email, password)){
                    Dashboard.main("Dashboard");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Connexion().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
