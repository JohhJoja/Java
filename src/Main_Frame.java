import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;

public class Main_Frame extends JFrame {

    Main_Frame(){
        setTitle("Main frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout, 10,10));

        JLabel label1 = new JLabel("Select file for executing");
        label1.setPreferredSize(new Dimension(160, 30));
        JLabel label2 = new JLabel("Here will be show path to u'r file");
        JPanel panel1 = new JPanel();
        add(label1);
        add(label2);
        add(panel1);
    }

}
