package Vue;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BiensClient {
    static int ID;
    private JLabel TitleLabel;
    private JTable biensTable;
    private JTextField venteID;
    private JButton annulerLaVenteButton;
    private JButton modifierButton;
    private JPanel Panel;
    private JButton venduButton;
    private JFrame parent;

    public BiensClient(JFrame f){
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        TitleLabel.setText("Biens de " + Controller.getclientInfos(ID, "nom") + " " + Controller.getclientInfos(ID, "prenom"));

        venteID.getDocument().addDocumentListener(new DocumentListener() {
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
                if (venteID.getText().equals("")){
                    modifierButton.setEnabled(false);
                    annulerLaVenteButton.setEnabled(false);
                    venduButton.setEnabled(false);
                }
                else {
                    modifierButton.setEnabled(true);
                    annulerLaVenteButton.setEnabled(true);
                    venduButton.setEnabled(true);
                }

            }
        });


        annulerLaVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sale = Integer.parseInt(venteID.getText());
                int cancel_sale = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler la vente de ce bien ? Cette action est irréversible","Confirmation",JOptionPane.OK_CANCEL_OPTION);
                if (cancel_sale == 0){
                    JOptionPane.showMessageDialog(null, Controller.cancelSale(sale));
                    parent.dispose();
                    main(ID);
                }
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sale = Integer.parseInt(venteID.getText());
                EditVente.main(sale);
            }
        });
        venduButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sale = Integer.parseInt(venteID.getText());
                int cancel_sale = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir finaliser la vente de ce bien ? Cette action est irréversible","Confirmation",JOptionPane.OK_CANCEL_OPTION);
                if (cancel_sale == 0){
                    JOptionPane.showMessageDialog(null, Controller.setVendu(sale));
                    parent.dispose();
                    main(ID);
                }
            }
        });
    }

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        biensTable = new JTable(Controller.buildTableModel(Controller.getUserBiens(ID)));
    }

    public static void main(int id) {
        ID=id;
        JFrame frame = new JFrame("Biens du client");
        frame.setContentPane(new BiensClient(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
