package imagewizard.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import imagewizard.image.Photo;

/**
 *
 * @author bombrunt
 */
public class Viewer {
    
    private final Photo photo;
    private final JFrame frame;
    
    public Viewer(final Photo photo) {
        this.photo = photo;
        frame = new JFrame("Image Wizard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        final JLabel label = new JLabel(new ImageIcon(photo.image));
        frame.add(label);
        
        addButton("B&W",new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                photo.getInBW(180);
                label.updateUI();
            }
        });
        
        addButton("Blobs",new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                photo.fillWithBlobs();
                label.updateUI();
            }
        });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void addButton(String label,ActionListener actionListener){
        JButton button = new JButton();
        button.addActionListener(actionListener);
        button.setText(label);
        frame.add(button);
    }
    
    public void pause(long laps) throws InterruptedException{
        Thread.sleep(laps);
    }
    
}
