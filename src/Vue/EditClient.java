package Vue;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClient {
    static int ID;
    private JPanel Panel;
    private JTextField clientName;
    private JTextField clientFN;
    private JTextField clientMail;
    private JLabel TitleLabel;
    private JButton validerButton;
    private JButton annulerButton;
    private JTextField clientPhone;
    private JTextField clientBD;
    private JFrame parent;


    public EditClient(JFrame f){
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));

        clientName.setText(Controller.getclientInfos(ID, "nom"));
        clientFN.setText(Controller.getclientInfos(ID, "prenom"));
        clientMail.setText(Controller.getclientInfos(ID, "email"));
        clientPhone.setText(Controller.getclientInfos(ID, "phone"));
        clientBD.setText(Controller.getclientInfos(ID, "birth_date"));

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.dispose();
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String nom = clientName.getText();
                String prenom = clientFN.getText();
                String mail = clientMail.getText();
                String phone = clientPhone.getText();
                String birth = clientBD.getText();
                JOptionPane.showMessageDialog(null,Controller.modifyClient(ID, nom, prenom, mail, phone, birth));
            }
        });
    }


    public static void main(int id) {
        ID = id;
        JFrame frame = new JFrame("Clients");
        frame.setContentPane(new EditClient(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
