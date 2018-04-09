import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import category.*;



class Setup extends JFrame implements ActionListener
{ JButton next;
  static int clicked=0;
  JTextField nameofa;
  JPanel categoryPane,appnamePane,customerPane,itemPane,centerPane;
  CusType ct;
  AddCategory ac;
  JLabel appname;

  Setup()
  {
    Container cont= getContentPane();
    appnamePane=new JPanel();
    appname=new JLabel("Enter Name of the application");
    nameofa=new JTextField(10);
    appnamePane.add(appname);
    appnamePane.add(nameofa);
    cont.add(appnamePane,BorderLayout.NORTH);
    //center Panel
    centerPane=new JPanel();
    centerPane.setLayout(new BoxLayout(centerPane,BoxLayout.Y_AXIS));
    //customer Panel
    customerPane=new JPanel();
    customerPane.setLayout(new BoxLayout(customerPane,BoxLayout.Y_AXIS));
    ct=new CusType();
    customerPane.add(ct);
    ct.addCus.addActionListener(this);
    customerPane.setVisible(false);
    centerPane.add(customerPane);
    //Category Pane
    categoryPane=new JPanel();
    ac=new AddCategory();
    categoryPane.add(ac);
    categoryPane.setVisible(false);
    centerPane.add(categoryPane);
    ac.addcri.addActionListener(this);

    //item Pane
    itemPane=new JPanel();
    itemPane.add(new AddItem());
    itemPane.setVisible(false);
    centerPane.add(itemPane);
    cont.add(centerPane,BorderLayout.CENTER);
    //next
    next= new JButton("Next>>");
    cont.add(next,BorderLayout.SOUTH);
    next.addActionListener(this);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource().equals(next))
    { ++clicked;
      System.out.println("next");
      switch(clicked)
      {
        case 1:
          {
            customerPane.setVisible(true);
            SqlFunc.setStatus("appname",nameofa.getText());
          }
          break;
        case 2:
          {
            categoryPane.setVisible(true);

          }
          break;
        case 3:
          itemPane.setVisible(true);
          break;
      }

    }
    else if(ae.getSource()==ct.addCus)
    {
      ct = new CusType();
      customerPane.add(ct);
      ct.addCus.addActionListener(this);

    }

    this.pack();
  }
  public static void main(String[] args) {
    Setup jr= new Setup();
    jr.setTitle("Setup");
    jr.setVisible(true);
    jr.pack();


  }
}
