package Vue;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditVente {
    static int ID;
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

        Type.setText(Controller.getSaleInfos(ID, "type"));
        Etage.setText(Controller.getSaleInfos(ID, "etage"));
        Superficie.setText(Controller.getSaleInfos(ID, "superficie"));
        NbPieces.setText(Controller.getSaleInfos(ID, "nb_pieces"));
        Localisation.setText(Controller.getSaleInfos(ID, "localisation"));
        Description.setText(Controller.getSaleInfos(ID, "descriptif"));
        PrixMin.setText(Controller.getSaleInfos(ID, "prix_min"));
        PrixMax.setText(Controller.getSaleInfos(ID, "prix_max"));
        PrixVente.setText(Controller.getSaleInfos(ID, "prix_vente"));

        annulerLesModificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.dispose();
            }
        });
        ouiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id_dep = Controller.recupID("id_dependance", ID);
                int id_bien = Controller.recupID("id_bien", ID);
                EditDep.main(id_dep, id_bien);
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

    public static void main(int id) {
        ID = id;
        JFrame frame = new JFrame("Modifier la vente");
        frame.setContentPane(new EditVente(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
