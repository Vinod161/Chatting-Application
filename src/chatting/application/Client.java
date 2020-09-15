
package chatting.application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class Client extends JFrame implements ActionListener{
        
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Boolean typing;
    
    
    Client(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 50);
        add(p1);
        
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
         Image i2 = i1.getImage().getScaledInstance(17,17,Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         JLabel l1 = new JLabel((i3));
         l1.setBounds(10,15,17,17);
         p1.add(l1);
         l1.addMouseListener(new MouseAdapter(){
         public void mouseClicked(MouseEvent ae){
             System.exit(0);
         }
             
            });
         
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/2IMG.jpg"));
         Image i5 = i4.getImage().getScaledInstance(40,60, Image.SCALE_DEFAULT);
         ImageIcon i6 = new ImageIcon(i5);
         JLabel l2 = new JLabel(i6);
         l2.setBounds(30,10,40,60);
         p1.add(l2);
         
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
         Image i8 = i7.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
         ImageIcon i9 = new ImageIcon(i8);
         JLabel l5 = new JLabel(i9);
         l5.setBounds(280,10,40,40);
         p1.add(l5);
         
         ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
         Image i11 = i10.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
         ImageIcon i12 = new ImageIcon(i11);
         JLabel l6 = new JLabel(i12);
         l6.setBounds(330,10,40,40);
         p1.add(l6);
         
         ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
         Image i14 = i13.getImage().getScaledInstance(20,17, Image.SCALE_DEFAULT);
         ImageIcon i15 = new ImageIcon(i14);
         JLabel l7 = new JLabel(i15);
         l7.setBounds(400,15,20,17);
         p1.add(l7);
         
         JLabel l3 = new JLabel("Vinod Parmar");
         l3.setBounds(100,10,100,10);
         l3.setFont(new Font("SAN_SERIF", Font.BOLD,10));
         l3.setForeground(Color.white);
         p1.add(l3);
         
         JLabel l4 = new JLabel("Active Now");
         l4.setBounds(100,25,100,10);
         l4.setFont(new Font("SAN_SERIF", Font.PLAIN,10));
         l4.setForeground(Color.white);
         p1.add(l4);
         
         Timer t = new Timer(1, new ActionListener () {
             public void actionPerformed(ActionEvent ae){
             if(!typing){
                 l4.setText("Active Now");
                 
                }
             }
        });
         
         t.setInitialDelay(2000);
         
         a1 = new JTextArea();
         a1.setBounds(3,52,445,400);
         a1.setFont(new Font("SAN_SERIF", Font.PLAIN,15));
         a1.setEditable(false);
         a1.setLineWrap(true);
         a1.setWrapStyleWord(true);
         add(a1);
         
         
         t1 = new JTextField();
         t1.setBounds(5,455,355,40);
         t1.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
         add(t1);
         
         t1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
            l4.setText("typing...");
            
            t.stop();
            
            typing = true;
            } 
            public void keyReleased(KeyEvent ke){
            typing = false;
            
            if(!t.isRunning()){
            t.start();
            }
            
            }
         });
         
         b1 = new JButton("Send");
         b1.setBounds(360,455,85,40);
         b1.setBackground(new Color(7,94,84));
         b1.setForeground(Color.white);
         b1.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
         b1.addActionListener(this);
         add(b1);
         
         
         setLayout(null);
        setSize(450,500);
        setLocation(800,100);
        setUndecorated(true);
        setVisible(true);
        
     
    }

    public void actionPerformed(ActionEvent ae){
        try{
        String out = t1.getText();
        a1.setText(a1.getText()+"\n\t\t\t"+out);
        dout.writeUTF(out);
        t1.setText("");
        }catch(Exception e){}
}

    

    public static void main(String [] args)
    {
        new Client().setVisible(true);  
        
        try{
            s = new Socket("127.0.0.1",1116);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            String msginput = "";
            msginput = din.readUTF();
            a1.setText(a1.getText()+"\n"+msginput);
      
        }
        catch(Exception e){
                
            
        }
    }
       
}
    

