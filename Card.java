import javax.swing.*;
public class Card
{
    private int value;
    private int suit;
    private ImageIcon picture;

    public Card(int value, int suit, ImageIcon picture)
    {
        this.value = value;
        this.suit = suit;
        this.picture = picture;
    }
    public int getValue()
    {
        return value;
    }
    public int getSuit()
    {
        return suit;
    }
    public ImageIcon getImage()
    {
        return picture;
    }
}