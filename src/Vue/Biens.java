package Vue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biens {
    private JPanel Panel;
    private JLabel TitleLabel;
    private JTable table1;
    private JTextField idField;
    private JButton modifierButton;
    private JButton annulerLaVenteButton;

    public Biens(JFrame f){
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        annulerLaVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cancel_sale = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler la vente de ce bien ?","Confirmation",JOptionPane.OK_CANCEL_OPTION);
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
                EditVente.main("Editer", id);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
