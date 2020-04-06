package Vue;

import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    private JPanel Panel;
    private JButton SellersButton;
    private JButton VoirBiensButton;
    private JLabel NbSellers;
    private JLabel NbSalesActual;
    private JLabel NbSalesTri;
    private JLabel NbTotalSales;
    private JLabel TitleLabel;

    public Dashboard(JFrame f){
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
//        NbSellers.setText("coucou");
        SellersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Clients.main("Clients");
            }
        });
        VoirBiensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Biens.main("Biens");
            }
        });
    }



    public static void main(String args) {
        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(new Dashboard(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
