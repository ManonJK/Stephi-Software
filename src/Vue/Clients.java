package Vue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clients {
    private JPanel Panel;
    private JButton supprimerButton;
    private JTextField idField;
    private JLabel TitleLabel;
    private JButton modifierButton;
    private JTable table1;
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
                int delete_client = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le client vendeur ?","Confirmation",JOptionPane.OK_CANCEL_OPTION);
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditClient.main("Clients");
            }
        });
        voirLesBiensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BiensClient.main("Biens du client");
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
