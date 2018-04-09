package category;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RentOnItem extends JPanel
{
  JPanel selectPane,numPane,typePane,buttonPane,searchPane;
  JTextField sitemtf,enumtf;
  JComboBox selcb,typecb;
  JButton add_Account,generate_Bill,cancel_Order,search;
  RentOnItem()
  {
     setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

    searchPane=new JPanel();
    sitemtf=new JTextField("Search",10);
    search=new JButton("Search");
    searchPane.add(sitemtf,BorderLayout.WEST);
    searchPane.add(search,BorderLayout.EAST);
     add(searchPane);

    selectPane=new JPanel();
    selcb=new JComboBox();
    selcb.addItem("Select Item");
    selectPane.add(selcb);
     add(selectPane);

    numPane=new JPanel();
    enumtf=new JTextField("Enter Number of Items");
    numPane.add(enumtf);
     add(numPane);

    typePane=new JPanel();
    typecb=new JComboBox();
    typecb.addItem("Type of Customer");
    typePane.add(typecb);
     add(typePane);

    buttonPane=new JPanel();
    add_Account=new JButton("Add Account");
    generate_Bill=new JButton("geberate bill");
    cancel_Order=new JButton("Cancel order");
    buttonPane.add(add_Account);
    buttonPane.add(generate_Bill);
    buttonPane.add(cancel_Order);
     add(buttonPane);

  }
}
