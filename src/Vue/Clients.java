package Vue;
import Controller.Controller;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Clients {
    private JPanel Panel;
    private JButton supprimerButton;
    private JTextField idField;
    private JLabel TitleLabel;
    private JButton modifierButton;
    private JTable clientsTable;
    private JButton voirLesBiensButton;

    public Clients(JFrame f){
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        idField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (idField.getText().equals("")){
                    modifierButton.setEnabled(false);
                    supprimerButton.setEnabled(false);
                    voirLesBiensButton.setEnabled(false);
                }
                else {
                    modifierButton.setEnabled(true);
                    supprimerButton.setEnabled(true);
                    voirLesBiensButton.setEnabled(true);
                }

            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id_client = Integer.parseInt(idField.getText());
                int del_client = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ce vendeur ? Cette action est irréversible et supprimera définitivement le compte choisi ainsi que tous les biens associés","Confirmation",JOptionPane.OK_CANCEL_OPTION);
                if (del_client == 0){
                    JOptionPane.showMessageDialog(null, Controller.del_client(id_client));
                }
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(idField.getText());
                EditClient.main(id);
            }
        });
        voirLesBiensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(idField.getText());
                BiensClient.main(id);
            }
        });
    }



    public static void main(String args) {
        JFrame frame = new JFrame("Clients");
        frame.setContentPane(new Clients(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        clientsTable = new JTable(Controller.buildTableModel(Controller.getAllClients()));
    }
}
