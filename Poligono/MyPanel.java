/**
 * @author Uri Leal
 * @beaninfo
 * description: Panel de dibujo
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

class MyPanel extends JPanel
{
    private int[] pX, pY;
    private int noPuntos, max, tamPunto;
    private Polygon pol;
    private JPopupMenu pMenu;
    private JMenuItem dibujar, limpiar;
    boolean polyCreado;
    
    MyPanel()
    {
        noPuntos = 0;
        max = 100;
        tamPunto = 7;
        polyCreado = false;
        pX = new int[max];
        pY = new int[max];
        
        this.setBackground(Color.WHITE);
        
        //Creamos popUpMenu y sus componentes (opciones).
        pMenu = new JPopupMenu();
        dibujar = new JMenuItem("Dibujar Polígono");
        limpiar = new JMenuItem("Limpiar Área");
        
        
        //Añadimos las opciones al popUpMenu.
        pMenu.add(dibujar);
        pMenu.add(limpiar);
        
        //Acciones que se ejecutan al hacer click en la opcion "Dibujar Polígono".
        dibujar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(noPuntos != 0)
                {
                    pol = new Polygon(pX, pY, noPuntos);
                    polyCreado = true;
                    
                    repaint();
                }
                
                else
                    JOptionPane.showMessageDialog(null, "Primero has click sobre el panel para crear puntos", "Sin puntos para crear polígono", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
        
        //Acciones que se ejecutan al hacer click en la opcion "Limpiar Área".
        limpiar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                noPuntos = 0;
                pol = new Polygon();
                polyCreado = false;
                
                repaint();
            }
        });
        
        //Acciones del Mouse.
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
                pX[noPuntos] = e.getX();
                pY[noPuntos] = e.getY();
                noPuntos++;
                
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e1)
            {
                if(e1.isPopupTrigger())
                    pMenu.show(e1.getComponent(), e1.getX(), e1.getY());
            }
        });
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.BLACK);
        
        if(!polyCreado && noPuntos > 0)
        {
            int i = 0;
            
            while(i < noPuntos)
            {
                g2.fill(new Ellipse2D.Double(pX[i], pY[i], tamPunto, tamPunto));
                i++;
            }
        }
        
        if(polyCreado)
        {
            g2.drawPolygon(pol);
            polyCreado = false;
        }
    }
}