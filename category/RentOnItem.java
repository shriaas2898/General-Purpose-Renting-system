package category;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class RentOnItem extends JPanel implements ActionListener
{
  JPanel selectPane,numPane,typePane,buttonPane,searchPane;
  JTextField sitemtf,enumtf;
  JLabel items;
  int id=0;
  JList rs;
  public JButton add_Account,generate_Bill,cancel_Order,search,select,next;
  AddCustomer addcus;
  boolean selection=false;

  public RentOnItem()
  {
     setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
     //search
    searchPane=new JPanel();
    sitemtf=new JTextField("Search",10);
    search=new JButton("Search");
    searchPane.add(sitemtf,BorderLayout.WEST);
    searchPane.add(search,BorderLayout.EAST);
    search.addActionListener(this);
     add(searchPane);
     //select
    selectPane=new JPanel(new BorderLayout());
      select=new JButton("selcet item");
      items=new JLabel("Search item to rent");
      selectPane.add(items,BorderLayout.NORTH);
      selectPane.setSize(selectPane.getPreferredSize());
     add(selectPane);
     //type
    typePane=new JPanel();
    typePane.setLayout(new BoxLayout(typePane,BoxLayout.Y_AXIS));

      addcus=new AddCustomer();
      typePane.add(addcus.searchPane);
      typePane.add(addcus.searchrsltPane);
      addcus.del.setVisible(false);

     add(typePane);
     //button
    buttonPane=new JPanel();
    //add_Account=new JButton("Add Account");
    generate_Bill=new JButton("generate bill");
    cancel_Order=new JButton("Cancel order");
    //buttonPane.add(add_Account);
    buttonPane.add(generate_Bill);
    buttonPane.add(cancel_Order);
    //add_Account.addActionListener(this);
    generate_Bill.addActionListener(this);
     add(buttonPane);

  }
  public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()==search)
      {
  		if(!sitemtf.getText().equals("Search")&&(!sitemtf.getText().equals(null)))
  		{
  			ArrayList<String> rentrs= SqlFunc.searchItem(sitemtf.getText());
        selectPane.removeAll();
        selectPane.revalidate();
        selectPane.repaint();
        rs=new JList(rentrs.toArray());
        selectPane.add(rs,BorderLayout.CENTER);
        selectPane.add(select,BorderLayout.SOUTH);
        select.addActionListener(this);
        selectPane.setSize(selectPane.getPreferredSize());
        //fr.setVisible(true);
        //fr.pack();
        System.out.println("search");
  		}
  		else
  		{
  			JOptionPane.showMessageDialog(this,"Cannot be Empty");
  		}

        //JFrame fr=new JFrame();

      }

      else if(ae.getSource()==select)
      { if(!rs.getSelectedValue().equals("No items found or items out of stock"))
        {
          id=Integer.parseInt(String.valueOf(rs.getSelectedValue()).split(" ")[0]);
          if(id==0)
            JOptionPane.showMessageDialog(this,"please select an item");
          else
            selection=true;
          System.out.println(id);
        }
        else
        {
          JOptionPane.showMessageDialog(this,"searched item not found!");
        }
    }
      else if(ae.getSource()==generate_Bill)
      {
  		if(selection)
  		{
  			int ans= JOptionPane.showOptionDialog(this,"Return Date:"+SqlFunc.generate_Bill(id)[0]+"\nTotal Amount:"+SqlFunc.generate_Bill(id)[1]+"\nConfirm order and accept payment?",
  			"order "
  			,JOptionPane.YES_NO_OPTION
  			,JOptionPane.QUESTION_MESSAGE
  			,null,null,null);
  			if(ans==0)
  			{

  				{
            System.out.println(String.valueOf(addcus.cusexis.getSelectedItem()));
  					SqlFunc.itemRented(Integer.parseInt(String.valueOf(addcus.cusexis.getSelectedItem()).split(" ")[0]),id,SqlFunc.generate_Bill(id)[0],Float.parseFloat(SqlFunc.generate_Bill(id)[1]));
  				}

  			}
  		}
  		else
  		{
  			JOptionPane.showMessageDialog(this,"please select valid item");
  		}

    }


}
}
