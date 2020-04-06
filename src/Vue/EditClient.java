package Vue;

import com.toedter.calendar.JDateChooser;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClient {
    private JPanel Panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel TitleLabel;
    private JButton validerLesModificationsButton;
    private JButton annulerButton;
    private JFrame parent;


    public EditClient(JFrame f){
        parent = f;
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(17.0f));

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String args) {
        JFrame frame = new JFrame("Clients");
        frame.setContentPane(new EditClient(frame).Panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
