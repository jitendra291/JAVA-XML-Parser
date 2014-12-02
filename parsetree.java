import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;


 class parsetree extends JPanel {
  public int numberofpoints,a=0,z,r,t,ed1;
  public void StartG(int nn, int ed,int w1,int w2) {
    numberofpoints=nn;
    ed1 = ed;
    r=w1;
    t=w2;
    System.out.println("\n\n"+"\t\t"+"******"+"\t"+"My Graph has :"+"\t"+"******"+"\n");
    System.out.println("\t\t\t"+"Number of nodes --->"+nn);
    System.out.println("\t\t\t"+"Nomuber of edges --->"+ed);
    System.out.println("\t\t\t"+"Weight Interval --->"+r+" to "+t);
    repaint(0);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    int maxWidth = getWidth();
    int maxHeight = getHeight();
    Random randomv = new Random();
    int x[] = new int [numberofpoints];
    int y[] = new int [numberofpoints];
    
    int adjacencyM[][] = new int [numberofpoints][numberofpoints];
    g.setColor(Color.red);
    for (int i = 0; i < numberofpoints; i++){
        x[i]=randomv.nextInt(maxWidth);
        y[i]=randomv.nextInt(maxHeight);
        g.fillOval(x[i],y[i],5,5);


    }
     g.setColor(Color.blue);
    int edges =0;
    for (int i1 = 0; i1 < numberofpoints -1 ;i1++){
      for  (int i2 = i1+1 ; i2 < numberofpoints;i2++){
        if ( edges <= randomv.nextInt(numberofpoints*(numberofpoints-1))/2 && edges<ed1 ) {
        
          
          adjacencyM[i2][i1]=1;
          edges++;
         
          g.drawLine(x[i1]+2,y[i1]+2,x[i2]+2,y[i2]+2);
         
          z=randomv.nextInt(t-r)+r;
          
          System.out.println("\t\t\t"+"The weight of "+edges+" "+"edge-->"+z);
          a=a+z;
      
        }
      }  
    }
    
    
    System.out.println("\t\t\t"+"Total weight--->"+a);
   a=0;
  }

}

public class Project extends JApplet implements ActionListener {

  int i;
  public JButton jButton1 = new JButton();

  public JLabel jLabel1 = new JLabel();
  public JLabel jLabel2 = new JLabel();

  public JLabel jLabel3 = new JLabel();
  public JLabel jLabel4 = new JLabel();
  public JTextField edges = new JTextField(3);
  public JTextField range = new JTextField(5);
  public JTextField range1 = new JTextField(5);
 public JTextField numofnodes = new JTextField(4);



  public parsetree graphd = new parsetree();

  Dimension d = new Dimension (350,350);

  public void init() {
  Container cp = getContentPane();
  cp.setLayout(new FlowLayout(0,20,20));
  cp.setSize(1100,550);
  jButton1.setText("Generate the Graph");
  
  jLabel1.setText("Number of Nodes:   ");
  jLabel2.setText("Total no. of edges:");
  

  jLabel3.setText("Edge Weight Lower Range:   ");
  jLabel4.setText("Edge Weight Upper Range:   ");

  cp.add(jLabel1);
  cp.add(numofnodes);
  cp.add(jLabel2);
  cp.add(edges);
  cp.add(jLabel3);
  cp.add(range);
   cp.add(jLabel4);
  cp.add(range1);
  cp.add(jButton1);

  graphd.setPreferredSize(d);
  cp.add(BorderLayout.CENTER,graphd);

  
  jButton1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent event) {
    graphd.StartG(Integer.parseInt(numofnodes.getText()),Integer.parseInt(edges.getText()),Integer.parseInt(range.getText()),Integer.parseInt(range1.getText()));
      }     
         });
  
  numofnodes.addActionListener(this);
  edges.addActionListener(this);
  range.addActionListener(this);
  range1.addActionListener(this);
  
  numofnodes.setText("10");
  edges.setText("5");
  range.setText("5");
  range1.setText("10");
    }

  public final void update(Graphics g) {
    paint(g);
     }


  public static void  main (String[] args) {
    JApplet applet = new Project();
    JFrame frame = new JFrame("Scale Free Small World Graphs");
    frame.getContentPane().add(applet);
    frame.setSize(1100,600);
    applet.init();
    applet.start();
    frame.setVisible(true);
  }
    public void actionPerformed(ActionEvent evt) {
    
      graphd.StartG(Integer.parseInt(numofnodes.getText()),Integer.parseInt(edges.getText()),Integer.parseInt(range.getText()),Integer.parseInt(range1.getText()));
    }

}
