package category;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReturnItem extends JPanel implements ActionListener
{
  public JPanel rentItmsPane;
  public JList rented;
  public JButton ret;
  public ReturnItem()
  {
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    rentItmsPane=new JPanel();
    rented=new JList(SqlFunc.itemsRented().toArray());
    rentItmsPane.add(rented);
    add(rentItmsPane);
    ret=new JButton("Return Selected item");
    add(ret);
    ret.addActionListener(this);

  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==ret)
    {
      int ans;
      if(SqlFunc.dueAmnt(Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0]))==0)
        {
           ans= JOptionPane.showOptionDialog(this,"Confirm return?","Return item ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
          if(ans==0)
          {

              SqlFunc.returnItem(Integer.parseInt((String.valueOf(rented.getSelectedValue()).split(":")[1]).split(" ")[0]),Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0]),SqlFunc.dueAmnt(Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0])));

        }
        else
        {
           ans= JOptionPane.showOptionDialog(this,"Confirm return and pay"+SqlFunc.dueAmnt(Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0]))+"?","Return item ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
          if(ans==0)
          {
            SqlFunc.returnItem(Integer.parseInt((String.valueOf(rented.getSelectedValue()).split(":")[1]).split(" ")[0]),Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0]),SqlFunc.dueAmnt(Integer.parseInt(String.valueOf(rented.getSelectedValue()).split(" ")[0])));
          }

        }
    }

    }
  }
}
