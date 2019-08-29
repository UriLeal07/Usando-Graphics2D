/**
 * @author Uri Leal
 * @beaninfo
 * description: Clase launcher
*/

import java.awt.GridLayout;
import javax.swing.*;

class MyPaint extends JFrame
{
    private final MyPanel panel;
    
    public MyPaint()
    {
        panel = new MyPanel();
        this.getContentPane().setLayout(new GridLayout());
        this.getContentPane().add(panel);
    }
    
    public static void main(String []arguments)
    {
        MyPaint fr = new MyPaint();
        fr.setVisible(true);
        fr.setSize(600,500);
        fr.setLocation(350, 100);
        fr.setResizable(true);
        fr.setTitle("Poligono");
        fr.setDefaultCloseOperation(MyPaint.EXIT_ON_CLOSE);
    }
}