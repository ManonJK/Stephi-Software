package Vue;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Biens {
    private JPanel Panel;
    private JLabel TitleLabel;
    private JTable biensTable;
    private JTextField idField;
    private JButton modifierButton;
    private JButton annulerLaVenteButton;
    private JFrame parent;

    public Biens(JFrame f){
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        annulerLaVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int sale = Integer.parseInt(idField.getText());
                int cancel_sale = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler la vente de ce bien ? Cette action est irréversible","Confirmation",JOptionPane.OK_CANCEL_OPTION);
                if (cancel_sale == 0){
                    JOptionPane.showMessageDialog(null, Controller.cancelSale(sale));
                    parent.dispose();
                    main("biens");
                }
            }
        });

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
                    annulerLaVenteButton.setEnabled(false);
                }
                else {
                    modifierButton.setEnabled(true);
                    annulerLaVenteButton.setEnabled(true);
                }

            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(idField.getText());
                EditVente.main(id);
            }
        });
    }

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        biensTable = new JTable(Controller.buildTableModel(Controller.getAllBiens()));
    }

    public static void main(String args) {
        JFrame frame = new JFrame("Biens");
        frame.setContentPane(new Biens(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
