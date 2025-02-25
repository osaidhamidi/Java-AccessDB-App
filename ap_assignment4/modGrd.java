
package ap_assignment4;

/**
 *
 * @author HP
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class modGrd extends JDialog implements ActionListener {

    private JLabel L1,L2,L3,L4;
    private JTextField grd, id,nGrd;
    private JPanel p1,p2;
    private JButton done, cancel;
    private Connection con;
    private PreparedStatement prep;

    public modGrd(Connection c ){
        super();
        con = c;
        setModal(true);
        setSize(300, 250);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        L1 = new JLabel("Modify a grade:");
        L2 = new JLabel("Student's ID : ");
        L3 = new JLabel("Grade to modify(g1,g2..) : ");
        L4 = new JLabel("New grade : ");
        
        p1 = new JPanel();
        p2 = new JPanel();
        
        grd = new JTextField();
        id = new JTextField();
        nGrd = new JTextField();
        
        grd.setColumns(7);
        nGrd.setColumns(7);
        id.setColumns(7);
        
        done = new JButton("Done");
        cancel = new JButton("Cancel");
        
        done.addActionListener(this);
        cancel.addActionListener(this);
        
        p1.add(L2);
        p1.add(id);
        p1.add(L3);
        p1.add(grd);
        p1.add(L4);
        p1.add(nGrd);
        
        p2.add(done);
        p2.add(cancel);
        add(L1,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        
        
        setVisible(true);
    }

    private void clear() {
        id.setText("");
        grd.setText("");
        nGrd.setText("");
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            dispose();
        }
        
        if(e.getSource() == done){
            try {
            prep = con.prepareStatement("update Table1 set " + grd.getText() + " = "+ nGrd.getText()+ " where ID = " + id.getText());
            prep.execute();
            clear();
        } catch (SQLException ex) {
            Logger.getLogger(modGrd.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    
            
           
}