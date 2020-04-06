package Vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiensClient {
    private JLabel TitleLabel;
    private JTable table1;
    private JTextField textField1;
    private JButton annulerLaVenteButton;
    private JButton modifierButton;
    private JPanel Panel;

    public BiensClient(JFrame f){
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        annulerLaVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cancel_sale = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler la vente de ce bien ?","Confirmation",JOptionPane.OK_CANCEL_OPTION);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String args) {
        JFrame frame = new JFrame("Biens du client");
        frame.setContentPane(new BiensClient(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
