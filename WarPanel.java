import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class WarPanel extends JPanel
{
    private Card[] deck = new Card[54];
    private JButton goButton = new JButton("GO");
    private JLabel titleLabel = new JLabel("WAR");
    private JLabel deckImage1 = new JLabel();
    private JLabel deckImage2 = new JLabel();
    private JLabel discardImage1 = new JLabel();
    private JLabel discardImage2 = new JLabel();
    private JLabel deckLabel1 = new JLabel("Cards: 27");
    private JLabel deckLabel2 = new JLabel("Cards: 27");
    private JLabel discardLabel1 = new JLabel("Cards: 0");
    private JLabel discardLabel2 = new JLabel("Cards: 0");
    private JLabel totalLabel1 = new JLabel("27");
    private JLabel totalLabel2 = new JLabel("27");
    private JLabel cardImage1 = new JLabel();
    private JLabel cardImage11 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage12 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage13 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage14 = new JLabel();
    private JLabel cardImage2 = new JLabel();
    private JLabel cardImage21 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage22 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage23 = new JLabel(new ImageIcon("b1fv.png"));
    private JLabel cardImage24 = new JLabel();
    private ArrayList<Card> deck1 = new ArrayList<Card>();
    private ArrayList<Card> deck2 = new ArrayList<Card>();
    private ArrayList<Card> discard1 = new ArrayList<Card>();
    private ArrayList<Card> discard2 = new ArrayList<Card>();
    private Card card1;
    private Card card11;
    private Card card12;
    private Card card13;
    private Card card14;
    private Card card2;
    private Card card21;
    private Card card22;
    private Card card23;
    private Card card24;
    private int activeCards;
    private int lastWinner;
    private int shuffleChoice = 0;
    private String oldText;
    private boolean justWarred = false;

    private JLabel firework[] = new JLabel[20];
    private JLabel testLabel = new JLabel();
    private JLabel winner = new JLabel(new ImageIcon("winner.jpg"));
    private boolean over = false;
    public WarPanel()
    {        
        for(int x = 0; x < firework.length; x ++)
        {
            firework[x] = new JLabel();
        }

        ButtonHandler listener = new ButtonHandler();

        setLayout(null);
        setBackground(new Color(35, 142, 35));
        createDeck();
        shuffle();
        deal();

        add(goButton);
        goButton.addActionListener(listener);
        goButton.setFocusable(false);
        goButton.setBackground(new Color(138, 86, 58));
        goButton.setFont(new Font("Stencil", Font.PLAIN, 17));
        goButton.setForeground(Color.WHITE);
        goButton.setSize(130, 50);
        goButton.setLocation(385, 450);
        
        add(titleLabel);
        titleLabel.setFont(new Font("Stencil", Font.PLAIN, 70));
        titleLabel.setSize(300, 300);
        titleLabel.setLocation(380, -60);

        add(deckImage1);
        deckImage1.setIcon(new ImageIcon("b1fv.png"));
        deckImage1.setSize(72, 96);
        deckImage1.setLocation(100, 150);

        add(deckLabel1);
        deckLabel1.setFont(new Font("Stencil", Font.PLAIN, 14));
        deckLabel1.setSize(200, 20);
        deckLabel1.setLocation(100, 250);

        add(deckImage2);
        deckImage2.setIcon(new ImageIcon("b1fv.png"));
        deckImage2.setSize(72, 96);
        deckImage2.setLocation(728, 150);

        add(deckLabel2);
        deckLabel2.setFont(new Font("Stencil", Font.PLAIN, 14));
        deckLabel2.setSize(200, 20);
        deckLabel2.setLocation(728, 250);

        add(totalLabel1);
        totalLabel1.setFont(new Font("Stencil", Font.PLAIN, 20));
        totalLabel1.setSize(200, 20);
        totalLabel1.setLocation(150, 100);

        add(totalLabel2);
        totalLabel2.setFont(new Font("Stencil", Font.PLAIN, 20));
        totalLabel2.setSize(200, 20);
        totalLabel2.setLocation(728, 100);

        add(cardImage1);
        cardImage1.setSize(72, 96);
        cardImage1.setLocation(256, 150);

        add(cardImage2);
        cardImage2.setSize(72, 96);
        cardImage2.setLocation(572, 150);

        add(discardImage1);
        discardImage1.setSize(72, 96);
        discardImage1.setLocation(100, 300);

        add(discardLabel1);
        discardLabel1.setFont(new Font("Stencil", Font.PLAIN, 14));
        discardLabel1.setSize(200, 20);
        discardLabel1.setLocation(100, 400);

        add(discardImage2);
        discardImage2.setSize(72, 96);
        discardImage2.setLocation(728, 300);

        add(discardLabel2);
        discardLabel2.setFont(new Font("Stencil", Font.PLAIN, 14));
        discardLabel2.setSize(200, 20);
        discardLabel2.setLocation(728, 400);

    }
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand() == "GO")
            {
                if(justWarred)
                {
                    cardImage1.setSize(72, 96);
                    cardImage2.setSize(72, 96);
                    remove(cardImage11);
                    remove(cardImage12);
                    remove(cardImage13);
                    remove(cardImage14);
                    remove(cardImage21);
                    remove(cardImage22);
                    remove(cardImage23);
                    remove(cardImage24);
                    repaint();
                    justWarred = false;
                }

                activeCards = 2;

                card1 = deck1.remove(0);
                card2 = deck2.remove(0);

                if(card1.getValue() > card2.getValue())
                {
                    lastWinner = 1;
                    discard1.add(card1);
                    discard1.add(card2);
                }
                else if(card1.getValue() < card2.getValue())
                {
                    lastWinner = 2;
                    discard2.add(card2);
                    discard2.add(card1);
                }
                else
                {
                    lastWinner = 0;
                    goButton.setText("3");
                }
            }
            else if(e.getActionCommand() == "RESHUFFLE")
            {
                if(lastWinner != 0)
                {
                    card1 = null;
                    card2 = null;
                }
                if(deck1.isEmpty())
                {
                    shuffleChoice = 1;
                    shuffle();
                }
                if(deck2.isEmpty())
                {
                    shuffleChoice = 2;
                    shuffle();
                }
                goButton.setText(oldText);
            }
            else if(e.getActionCommand() == "3")
            {

                card11 = deck1.remove(0);

                cardImage1.setSize(72, 30);
                cardImage1.setVerticalAlignment(JLabel.TOP);
                add(cardImage11);
                cardImage11.setSize(72, 96);
                cardImage11.setLocation(256, 180);

                card21 = deck2.remove(0);

                cardImage2.setSize(72, 30);
                cardImage2.setVerticalAlignment(JLabel.TOP);
                add(cardImage21);
                cardImage21.setSize(72, 96);
                cardImage21.setLocation(572, 180);

                goButton.setText("2");
            }
            else if(e.getActionCommand() == "2")
            {

                card12 = deck1.remove(0);

                cardImage11.setSize(72, 30);
                cardImage11.setVerticalAlignment(JLabel.TOP);
                add(cardImage12);
                cardImage12.setSize(72, 96);
                cardImage12.setLocation(256, 210);

                card22 = deck2.remove(0);

                cardImage21.setSize(72, 30);
                cardImage21.setVerticalAlignment(JLabel.TOP);
                add(cardImage22);
                cardImage22.setSize(72, 96);
                cardImage22.setLocation(572, 210);

                goButton.setText("1");
            }
            else if(e.getActionCommand() == "1")
            {

                card13 = deck1.remove(0);

                cardImage12.setSize(72, 30);
                cardImage12.setVerticalAlignment(JLabel.TOP);
                add(cardImage13);
                cardImage13.setSize(72, 96);
                cardImage13.setLocation(256, 240);

                card23 = deck2.remove(0);

                cardImage22.setSize(72, 30);
                cardImage22.setVerticalAlignment(JLabel.TOP);
                add(cardImage23);
                cardImage23.setSize(72, 96);
                cardImage23.setLocation(572, 240);

                goButton.setText("WAR!!!");
            }
            else if(e.getActionCommand() == "WAR!!!")
            {
                card14 = deck1.remove(0);

                cardImage13.setSize(72, 30);
                cardImage13.setVerticalAlignment(JLabel.TOP);
                add(cardImage14);
                cardImage14.setIcon(card14.getImage());
                cardImage14.setSize(72, 96);
                cardImage14.setLocation(256, 270);

                card24 = deck2.remove(0);

                cardImage23.setSize(72, 30);
                cardImage23.setVerticalAlignment(JLabel.TOP);
                add(cardImage24);
                cardImage24.setIcon(card24.getImage());
                cardImage24.setSize(72, 96);
                cardImage24.setLocation(572, 270);

                if(card14.getValue() > card24.getValue())
                {
                    activeCards = 10;
                    lastWinner = 1;
                    discard1.add(card1);
                    discard1.add(card11);
                    discard1.add(card12);
                    discard1.add(card13);
                    discard1.add(card14);
                    discard1.add(card2);
                    discard1.add(card21);
                    discard1.add(card22);
                    discard1.add(card23);
                    discard1.add(card24);
                }
                else if(card14.getValue() < card24.getValue())
                {
                    activeCards = 10;
                    lastWinner = 2;
                    discard2.add(card2);
                    discard2.add(card21);
                    discard2.add(card22);
                    discard2.add(card23);
                    discard2.add(card24);
                    discard2.add(card1);
                    discard2.add(card11);
                    discard2.add(card12);
                    discard2.add(card13);
                    discard2.add(card14);
                }
                else
                {
                    activeCards = 5;
                    lastWinner = 3;
                    discard1.add(card1);
                    discard1.add(card11);
                    discard1.add(card12);
                    discard1.add(card13);
                    discard1.add(card14);
                    discard2.add(card2);
                    discard2.add(card21);
                    discard2.add(card22);
                    discard2.add(card23);
                    discard2.add(card24);
                }
                goButton.setText("GO");
                justWarred = true;
            }
            else if(e.getActionCommand() == "EXIT")
            {
                System.exit(0);
            }
            updateDisplay();
        }
    }
    
    private void updateDisplay()
    {
        if(! (card1 == null || card2 == null))
        {
            cardImage1.setIcon(card1.getImage());
            cardImage2.setIcon(card2.getImage());
        }
        else
        {
            activeCards = 0;
            cardImage1.setIcon(null);
            cardImage2.setIcon(null);
        }
        switch(lastWinner)
        {
            case 0:
            if(discard1.isEmpty())
            {
                discardImage1.setIcon(null);
            }
            else
            {
                discardImage1.setIcon(discard1.get(discard1.size() - 1).getImage());
            }
            if(discard2.isEmpty())
            {
                discardImage2.setIcon(null);
            }
            else
            {
                discardImage2.setIcon(discard2.get(discard2.size() - 1).getImage());
            }
            discardLabel1.setText("Cards: " + discard1.size());
            discardLabel2.setText("Cards: " + discard2.size());
            break;
            case 1:
            if(discard1.size() <= activeCards)
            {
                discardImage1.setIcon(null);
                discardLabel1.setText("Cards: 0");
            }
            else
            {
                discardImage1.setIcon(discard1.get(discard1.size() - activeCards - 1).getImage());
                discardLabel1.setText("Cards: " + (discard1.size() - activeCards));
            }
            if(discard2.isEmpty())
            {
                discardImage2.setIcon(null);
            }
            else
            {
                discardImage2.setIcon(discard2.get(discard2.size() - 1).getImage());
            }
            discardLabel2.setText("Cards: " + discard2.size());
            break;
            case 2:
            if(discard1.isEmpty())
            {
                discardImage1.setIcon(null);
            }
            else
            {
                discardImage1.setIcon(discard1.get(discard1.size() - 1).getImage());
            }
            if(discard2.size() <= activeCards)
            {
                discardImage2.setIcon(null);
                discardLabel2.setText("Cards: 0");
            }
            else
            {
                discardImage2.setIcon(discard2.get(discard2.size() - activeCards - 1).getImage());
                discardLabel2.setText("Cards: " + (discard2.size() - activeCards));
            }
            discardLabel1.setText("Cards: " + discard1.size());
            break;
            case 3:
            if(discard1.size() <= activeCards)
            {
                discardImage1.setIcon(null);
                discardImage1.setText("Cards: 0");
            }
            else
            {
                discardImage1.setIcon(discard1.get(discard1.size() - activeCards - 1).getImage());
                discardLabel1.setText("Cards: " + (discard1.size() - activeCards));
            }
            if(discard2.size() <= activeCards)
            {
                discardImage2.setIcon(null);
                discardLabel2.setText("Cards: 0");
            }
            else
            {
                discardImage2.setIcon(discard2.get(discard2.size() - activeCards - 1).getImage());
                discardLabel2.setText("Cards: " + (discard2.size() - activeCards));
            }
        }
        deckLabel1.setText("Cards: " + deck1.size());
        deckLabel2.setText("Cards: " + deck2.size());
        totalLabel1.setText(Integer.toString(deck1.size() + discard1.size()));
        totalLabel2.setText(Integer.toString(deck2.size() + discard2.size()));
        
        if(deck1.isEmpty())
        {
            deckImage1.setIcon(null);
            oldText = goButton.getText();
            if(discard1.isEmpty())
            {
                 goButton.setText("EXIT");
                 end();
            }
            else
            {
                goButton.setText("RESHUFFLE");
            }
        }
        else
        {
            deckImage1.setIcon(new ImageIcon("b1fv.png"));
        }
        if(deck2.isEmpty())
        {
            deckImage2.setIcon(null);
            if(! deck1.isEmpty()) oldText = goButton.getText();
            if(! over)
            {
                if(discard2.isEmpty())
                {
                    goButton.setText("EXIT");
                    end();
                }
                else
                {
                    goButton.setText("RESHUFFLE");
                }
            }
        }
        else
        {
            deckImage2.setIcon(new ImageIcon("b1fv.png"));
        }
        
        if(! over)
        {
            //goButton.doClick();
        }
    }

    private void createDeck()
    {
        int num = 0;
        for(int value = 12; value >= 0; value --)
        {
            for(int suit = 0; suit < 4; suit ++)
            {
                deck[num] = new Card(value, suit, new ImageIcon(Integer.toString(num + 1) + ".png"));
                num ++;
            }
        }
        deck[52] = new Card(13, 0, new ImageIcon("53.png"));
        deck[53] = new Card(13, 2, new ImageIcon("54.png"));
    }

    private void shuffle()
    {
        switch(shuffleChoice)
        {
            case 0:
            Card tempCard;
            int cardIndex1 = (int)(Math.random() * 54);
            int cardIndex2;
            tempCard = deck[cardIndex1];
            for(int x = 0; x < 100; x ++)
            {
                cardIndex2 = (int)(Math.random() * 54);
                deck[cardIndex1] = deck[cardIndex2];
                cardIndex1 = cardIndex2;
            }
            deck[cardIndex1] = tempCard;
            break;
            case 1:

            while(! discard1.isEmpty())
            {
                int index = (int)(Math.random() * discard1.size());
                deck1.add(discard1.remove(index));
            }

            break;
            case 2:

            while(! discard2.isEmpty())
            {
                int index = (int)(Math.random() * discard2.size());
                deck2.add(discard2.remove(index));
            }

        
        }   
    }

    private void deal()
    {
        for(int x = 0; x < 54; x ++)
        {
            if(x % 2 == 0)
            {
                deck1.add(deck[x]);
            }
            else
            {
                deck2.add(deck[x]);
            }
        }
    }

    private void end()
    {
        winner.setSize(250,202);
        for(int x = 0; x < firework.length; x ++)
        {
            int tempInt = (int)(Math.random() * 4);
            firework[x].setIcon(new ImageIcon("firework" + tempInt + ".gif"));
            firework[x].setSize(128,128);
        }
        if(deck1.isEmpty() && discard1.isEmpty()){
            winner.setLocation(575, 300);
            for(int x = 0; x < firework.length; x ++)
            {
                firework[x].setLocation((int)(Math.random() * 322) + 450, ((int)(Math.random() * 460)));
                add(firework[x]);
            }
            add(winner);
        }
        if(deck2.isEmpty() && discard2.isEmpty()){
            winner.setLocation(75, 300);
            for(int x = 0; x < firework.length; x ++)
            {
                firework[x].setLocation((int)(Math.random() * 322), ((int)(Math.random() * 460)));
                add(firework[x]);
            }
            add(winner);
        }
        repaint();
        over = true;
    }
}