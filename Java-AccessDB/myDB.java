
package ap_assignment4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author HP
 */
public class myDB extends JFrame implements ActionListener {

    private JMenuBar bar;
    private JMenu file, ops;
    private JMenuItem imp, exp, ext, ad, del, srch, mod;

    private Statement st;
    private PreparedStatement prep;
    private Connection con;
    private ResultSet rs,rs2;
    
    private JList list1;
    private DefaultListModel mode;
    private JLabel []Lt;
    private JLabel head;
    private JPanel p1;

    public myDB() {
        super("My DataBase");

        setSize(570, 500);
        setLocation(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        head = new JLabel("Current Data : ");
        add(head,BorderLayout.NORTH);
        
        bar = new JMenuBar();
        file = new JMenu("File");
        ops = new JMenu("Operations");
        imp = new JMenuItem("Import");
        exp = new JMenuItem("Export");
        ext = new JMenuItem("Exit");
        ad = new JMenuItem("Add");
        del = new JMenuItem("Delete");
        srch = new JMenuItem("Search");
        mod = new JMenuItem("Modify a grade");
        
        imp.addActionListener(this);
        exp.addActionListener(this);
        del.addActionListener(this);
        ext.addActionListener(this);
        ad.addActionListener(this);
        srch.addActionListener(this);
        mod.addActionListener(this);
        
        setJMenuBar(bar);

        file.add(imp);
        file.add(exp);
        file.add(ext);
        ops.add(ad);
        ops.add(del);
        ops.add(srch);
        ops.add(mod);

        bar.add(file);
        bar.add(ops);
        
        mode = new DefaultListModel();
        list1 = new JList(mode);
        list1.setBorder(BorderFactory.createTitledBorder("Data :"));
        
        //add(list1,BorderLayout.CENTER);

        try {
            con = getCon();
            st = con.createStatement();
            prep = con.prepareStatement("insert into Table1 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            rs = st.executeQuery("Select * from Table1");
            rs2 = st.executeQuery("Select * from Table1");
            System.out.println("olalallala");
            
            /*
            mode.addElement("FirstName  LastName  ID  Sex  G1  G2  G3  G4  G5  G6  G7  G8  G9  G10  Average");
            mode.addElement("------------------------------------------------------------------------------");
            
            while(rs.next()){
                mode.addElement(rs.getString(15) + "  "+rs.getString(1) + "  " + rs.getInt(2) + 
                            "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getInt(5) + "  " + 
                            rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getInt(9) + 
                            "  " + rs.getInt(10) + "  " + rs.getInt(11) + "  " + rs.getInt(12) + "  " +
                             rs.getInt(13) + "  " + rs.getInt(14) + " ");
            }
            */
           ArrayList<String> rows = new ArrayList<>();
           
           
           while(rs.next()){
               int sum = 0,count = 0;
               String str = rs.getString(15) + "  "+rs.getString(1) + "  "+ 
                            rs.getInt(2)  + "  " + rs.getString(3) + "  " + 
                            rs.getString(4) + "  " + rs.getInt(5) + "  " +
                            rs.getInt(6) + " " + rs.getInt(7) + " " + 
                            rs.getInt(8) + " " + rs.getInt(9) + "  " +
                            rs.getInt(10) + "  " + rs.getInt(11) + "  " + 
                            rs.getInt(12) + "  " + rs.getInt(13) + "  " ; 
                            
               
               for(int i = 4 ; i <= 13; i++){
                   if(rs.getInt(i) != -9999){
                   sum += rs.getInt(i);
                   count++;
                   }
               }
               rows.add(str + ((float)sum / count));
               
           }

           
           int count = rows.size();
           Lt = new JLabel[count];
           p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
           
           for(int i = 0 ; i < count; i++){
               Lt[i] = new JLabel(rows.get(i));
               p1.add(Lt[i]);
           }
           
           add(p1,BorderLayout.CENTER);
            
        } catch (Exception ex) {
            System.out.println("oooops" + ex);
        }

       
        setVisible(true);
    }

    private Connection getCon() throws Exception {
        //String drv = "sun.jdbc.odbc.JdbcOdbcDriver";
        String drv = "net.ucanaccess.jdbc.UcanaccessDriver";
        
        //String url = "jdbc:odbc:123";
        String url = "jdbc:ucanaccess://C:/Users/HP/Documents/123.accdb";
        
        System.out.println("111111111111111111");
        Class.forName(drv);
        return DriverManager.getConnection(url);

    }
    
    public void addStu(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == imp){
           myDlg dlg =  new myDlg();
           String path = dlg.getPath();
           int ss = 0;
            
            try {
                Scanner input = new Scanner(new File(path));
                
                int sum = 0,num1,count = 0;
                while(input.hasNext()){
                prep.setString(15,input.next());
                prep.setString(1,input.next());
                prep.setInt(2,input.nextInt());
                prep.setString(3,input.next());
                for(int i = 4; i <= 13; i++){
                    num1 = input.nextInt();
                    System.out.println(num1);
                    if(num1 != -9999){
                    sum += num1;
                    count++;
                    }
                    prep.setInt(i,num1);
                }
                
                prep.setFloat(14,(float)sum / count);
                input.nextFloat();
                
                prep.execute();
                
                
                
                
                
                
               
            }
                
                
            } catch (FileNotFoundException ex) {
                System.out.println("this");
               
            } catch (SQLException ex) {
                System.out.println("that");
            }
           
         System.out.println(ss);
           
        }
        
        if(e.getSource() == exp){
            
            myDlg dlg =  new myDlg();
            try {
                PrintStream output = new PrintStream(dlg.getPath());
                
                int sum = 0, count = 0;
                while(rs2.next()){
                    output.print(rs2.getString(15) + " ");
                    output.print(rs2.getString(1) + " ");
                    output.print(rs2.getInt(2) + " ");
                    output.print(rs2.getString(3) + " ");
                    output.print(rs2.getString(4) + " ");
                    output.print(rs2.getInt(5) + " ");
                    output.print(rs2.getInt(6) + " ");
                    output.print(rs2.getInt(7) + " ");
                    output.print(rs2.getInt(8) + " ");
                    output.print(rs2.getInt(9) + " ");
                    output.print(rs2.getInt(10) + " ");
                    output.print(rs2.getInt(11) + " ");
                    output.print(rs2.getInt(12) + " ");
                    output.print(rs2.getInt(13) + " ");
                    //output.println(rs2.getInt(14) + " ");
                    
                    
                   // rs2.next();
                   sum = 0;
                   count = 0;
                    for(int i = 4 ; i <= 13;i++){
                        if(rs2.getInt(i) != -9999){
                            System.out.println(rs2.getInt(i));
                            sum+= rs2.getInt(i);
                            count++;
                        }
                            
                    }
                    
                    output.println(((float)sum / count) + " ");
                    
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(myDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(myDB.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        if(e.getSource() == ext){
            dispose();
        }
        
        
        if(e.getSource() == ad){
            new addStuDlg(prep);
        }
        
        if(e.getSource() == del){
            new delStuDlg(con);
        }
        
        if(e.getSource() == srch){
            new srchStu(st);
        }
        
        if(e.getSource() == mod){
            new modGrd(con);
        }
    }

}
