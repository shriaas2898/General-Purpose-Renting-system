package category;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class AddCriteria extends JPanel
{
  JLabel acri,of,rs,fr;
  JTextField notf,chargetf;
  JComboBox bfraftrcb,timecb,peroncecb,cuscb;
   AddCriteria()
  {
      setLayout(new FlowLayout());
      acri=new JLabel("Add new criteria");
      bfraftrcb=new JComboBox();
      bfraftrcb.addItem("Before");
      bfraftrcb.addItem("After");
     add(bfraftrcb);
      notf=new JTextField("no.");
      notf.setForeground(Color.gray);
     add(notf);
      of=new JLabel("of");
     add(of);
      timecb=new JComboBox();
      timecb.addItem("Day");
      timecb.addItem("Week");
      timecb.addItem("Month");
     add(timecb);
      chargetf=new JTextField("Charge");
     add(chargetf);
      rs=new JLabel("Rs.");
     add(rs);
      peroncecb=new JComboBox();
      peroncecb.addItem("Per_day");
      peroncecb.addItem("Once");
     add(peroncecb);
      fr=new JLabel("For");
      fr.setVisible(false);
     add(fr);
      cuscb=new JComboBox();
      cuscb.setVisible(false);
     add(cuscb);

  }
}

public class AddCategory extends JPanel implements ActionListener
{
  JPanel categoryPane,criteriaPane,existPane;
  JLabel category,ucri,or;
  public JTextField cattf;
  JComboBox ucricb;
  public JButton save ,addcri;
  AddCriteria ac;
  public AddCategory()
  {
    this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    categoryPane= new JPanel();
    categoryPane.setLayout(new FlowLayout());
    category=new JLabel("Category Title:");
    categoryPane.add(category);
    cattf=new JTextField(10);
    categoryPane.add(cattf);
    add(categoryPane);
    //criteria
    existPane=new JPanel(new FlowLayout());
    ucri=new JLabel("Use existing criteria");
    ucricb=new JComboBox();
    ucricb.addItem("--select criteria--");
    ArrayList<String> catrs=SqlFunc.getAllCriteria();
    for(int i=0;i<catrs.size();i++)
    {
      ucricb.addItem(catrs.get(i));
      for(String sp:catrs.get(i).split(" "))
      {
        System.out.println("split:"+sp);
      }
    }
    existPane.add(ucri);
    existPane.add(ucricb);
    add(existPane);
    or=new JLabel("OR");
    add(or);
    //new criteria
    criteriaPane=new JPanel();
    criteriaPane.setLayout(new BoxLayout(criteriaPane,BoxLayout.Y_AXIS));
    ac=new AddCriteria();
    criteriaPane.add(ac);
    add(criteriaPane);
    //buttons
    addcri=new JButton("Add another criteria");
    save=new JButton("Save");
    add(addcri);
    addcri.addActionListener(this);
    addcri.setActionCommand("save");
    add(save);
    save.addActionListener(this);
    save.setActionCommand("save");
    }

    public void actionPerformed(ActionEvent ae)
 {
   if(ae.getSource()==addcri)
   {
     ac=new AddCriteria();
     criteriaPane.add(ac);

   }
   else if(ae.getActionCommand().equals("save"))
   {
    if(cattf.getText().equals(""))
   {
     JOptionPane.showMessageDialog(this,"Fields Cannot Be Empty");
   }
   else
   {
     int ans=JOptionPane.showOptionDialog(this,"Save this category ","save category",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
     if(ans==0)
     {
       if(!ucricb.getSelectedItem().equals("--select criteria--"))
       {
       SqlFunc.addCriteria(cattf.getText(),
       String.valueOf(ucricb.getSelectedItem()).split(" ")[1],
       Integer.parseInt(String.valueOf(ucricb.getSelectedItem()).split(" ")[2]),
       String.valueOf(ucricb.getSelectedItem()).split(" ")[3],
       Float.parseFloat(String.valueOf(ucricb.getSelectedItem()).split(" ")[4]),
       String.valueOf(ucricb.getSelectedItem()).split(" ")[5]);
      }
      else
      {
      if(ac.notf.getText().equals("") || ac.chargetf.equals("") || ac.notf.getText().equals("no.") || ac.chargetf.equals("Charge"))
      {
        JOptionPane.showMessageDialog(this,"please select an existing criteria or don't leave the fields empty");
      }
      else
       {
         SqlFunc.addCriteria(cattf.getText(),String.valueOf(ac.bfraftrcb.getSelectedItem()),+Integer.parseInt(ac.notf.getText()),String.valueOf(ac.timecb.getSelectedItem()),Float.parseFloat(ac.chargetf.getText()),String.valueOf(ac.peroncecb.getSelectedItem()));
      }
    }
   }
}
   }
 }
}
