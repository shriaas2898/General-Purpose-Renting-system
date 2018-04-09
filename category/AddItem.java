package category;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddItem extends JPanel implements ActionListener
{
  JLabel addi,or;
  JTextField name,qty;
  public JButton addcat,save,clear,saveadd,refresh;
  JComboBox cat;
  JPanel catPane,buttonPane;
  public AddItem()
  {
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    //heading
    addi=new JLabel("Add new item");
    add(addi);
    //name
    name=new JTextField("Name of Item",10);
    name.setMaximumSize( name.getPreferredSize() );

    add(name);
    //category
    catPane=new JPanel(new FlowLayout());
    refresh=new JButton("refresh list");
    refresh.addActionListener(this);
    catPane.add(refresh);
    cat=new JComboBox();
    cat.addItem("----Select Category----");
    ArrayList<String> catrs=SqlFunc.getCategories();
    for(int i=0;i<catrs.size();i++)
    {
      cat.addItem(catrs.get(i));
      System.out.println(catrs.get(i));
    }
    catPane.add(cat);
    or=new JLabel("OR");
    catPane.add(or);
    addcat=new JButton("Add new category");
    catPane.add(addcat);
    addcat.addActionListener(this);
    add(catPane);
    //qty
    qty=new JTextField("Quantity",10);
    qty.setMaximumSize( qty.getPreferredSize() );
    add(qty);
    //button panel
    buttonPane=new JPanel(new FlowLayout());
    save=new JButton("Save");
    save.addActionListener(this);
    buttonPane.add(save);
    clear=new JButton("Clear all");
    buttonPane.add(clear);
    //saveadd=new JButton("Save & add another item");
    //buttonPane.add(saveadd);
    add(buttonPane);
  }
  public void actionPerformed(ActionEvent ae)
{
  if(ae.getSource()==addcat)
  {
    JFrame cat=new JFrame();
    AddCategory addcat=new AddCategory();

    cat.getContentPane().add(addcat);
    cat.setVisible(true);
    cat.pack();

  }
  if(ae.getSource()==save)
  {
  if(name.getText().equals("") || String.valueOf(cat.getSelectedItem()).equals("---Select Category----") || qty.getText().equals("")||name.getText().equals("Name of Item") ||  qty.getText().equals("Quantity"))
  {
    JOptionPane.showMessageDialog(this,"Fields Cannot Be Empty");
  }
  else
  {
    SqlFunc.addItem(name.getText(),String.valueOf(cat.getSelectedItem()),Integer.parseInt(qty.getText()));
    JOptionPane.showMessageDialog(this,"Item added");
  }
  }
  else if(ae.getSource()==refresh)
  {
    cat.removeAllItems();
    cat.addItem("----Select Category----");
    ArrayList<String> catrs=SqlFunc.getCategories();
    for(int i=0;i<catrs.size();i++)
    {
      cat.addItem(catrs.get(i));
      System.out.println(catrs.get(i));
    }

  }
}

}
