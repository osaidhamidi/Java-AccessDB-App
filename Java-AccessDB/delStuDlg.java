
package ap_assignment4;

//import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author HP
 */
public class delStuDlg extends JDialog implements ActionListener {

    private JLabel L1, L2;
    private JTextField id;
    private JButton del, cancel;
    private JPanel p1, p2;
    private Connection con;
    //  private PreparedStatement prep;

    public delStuDlg(Connection c) {

        super();
        con = c;
        setModal(true);
        setSize(250, 250);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        L1 = new JLabel("Delete student");
        L2 = new JLabel("Enter student's ID");
        id = new JTextField();
        id.setColumns(7);

        del = new JButton("delete");
        cancel = new JButton("Cancel");

        add(L1, BorderLayout.NORTH);

        p1 = new JPanel();
        p2 = new JPanel();

        p1.add(L2);
        p1.add(id);

        p2.add(del);
        p2.add(cancel);

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        del.addActionListener(this);
        cancel.addActionListener(this);
        setVisible(true);
    }

    private void clear() {
        id.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            dispose();
        }

        if (e.getSource() == del) {
            int num = Integer.parseInt(id.getText());

            try {
                String s = "Delete from Table1 where ID = " + num;
                PreparedStatement prep = con.prepareStatement(s);
                prep.executeUpdate();
                clear();
                
             
            } catch (Exception ex) {
                System.out.println("error");
            }

        }
    }

}
