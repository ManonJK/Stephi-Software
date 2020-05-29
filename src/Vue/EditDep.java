package Vue;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDep {
    static int ID;
    private JPanel Panel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton validerButton;
    private JButton annulerButton;
    private JLabel TitleLabel;
    private JFrame parent;

    public EditDep (JFrame f){
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
                String type = textField1.getText();
                String superficie = String.valueOf(comboBox1.getSelectedItem());
                Controller.creaDep(ID, type, superficie);
            }
        });
    }

    public static void main(Integer id) {
        ID = id;
        JFrame frame = new JFrame("Modifier la dépendance");
        frame.setContentPane(new EditDep(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
