/**
 * GUI class
 */
//imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//GUI class
public class GUI implements ActionListener{

    private JPanel panel;
    private JFrame frame;
    
    private JButton loginButton;
    private JLabel title;
    
    //constructor
    public GUI() {
        this.frame = new JFrame();
        this.panel = new JPanel();
        
        this.loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
        this.title = new JLabel("Music Unleased");
        //panel modifier
         panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
         panel.setLayout(new GridLayout(0, 1));

         
         panel.add(title);
         panel.add(loginButton);
         //frame modifier
         frame.add(panel, BorderLayout.CENTER);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setTitle("Music Unleashed");
         frame.pack();
         frame.setVisible(true);
         
         
    }
//changes text to "Hello" after button is hit. 
    @Override
    public void actionPerformed(ActionEvent e) {
        title.setText("Hello");
        
    }

}

