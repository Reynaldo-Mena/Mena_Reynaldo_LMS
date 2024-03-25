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

    public static  Menu menu = new Menu();

    public MainFrame(){
        Database db2 = new Database();
        setContentPane(mainPanel);
        setTitle("LMS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(410, 510);
        setLocationRelativeTo(null);
        setVisible(true);

        a1Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                menu.addBooks(db2);
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.removeBooksByBarcode(db2);
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.removeBooksByTitle(db2);
            }
        });
        a4CheckoutBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.checkOut(db2);
            }
        });
        a5CheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.checkIn(db2);
            }
        });
    }

    public static void main(String[] args) {

        MainFrame mrFrame = new MainFrame();




    }




}
