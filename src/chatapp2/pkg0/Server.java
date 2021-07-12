
package chatapp2.pkg0;
import java.net.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.*;
public class Server extends JFrame implements ActionListener
{
    JPanel p1;
    
   JTextField t1;
   JButton b1;
  static JTextArea ta;
   static ServerSocket sk;
   static Socket sok;
   static DataInputStream di;
   static DataOutputStream dout;
   Boolean typing ;
    public Server()
    {
        p1= new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,400,70);
        add(p1);
       
         ImageIcon  i1= new ImageIcon(ClassLoader.getSystemResource("chatapp2/pkg0/icons/3.png"));
         Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon i3= new ImageIcon(i2);
         JLabel lab1= new JLabel(i3);
         lab1.setBounds(5,17, 30,30);
          p1.add(lab1);
         // to perform action
          lab1.addMouseListener(new MouseAdapter(){
              public void mouseClicked(MouseEvent ae)
              {
                  System.exit(0);
              }
          });
                  
          // 2nd image
         ImageIcon  i4= new ImageIcon(ClassLoader.getSystemResource("chatapp2/pkg0/icons/1.png"));
         Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
         ImageIcon i6= new ImageIcon(i5);
         JLabel lab2= new JLabel(i6);
         lab2.setBounds(45,5, 60,60);
          p1.add(lab2);
         
       // video icon
         ImageIcon  vicon1= new ImageIcon(ClassLoader.getSystemResource("chatapp2/pkg0/icons/video.png"));
         Image vicon2 = vicon1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon vicon3= new ImageIcon(vicon2);
         JLabel lbl1= new JLabel(vicon3);
         lbl1.setBounds(250,13,30,30);
          p1.add(lbl1);
          // phone icon
          ImageIcon  picon1= new ImageIcon(ClassLoader.getSystemResource("chatapp2/pkg0/icons/phone.png"));
         Image picon2 = picon1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon picon3= new ImageIcon(picon2);
         JLabel lbl2= new JLabel(picon3);
         lbl2.setBounds(300,13,30,30);
          p1.add(lbl2);
          // symbols
         ImageIcon  sicon1= new ImageIcon(ClassLoader.getSystemResource("chatapp2/pkg0/icons/3icon.png"));
         Image sicon2 = sicon1.getImage().getScaledInstance(13,25, Image.SCALE_DEFAULT);
         ImageIcon sicon3= new ImageIcon(sicon2);
         JLabel lbl3= new JLabel(sicon3);
         lbl3.setBounds(350,13, 13,25);
          p1.add(lbl3);
          
         // now name 
         JLabel  lab3=new JLabel("Gaitonde");
         lab3.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
         lab3.setForeground(Color.WHITE);
         lab3.setBounds(120,15,100,15);
         p1.add(lab3);
         // active now
         JLabel  lab4=new JLabel("Active Now");
         lab4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
         lab4.setForeground(Color.WHITE);
         lab4.setBounds(120,40,100,10);
         p1.add(lab4);
         // now we need to add Timer;
         Timer t= new Timer(1,new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae)
            {
                     if(!typing)
                {
                    lab4.setText("Active Now");
                }
         
            }
         });
         // for 2 second
         t.setInitialDelay(2000);
        
         // text field to enter messages;
         t1=new JTextField();
         b1=new JButton("Send");
         t1.setBounds(5,500, 320,35);
         t1.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
         b1.setBounds(325,500,70,35);
         b1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
         b1.setBackground(new Color(7,94,84));
         b1.setForeground(Color.WHITE);
         add(t1);
         add(b1);
         b1.addActionListener(this);
         // test area
         ta= new JTextArea();
         ta.setBounds(5,75,390,420);
         ta.setEditable(false);
         ta.setLineWrap(true);
         ta.setWrapStyleWord(true);
         ta.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
         add(ta);
         // ad key lisner on textfield
          t1.addKeyListener(new KeyAdapter(){
             @Override
             public void keyPressed(KeyEvent ke)
                 {
                     lab4.setText("Typing.....");
                     t.stop();
                     typing=true;
                 }
             @Override
             public void keyReleased(KeyEvent ke)
                  {
                     typing=false;
                     if(!t.isRunning())
                    {
                      t.start();
                    }
                  }
         });
         
         
         
         setLayout(null);
         setSize(400,550);
         
         setLocation(350,125);
        
       getContentPane().setBackground(Color.WHITE);
        // to remove upper part caled tabbar
        setUndecorated(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
         try{
        String out= t1.getText();
        ta.setText(ta.getText()+"\n\t\t"+out);
        t1.setText(" ");
        dout.writeUTF(out);
       
        }
        catch(Exception e){}
        
            
    }
  public static void main(String[] args)
  {
      String msgget="";
      new Server().setVisible(true);
      try{
          sk= new ServerSocket(3178);
          sok= sk.accept();
          di=new DataInputStream(sok.getInputStream());
          dout = new DataOutputStream(sok.getOutputStream());
          msgget=di.readUTF();
          ta.setText(ta.getText()+"\n"+msgget);
          sok.close();
          sk.close();
          
      }
      catch(Exception e){}
      
  }

    private ActionListener ActionListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
