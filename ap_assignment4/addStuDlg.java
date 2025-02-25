
package ap_assignment4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addStuDlg extends JDialog implements ActionListener {

    private JLabel L1;
    private JLabel[] L2;
    private JLabel Lfn, Lln, Lid, Ls, Lavg;
    private JTextField fn, ln, id, s, avg;
    private JTextField[] g;
    private JButton ad, cancel;
    private JPanel p1, p2;
    private PreparedStatement prep;

    public addStuDlg(PreparedStatement p) {

        super();
        prep = p;
        setModal(true);
        setSize(500, 500);
        setLocation(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        L1 = new JLabel("Add a new student");
        L2 = new JLabel[10];
        g = new JTextField[10];
        ad = new JButton("Add");
        cancel = new JButton("Cancel");

        fn = new JTextField();
        ln = new JTextField();
        id = new JTextField();
        s = new JTextField();
        avg = new JTextField();

        Lfn = new JLabel("First Name");
        Lln = new JLabel("Last Name");
        Lid = new JLabel("ID");
        Ls = new JLabel("Sex");
        Lavg = new JLabel("Average");

        add(L1, BorderLayout.NORTH);

        p1 = new JPanel(new GridLayout(0, 2, 0, 0));
        p2 = new JPanel();

        p2.add(ad);
        p2.add(cancel);

        p1.add(Lfn);
        p1.add(fn);
        p1.add(Lln);
        p1.add(ln);
        p1.add(Lid);
        p1.add(id);
        p1.add(Ls);
        p1.add(s);

        for (int i = 0; i < 10; i++) {
            L2[i] = new JLabel("Grade " + (i + 1));
            g[i] = new JTextField();
            g[i].setColumns(1);

            p1.add(L2[i]);
            p1.add(g[i]);
        }

        p1.add(Lavg);
        p1.add(avg);

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        ad.addActionListener(this);
        cancel.addActionListener(this);
        setVisible(true);
    }

    private void clear() {
        for (int i = 0; i < 10; i++) {
            g[i].setText("");

        }
        
        fn.setText("");
        ln.setText("");
        id.setText("");
        s.setText("");
        avg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ad) {
            try {

                prep.setString(15, fn.getText());
                prep.setString(1, ln.getText());
                prep.setInt(2, Integer.parseInt(id.getText()));
                prep.setString(3, s.getText());

                for (int i = 0; i < 10; i++) {
                    prep.setInt((i + 4), Integer.parseInt(g[i].getText()));
                }
                prep.setInt(14, Integer.parseInt(avg.getText()));
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(addStuDlg.class.getName()).log(Level.SEVERE, null, ex);
            }

            clear();
        }

        if (e.getSource() == cancel) {
            dispose();
        }

    }

}
