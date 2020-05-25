package Vue;

import Controller.Controller;
import com.toedter.calendar.JDateChooser;
import datechooser.beans.DateChooserCombo;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClient {
    static int ID;
    private JPanel Panel;
    private JTextField clientName;
    private JTextField clientFN;
    private JTextField clientMail;
    private JTextField clientAdd;
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
        clientAdd.setText(Controller.getclientInfos(ID, "adresse"));
        clientMail.setText(Controller.getclientInfos(ID, "mail"));
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
                String adresse = clientAdd.getText();
                String phone = clientPhone.getText();
                String birth = clientBD.getText();
                JOptionPane.showMessageDialog(null,Controller.modifyClient(ID, nom, prenom, mail, adresse, phone, birth));
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
