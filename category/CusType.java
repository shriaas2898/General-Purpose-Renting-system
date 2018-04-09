package category;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CusType extends JPanel implements ActionListener
{
  public JButton addCus,save;
  public JTextField custType;
  public JLabel customer;
  public CusType()
  {
    setLayout(new FlowLayout());
    customer=new JLabel("Add type of customer");
    add(customer);
    custType=new JTextField("eg. general/premium",20);
    add(custType);
    save=new JButton("Save Customer type");
    add(save);
    save.addActionListener(this);
    addCus=new JButton("Add another customer");
    add(addCus);
  }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==save)
    {if((!custType.getText().equals(""))&&(!custType.getText().equals("eg. general/premium")))
      {int ans=JOptionPane.showOptionDialog(this,"Save customer type","save type",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
      if(ans==0)
      {
        switch(SqlFunc.addCusType(custType.getText()))
        {
          case 1:
            JOptionPane.showMessageDialog(this,"type already exists");
            break;
          case 2:
            JOptionPane.showMessageDialog(this,"Type sucessfully inserted");
            break;
          case -1:
            JOptionPane.showMessageDialog(this,"some error occured try again later");
            break;
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(this,"please enter value for customer type");
    }
  }

  else if(ae.getSource()==addCus)
  {

      if((!custType.getText().equals(""))&&(!custType.getText().equals("eg. general/premium")))
      {
        int ans=JOptionPane.showOptionDialog(this,"Save this customer type and add another","save type",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(ans==0)
        {
          switch(SqlFunc.addCusType(custType.getText()))
          {
            case 1:
              JOptionPane.showMessageDialog(this,"type already exists");
            case 2:
              JOptionPane.showMessageDialog(this,"Type sucessfully inserted");
            case -1:
              JOptionPane.showMessageDialog(this,"some error occured try again later");
          }
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this,"please enter value for customer type");
      }

  }
}
}
