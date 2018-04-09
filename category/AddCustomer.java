package category;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class AddCustomer extends JPanel implements ActionListener
{
  public JPanel namePane,addrPane,numPane,typePane,buttonPane,searchPane,searchrsltPane;
  public JTextField nametf,numtf,searchtf;
  public JTextArea area;
  public JComboBox typecb ,cusexis;
  public JButton save,clear,searchbtn,del;
  JLabel addcus;
  int id=0;
  public AddCustomer()
  {

    this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

    searchPane=new JPanel();
    searchtf=new JTextField("Search Customer",10);
    searchbtn=new JButton("Search");
    System.out.println(searchbtn.getText());
    searchPane.add(searchtf,BorderLayout.WEST);
    searchPane.add(searchbtn,BorderLayout.EAST);
    searchbtn.addActionListener(this);
    this.add(searchPane);

    searchrsltPane=new JPanel();
    del=new JButton();
    add(searchrsltPane);

    addcus=new JLabel("Add new Customer");
    add(addcus);

    namePane=new JPanel();
    nametf=new JTextField("Name",20);
    namePane.add(nametf);
    this.add(namePane);

    addrPane=new JPanel();
    area =new JTextArea("Address",20,20);
    //area.setBounds(10,30,200,200);
    addrPane.add(area);
    //addrPane.setSize(300,300);
    this.add(addrPane);

    numPane=new JPanel();
    numtf=new JTextField("Phone Number");
    numPane.add(numtf);
    numtf.setSize(20,10);
    this.add(numPane);

    typePane=new JPanel();
    typecb=new JComboBox();
    typecb.addItem("--Customer Type--");
    ArrayList<String> typers=SqlFunc.allCusTypes();
    for(int i=0;i<typers.size();i++)
    {
      typecb.addItem(typers.get(i));

    }
    typePane.add(typecb);
    this.add(typePane);

    buttonPane=new JPanel();
    save=new JButton("Save");
    clear=new JButton("Clear");
    buttonPane.add(save);
    buttonPane.add(clear);
    this.add(buttonPane);
    save.addActionListener(this);
    clear.addActionListener(this);
  }

  public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()==save)
      {
  		if(nametf.getText().equals("") || area.getText().equals("") || numtf.getText().equals("")||nametf.getText().equals("Name") || area.getText().equals("Address") || numtf.getText().equals("Phone Number")||String.valueOf(typecb.getSelectedItem()).equals("--Customer Type--"))
  		{
        JOptionPane.showMessageDialog(this,"Enter Proper details!");
  		}
  		else
  		{
        SqlFunc.addCustomer(nametf.getText(),area.getText(),numtf.getText(),String.valueOf(typecb.getSelectedItem()));
        JOptionPane.showMessageDialog(this,"Saved changes");
  		}
      }
      else
      if(ae.getSource()==searchbtn)
      {
        System.out.println("searchbtn");
        searchrsltPane.removeAll();
        searchrsltPane.revalidate();
        searchrsltPane.repaint();
        cusexis=new JComboBox(SqlFunc.searchCustomer(searchtf.getText()).toArray());
        searchrsltPane.add(cusexis);
          del=new JButton("delete customer");
          searchrsltPane.add(del);
          del.addActionListener(this);

      }
      else if(ae.getSource()==del)
      {
        if(!String.valueOf(cusexis.getSelectedItem()).equals("No items found"))
          {
            id=Integer.parseInt(String.valueOf(cusexis.getSelectedItem()).split(" ")[0]);
            if(id==0)
              JOptionPane.showMessageDialog(this,"please select an item");
            else
              {
                  System.out.println(id);
                  int ans= JOptionPane.showOptionDialog(this,"Are you sure want to delete this customer?",
            			"delete customer"
            			,JOptionPane.YES_NO_OPTION
            			,JOptionPane.QUESTION_MESSAGE
            			,null,null,null);
            			if(ans==0)
                  {
                    SqlFunc.delCustomer(id);
                    JOptionPane.showMessageDialog(this,"Customer deleted!");
                  }
              }
          }
          else
          {
            JOptionPane.showMessageDialog(this,"searched item not found!");
          }
      }
      else if(ae.getSource()==clear)
      {
        nametf.setText("Name");
        area.setText("Address");
        numtf.setText("Phone Number");
      }
    }

}
