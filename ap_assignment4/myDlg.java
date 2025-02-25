
package ap_assignment4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class myDlg extends JDialog implements ActionListener{
    JFileChooser FC;
    String Path;
    public myDlg(){
        super();
        setTitle("Select :");
        setModal(true);
        setLocation(400,300);
        setSize(400,400);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        FC = new JFileChooser();
        FC.setCurrentDirectory(new File("C:/Users/HP/Desktop/App4"));
        FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        add(FC,BorderLayout.CENTER);
        
        FC.addActionListener(this);
        setVisible(true);
    }
    
    public String getPath(){
        return Path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == FC){
            Path = FC.getSelectedFile().getAbsolutePath();
            dispose();
        }
    }
}
