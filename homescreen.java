import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import category.*;



class HomeScreen extends JFrame implements ActionListener
{
  private static HomeScreen hs=null;
  private int instance=0;
  JPanel centerPan,north;
  JButton rntitem,vrent,mitem,mcustomer;
  JLabel greeting;
  private HomeScreen()
  {System.out.println(++instance);
    System.out.println("HomeScreen constructor");
    Container cont=getContentPane();
    cont.setLayout(new GridBagLayout());
    //cont.setLayout(new BoxLayout(cont,BoxLayout.Y_AXIS));
    centerPan=new JPanel();
    centerPan.setLayout(new BoxLayout(centerPan,BoxLayout.Y_AXIS));
    //welcome
    greeting=new JLabel("Welcome!!");
    centerPan.add(greeting);
    //rent item
    rntitem=new JButton("Rent an item");
    //rntitem.setPreferredSize(new Dimension(20,10));
    rntitem.addActionListener(this);
    centerPan.add(rntitem);
        //view
    vrent=new JButton("View rented items");
    //vrent.setPreferredSize(new Dimension(20,10));
    vrent.addActionListener(this);
    centerPan.add(vrent);
    //rent item
    mitem=new JButton("Manage items");
    //mitem.setPreferredSize(new Dimension(20,10));
    centerPan.add(mitem);
    mitem.addActionListener(this);
    //rent item
    mcustomer=new JButton("Manage Customers");
    mcustomer.setVisible(false);
    centerPan.add(mcustomer);
    mcustomer.addActionListener(this);
    //mcustomer.setPreferredSize(new Dimension(40,20));

    cont.add(centerPan,new GridBagConstraints());
    setTitle("Home");
    setSize(600,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  //only single object should exist
  public static HomeScreen getInstance()
  {
    if(hs==null)
      {
        System.out.println("if called");
        hs=new HomeScreen();
      }
      return hs;
  }

  public void actionPerformed(ActionEvent ae)
  {
     HomeScreen2 hs2=HomeScreen2.getInstance(((JButton) ae.getSource()).getText());
     hs2.setVisible(true);
     this.setVisible(false);
  }



}


class HomeScreen2 extends JFrame implements ActionListener
{
    private static HomeScreen2 hs2;
    static String pn;
    JButton back;
    AddCustomer addcus;
    AddItem additm;
    RentOnItem roi;
    ReturnItem rtn;
    private  HomeScreen2(String panelName)
    {
      pn=panelName;
    getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
    switch(panelName)
    {
      case "Rent an item":
        {
          roi=new RentOnItem();
          getContentPane().add(roi);
          roi.cancel_Order.addActionListener(this);
          roi.search.addActionListener(this);
          roi.cancel_Order.setActionCommand("cancel");
      }break;

      case "View rented items":
      {
        rtn=new ReturnItem();
        getContentPane().add(rtn);
        }
      break;
      case "Manage items":
        {
          additm=new AddItem();
          getContentPane().add(additm);
          additm.clear.addActionListener(this);
          additm.clear.setActionCommand("cancel");
        }
      break;
      case "Manage Customers":
      {
        addcus=new AddCustomer();
        getContentPane().add(addcus);
        addcus.clear.addActionListener(this);
        addcus.clear.setActionCommand("cancel");
        addcus.del.setVisible(false);
      }break;
    }
    back=new JButton("<<Back");
    getContentPane().add(back);
    back.addActionListener(this);
    setTitle(panelName);
    setSize(600,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==back)
    {

      this.setVisible(false);
      HomeScreen.getInstance().setVisible(true);
    }

    else if(ae.getActionCommand()=="cancel")
    {
      int ans= JOptionPane.showOptionDialog(this,"Are you sure want to cancel?","cancel ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
      System.out.println(ans);
      if(ans==0)
      {
        this.setVisible(false);
        HomeScreen.getInstance().setVisible(true);
      }
    }
  else  if(ae.getSource()==roi.search)
    {
    this.setSize(600,600);
    }
  }
  public static HomeScreen2 getInstance(String panelName)
  {
    if(hs2==null||pn!=panelName)
      {

        hs2= new HomeScreen2(panelName);
      }
      return hs2;
  }}
class Setup extends JFrame implements ActionListener
{ JButton next;
  static int clicked=1;
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
    this.pack();
    setTitle("setup");
    }
  public void actionPerformed(ActionEvent ae)
 {
   if(ae.getSource().equals(next))
   {
     System.out.println("next");
     System.out.println(clicked);
     switch(clicked)
     {
       case 1:
         {
           if(!nameofa.getText().equals(""))
           {
             SqlFunc.setStatus("appname",nameofa.getText());
             customerPane.setVisible(true);
               ++clicked;
           }
           else
           {
             JOptionPane.showMessageDialog(this,"App Name cannot be empty");
           }
         }
         break;
       case 2:
         {
           if(ct.custType.getText().equals("") || ct.custType.getText().equals("eg. general/premium"))
           {
             JOptionPane.showMessageDialog(this,"Fields cannot be empty");
           }
           else
           {
             categoryPane.setVisible(true);
             SqlFunc.addCusType(ct.custType.getText());
              ++clicked;
           }
         }
         break;
       case 3:
         {
          if(ac.cattf.getText().equals(""))
          {
           JOptionPane.showMessageDialog(this,"Fields cannot be empty");
          }
          else
          {
            itemPane.setVisible(true);
            ++clicked;
          }
        }break;
     case 4:
     {
       JOptionPane.showMessageDialog(this,"All Set! Lets go to the Home Screen!");
       HomeScreen hs=HomeScreen.getInstance();
     }
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
}


class Start
{
  public static void main(String[] args) {
    if(SqlFunc.getStatus("appname").equals("set"))
    {
      HomeScreen hs= HomeScreen.getInstance();
      hs.setVisible(true);
      hs.mcustomer.setVisible(true);
      if(SqlFunc.getStatus("custype").equals("set"))
      {

      hs.mcustomer.setVisible(true);
      }
    }
    else{
      Setup st=new Setup();
      st.setVisible(true);
    }
  }
}
