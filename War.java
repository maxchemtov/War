import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class War extends JFrame
{
    JMenuBar menu = new JMenuBar();
    JMenu game = new JMenu("Game");
    JMenu help = new JMenu("Help");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem instructions = new JMenuItem("Instructions");

    public War()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setResizable(false);
        getContentPane().add(new WarPanel());
        
        MenuHandler listener = new MenuHandler();
        
        quit.addActionListener(listener);
        game.add(quit);
        
        instructions.addActionListener(listener);
        help.add(instructions);
        
        menu.add(game);
        menu.add(help);
        menu.setBackground(new Color(138, 86, 58));
        setJMenuBar(menu);
        
        setVisible(true);
    }
    public static void main(String args[])
    {
        War startGame = new War();
    }
    private class MenuHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == quit)
            {
                System.exit(0);
            }
            else if(e.getSource() == instructions)
            {
                JOptionPane.showMessageDialog(null, "You should already know how to play.");
            }
        }
    }
}