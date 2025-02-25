
package ap_assignment4;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author HP
 */
public class srchStu extends JDialog implements ActionListener {

    private JLabel L1, L2,L3;
    private JTextField val;
    private JButton sch, cancel;
    private JPanel p1,p2;
    private JRadioButton fn,ln,Id;
    private Statement st;
    
    //private Connection con;
    //  private PreparedStatement prep;

    public srchStu(Statement s) {

        super();
        st = s;
        setModal(true);
        setSize(400, 250);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        L1 = new JLabel("Find a student:");
        L2 = new JLabel("Search value");
        L3 = new JLabel("");
        val = new JTextField();
        val.setColumns(7);

        sch = new JButton("Search");
        cancel = new JButton("Cancel");

        add(L1, BorderLayout.NORTH);
        
        
        
      

        p1 = new JPanel();
        p2 = new JPanel();
        
        p1.add(L2);
        p1.add(val);
        p1.add(L3);

       

        
        p2.add(sch);
        p2.add(cancel);

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        sch.addActionListener(this);
        cancel.addActionListener(this);
        setVisible(true);
    }

    private void clear() {
        val.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            dispose();
        }
        
        if (e.getSource() == sch) {
           
            
            try {
                ResultSet rs = st.executeQuery("Select * from Table1 where ID = " + val.getText() + " ");
                
                if(rs.next())
                {L3.setText(rs.getString(15) + " "+rs.getString(1) + " " + rs.getInt(2) + 
                            " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getInt(5) + " " + 
                            rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getInt(9) + 
                            " " + rs.getInt(10) + " " + rs.getInt(11) + " " + rs.getInt(12) + " " +
                             rs.getInt(13) + " " + rs.getInt(14) + " ");
                }else{
                    L3.setText("Not found!!");
                }
                
                clear();
            } catch (SQLException ex) {
                Logger.getLogger(srchStu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        }
    }

}