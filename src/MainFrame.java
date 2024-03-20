import javax.swing.*;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class MainFrame extends JFrame {

    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JPanel mainPanel;

    public static  Menu menu = new Menu();

    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("LMS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 410);
        setLocationRelativeTo(null);
        setVisible(true);

        a1Button.addActionListener(new ActionListener() {

            public void actionPerformed() {
                ("jfbrjkbgkjrbg");
                menu.
            }
        });
    }

    public static void main(String[] args) {
        MainFrame mrFrame = new MainFrame();

        Database db2 = new Database();


    }




}
