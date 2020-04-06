package Vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditVente {
    private JPanel Panel;
    private JLabel TitleLabel;
    private JButton validerLesModificationsButton;
    private JButton annulerLesModificationsButton;
    private JTable Photos;
    private JTextField Type;
    private JTextField Etage;
    private JTextField Superficie;
    private JTextField NbPieces;
    private JTextField Localisation;
    private JTextField Description;
    private JTextField PrixMin;
    private JTextField PrixMax;
    private JTextField PrixVente;
    public JRadioButton ouiRadioButton;
    public JRadioButton nonRadioButton;
    private JFrame parent;
    public static EditVente instance;

    public EditVente(JFrame f){
        instance = this;
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        annulerLesModificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.dispose();
            }
        });
        ouiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditDep.main("Editer la dépendance");
            }
        });
    }

    public static EditVente getInstance(){
        return instance;
    }

    public void checkButtons(){

        //Rajouter un if qui vérifie que dans la database il n'y ait pas de dépendance. S'il n'y en a pas :
        ouiRadioButton.setSelected(false);
        nonRadioButton.setSelected(true);
        // S'il y en a :
        // ouiRadioButton.setSelected(true);
        // nonRadioButton.setSelected(false);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String args, int id) {
        JFrame frame = new JFrame("Modifier la vente");
        frame.setContentPane(new EditVente(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
