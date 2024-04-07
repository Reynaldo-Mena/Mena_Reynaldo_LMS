import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4CheckoutBookButton;
    private JButton a5CheckInButton;
    private JPanel mainPanel;


    public MainFrame(){

        setContentPane(mainPanel);
        setTitle("LMS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(410, 510);
        setLocationRelativeTo(null);
        setVisible(true);

        a1Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Menu.displayContents();
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.removeBooksByBarcode();
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.removeBooksByTitle();
            }
        });
        a4CheckoutBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.checkOut();
            }
        });
        a5CheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.checkIn();
            }
        });
    }

    public static void main(String[] args) {

        MainFrame mrFrame = new MainFrame();




    }




}
