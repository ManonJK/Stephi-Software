package Vue;

import Controller.Controller;

import javax.swing.*;
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
    private JFrame parent;

    public BiensClient(JFrame f){
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        TitleLabel.setText("Biens de " + Controller.getclientInfos(ID, "nom") + " " + Controller.getclientInfos(ID, "prenom"));
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
