/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


public class tempDlg extends JDialog {

    private JLabel []L1;
    private ResultSet rs;
    private JPanel p1;
    

    public tempDlg(ResultSet r) throws SQLException {
        super();
        rs = r;
        setModal(true);
        setSize(300, 250);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        
                List<String> rowDataList = new ArrayList<>();
                
                
                while(rs.next()){
                    String str = rs.getString(15) + " "+rs.getString(1) + " " + rs.getInt(2) + 
                            " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getInt(5) + " " + 
                            rs.getInt(6) + " " + rs.getInt(7) + " " + (rs.getInt(8) + " " + rs.getInt(9) + 
                            " " + rs.getInt(10) + " " + rs.getInt(11) + " " + rs.getInt(12) + " " +
                             rs.getInt(13) + " " + rs.getInt(14) + " ");
                    
                    rowDataList.add(str);
                }
                
        
      
                int count= rowDataList.size();
                L1 = new JLabel[count];
                p1 = new JPanel();
                
                for(int i = 0; i < count; i++){
                    L1[i] = new JLabel(rowDataList.get(i));
                    p1.add(L1[i]);
                }
                
                
                add(p1,BorderLayout.CENTER);
        

      
        setVisible(true);
    }

    
            
           
}