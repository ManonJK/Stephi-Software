package Vue;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EditDep {
    static int ID_dep;
    static int ID_bien;
    private JPanel Panel;
    private JTextField textField1;
    private JComboBox comboTypes;
    private JButton validerButton;
    private JButton annulerButton;
    private JLabel TitleLabel;
    private JFrame parent;

    public EditDep (JFrame f){
        System.out.println("id_bien : " + ID_bien);
        System.out.println("id_dep : " +ID_dep);
        textField1.setText(Controller.getDepSize(ID_bien, ID_dep));
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.dispose();
                EditVente.getInstance().checkButtons();
            }
        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int superficie = Integer.parseInt(textField1.getText());
                String type = String.valueOf(comboTypes.getSelectedItem());
                Controller.creaDep(ID_bien, type, superficie);
            }
        });
    }

    public static void main(int id_dep, int id_bien) {
        ID_dep = id_dep;
        ID_bien = id_bien;
        JFrame frame = new JFrame("Modifier la dépendance");
        frame.setContentPane(new EditDep(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ArrayList<String> list = new ArrayList<String>();
        ResultSet allDepTypes = Controller.getDepTypes();
        try{
            while(allDepTypes.next()){
                list.add(allDepTypes.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        String[] types = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            types[i] = list.get(i);
        }
        comboTypes = new JComboBox(types);
    }
}
