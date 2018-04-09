package category;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlFunc
{
  //staticConnection cn;
  static Connection cn;
  static ResultSet rs,rs2;
  static String query;
  static Statement st;
//--------------------------------------------Opening Connection--------------------------------------
  static Connection openConn()
  {
    try{
    Class.forName("com.mysql.jdbc.Driver");
    cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");
    return cn;
  }
    catch(SQLException e){
      System.out.println(e);
      return cn;
    }
    catch(Exception e){
      System.out.println(e);
      return cn;
    }
  }
//--------------------------------------------Search-------------------------------------------------
public  static ArrayList<String> getAllCriteria()
{
    ArrayList<String> result=new ArrayList<String>();
    try
    {
        cn=openConn();
        query="select time,no_of,duration,charge,occurence from criteria ";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        int i,j=0;
        String rows[]=new String [6];
        //String[] rsarr=new String[result.size()];
        while(rs.next())
        {
          rows[0]=Integer.toString(rs.getInt(1));
          rows[1]=rs.getString(2);
          rows[2]=Integer.toString(rs.getInt(3));
          rows[3]=rs.getString(4);
          rows[4]=Float.toString(rs.getFloat(5));
          rows[5]=rs.getString(6);
          result.add(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]+" "+rows[4]+" "+rows[5]);


          for(String rsarr:result)
          {

            System.out.println(rsarr);
          }
          j++;
        }

    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
  }
public  static ArrayList<String> searchItem(String name)
{
    ArrayList<String> result=new ArrayList<String>();
    try
    {
        cn=openConn();
        query="select * from items where name like '%"+name+"%' and qty_available>0";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        int i,j=0;
        String rows[]=new String [4];
        //String[] rsarr=new String[result.size()];
        if(rs.next())
        { rs.previous();
        while(rs.next())
        {
          rows[0]=Integer.toString(rs.getInt(1));
          rows[1]=rs.getString(2);
          rows[2]=rs.getString(3);
          rows[3]=Integer.toString(rs.getInt(4));
          result.add(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]+" ");
          System.out.println("j="+j);

          for(String rsarr:result)
          {

            System.out.println(rsarr);
          }
          j++;
        }
      }
      else
      {
        result.add("No items found or items out of stock");
      }

    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
  }

public  static ArrayList<String> searchCustomer(String name)
  {
    ArrayList<String> result=new ArrayList<String>();
    try
    {
        cn=openConn();
        query="select * from customer where name like '%"+name+"%'";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        String rows[]=new String [5];
        if(rs.next())
        { rs.previous();
        while(rs.next())
        {
          rows[0]=Integer.toString(rs.getInt(1));
          rows[1]=rs.getString(2);
          rows[2]=rs.getString(3);
          rows[3]=Integer.toString(rs.getInt(4));
          result.add(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]+" ");
          for(String rsarr:result)
          {
            System.out.println(rsarr);
          }
        }
      }
      else
      {
        result.add("No items found");

    }
    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
  }

  public  static ArrayList<String> allCusTypes()
    {
      ArrayList<String> result=new ArrayList<String>();
      try
      {
          cn=openConn();
          query="select * from customer_type";
          st=cn.createStatement();
          rs=st.executeQuery(query);
          while(rs.next())
          {
            result.add(rs.getString(1));
          }
      }
      catch(Exception e){
        System.out.println(e);
      }
        //return cn;

      finally{
        try{
          rs.close();
          cn.close();
        }
        catch(Exception e){
          System.out.println();
        }
      }
      return result;
}
public  static String getStatus(String name)
  {
    String result=null;
    try
    {
        cn=openConn();
        query="select status from entity_status where entity_name='"+name+"'";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        while(rs.next())
        {
          result=rs.getString(1);
        }
        System.out.println(result);
    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
}

public  static ArrayList<String> itemsRented()
  {
    ArrayList<String> result=new ArrayList<String>();
    try
    {
        cn=openConn();
        query="select items.id,items.name,customer.id,customer.name,cus_item.date_rented from cus_item,items,customer where items.id=cus_item.iid and customer.id=cus_item.cid and cus_item.date_return is null ";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        while(rs.next())
        {
          result.add(Integer.toString(rs.getInt(1))+" "+rs.getString(2)+":"+Integer.toString(rs.getInt(3))+" "+rs.getString(4)+" "+rs.getString(4));
        }
        for(String s:result)
        {
          System.out.println("rented:"+s);
        }
    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
}
public  static ArrayList<String> getCategories()
  {
    ArrayList<String> result=new ArrayList<String>();
    try
    {
        cn=openConn();
        query="select * from category ";
        st=cn.createStatement();
        rs=st.executeQuery(query);
        while(rs.next())
        {
          result.add(rs.getString(1));
        }

    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
    return result;
}
//-------------------------------------------Add function---------------------------------------------

public  static void addCustomer(String name,String addr,String phno,String type)
  {
    try
    {
        cn=openConn();
        query="insert into customer(name,address,Phno,type) values('"+name+"','"+addr+"','"+phno+"','"+type+"') ";
        st=cn.createStatement();
        st.executeUpdate(query);
        System.out.println("customer inserted");

    }
    catch(Exception e){
      System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
  }
public  static void addItem(String name,String category,int qty)
  {
    try
    {
        cn=openConn();
        query="insert into items(name,category,qty_total,qty_available) values('"+name+"','"+category+"',"+qty+","+qty+")";
        st=cn.createStatement();
        st.executeUpdate(query);

        System.out.println("item added");
    }
      catch(Exception e){
        System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
  }
  public  static int addCusType(String name)
    {
      try
      {
          cn=openConn();
          st=cn.createStatement();
          query="select * from customer_type where type='"+name+"'";
          rs=st.executeQuery(query);
          if(rs.next())
          {
            return 1;
          }
          query="insert into customer_type(type) values('"+name+"')";
          st.executeUpdate(query);
          query="select count(distinct type) from customer_type";
          rs=st.executeQuery(query);
          int no=0;
          if(rs.next())
           no=rs.getInt(1);
          if(no>1)
          {
            setStatus("custype",Integer.toString(no));
          }
          System.out.println("type inserted");
          return 2;
      }
        catch(Exception e){
          System.out.println(e);
      }
        //return cn;

      finally{
        try{
          rs.close();
          cn.close();
        }
        catch(Exception e){
          System.out.println();
        }
      }
      return -1;
    }
    public  static void addCriteria(String category,String time,int no_of,String duration,float charge,String occurence)
      {
        try
        {
            cn=openConn();
            query="insert into category values('"+category+"')";
            st=cn.createStatement();
            st.executeUpdate(query);
            query="insert into criteria (category,	time,	no_of,	duration,	charge,	occurence) values('"+category+"','"+time+"',"+no_of+",'"+duration+"',"+charge+",'"+occurence+"')";
            st=cn.createStatement();
            st.executeUpdate(query);
            System.out.println("criteria inserted");
        }
          catch(Exception e){
            System.out.println(e);
        }
          //return cn;

        finally{
          try{
            rs.close();
            cn.close();
          }
          catch(Exception e){
            System.out.println();
          }
        }
      }

        public  static void itemRented(int cid ,int	iid	,String return_date,	float amnt_paid)
        {
          try
          {
              cn=openConn();
              query="insert into cus_item(cid,iid,date_rented,return_date,amnt_paid) values("+cid+","+iid+",CURDATE(),'"+return_date+"',"+amnt_paid+")";
              st=cn.createStatement();
              st.executeUpdate(query);
              query="update items set qty_available=qty_available-1 ";
              st=cn.createStatement();
              st.executeUpdate(query);


          }
            catch(Exception e){
              System.out.println(e);
          }
            //return cn;

          finally{
            try{
              rs.close();
              cn.close();
            }
            catch(Exception e){
              System.out.println();
            }
          }
        }
//-----------------------------------------------delete function-------------------------------------
public  static void delCustomer(int id)
  {
    try
    {
        cn=openConn();
        query="delete from customer where id="+id;
        st=cn.createStatement();
        st.executeUpdate(query);
    }
      catch(Exception e){
        System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
  }

public  static void delItem(int id)
  {
    try
    {
        cn=openConn();
        query="delete from customer where id="+id;
        st=cn.createStatement();
        st.executeUpdate(query);
    }
      catch(Exception e){
        System.out.println(e);
    }
      //return cn;

    finally{
      try{
        rs.close();
        cn.close();
      }
      catch(Exception e){
        System.out.println();
      }
    }
  }
//---------------------------------------------------set Status----------------------------------------

public static void setStatus(String entity,String val)
{
  try{
    cn=openConn();
    query="Update entity_status set value='"+val+"',status='set' where entity_name='"+entity+"'";
    st=cn.createStatement();
    st.executeUpdate(query);
    System.out.println("updated");


  }
  catch(Exception e){
    System.out.println(e);
  }
  //return cn;

  finally{
  try{
    rs.close();
    cn.close();
  }
  catch(Exception e){
    System.out.println();
  }
  }
}
public static void returnItem(int cid,int iid,float due)
{
  try{
    cn=openConn();
    query="Update cus_item set date_return=CURDATE(),amnt_paid=amnt_paid+"+due+" where iid="+iid+" and cid="+cid;
    st=cn.createStatement();
    st.executeUpdate(query);
    query="Update items set qty_available=qty_available+1 where id="+iid;
    st=cn.createStatement();
    st.executeUpdate(query);
    System.out.println("updated");


  }
  catch(Exception e){
    System.out.println(e);
  }
  //return cn;

  finally{
  try{
    rs.close();
    cn.close();
  }
  catch(Exception e){
    System.out.println();
  }
  }
}

//--------------------------------------------------generate Bill------------------------------------------------
public static float dueAmnt(int id)
{ float bill=0;
  try{
    int criId,no;
    String duration,retDate;
    cn=openConn();
    st=cn.createStatement();
    query="SELECT return_date<CURDATE() FROM `cus_item` ";
    rs=st.executeQuery(query);
    rs.next();
    boolean flag=false;
    if(rs.getInt(1)==1)
    {query="SELECT return_date FROM `cus_item` ";
    st=cn.createStatement();
    rs=st.executeQuery(query);
    rs.next();
    retDate=rs.getString(1);
    query="select criteria.id,no_of,duration,charge from criteria,items where criteria.category=items.category and time='After' and occurence='Once' order by no_of";
    st=cn.createStatement();
    rs=st.executeQuery(query);
    if(rs.next())
    {
      criId=rs.getInt(1);
      no=rs.getInt(2);
      duration=rs.getString(3);
      bill=rs.getFloat(4);
    }
    else
    {
      query="select criteria.id,no_of,duration,charge from criteria,items where criteria.category=items.category and time='After' and occurence='Per_day' order by no_of";
      st=cn.createStatement();
      rs=st.executeQuery(query);
      if(rs.next())
      {
        flag=true;
        criId=rs.getInt(1);
        no=rs.getInt(2);
        duration=rs.getString(3);
        bill=rs.getFloat(4);
      }
    }
    if(flag)
    {
      query="select DATEDIFF('"+retDate+"', CURDATE())";
      st=cn.createStatement();
      rs=st.executeQuery(query);
      if(rs.next())
      {
        bill*=rs.getInt(1);
      }
    }
  }
  //System.out.println("updated");



}
catch(Exception e){
  System.out.println(e);
}
//return cn;

finally{
  try{
    rs.close();
    cn.close();
  }
  catch(Exception e){
    System.out.println();
  }
}
System.out.println(bill);
return bill;

}
static String[] generate_Bill(int id)
{
String[] result=new String[2];
  try{
    int criId,no=0;
    boolean flag=false;
    String duration=null;
    float bill=0;
    cn=openConn();
    query="select criteria.id,no_of,duration,charge from criteria,items where criteria.category=items.category and time='Before' and occurence='Once' order by no_of";
    st=cn.createStatement();
    rs=st.executeQuery(query);
    if(rs.next())
    {
      criId=rs.getInt(1);
      no=rs.getInt(2);
      duration=rs.getString(3);
      bill=rs.getFloat(4);
    }
    else
    {
      query="select criteria.id,no_of,duration,charge from criteria,items where criteria.category=items.category and time='Before' and occurence='Per_day' order by no_of";
      st=cn.createStatement();
      rs=st.executeQuery(query);
      if(rs.next())
      {
        flag=true;
        criId=rs.getInt(1);
        no=rs.getInt(2);
        duration=rs.getString(3);
        bill=rs.getFloat(4);
    }
    }
    query="select DATE_ADD(CURDATE(),INTERVAL+"+no+" "+duration+")";
    st=cn.createStatement();
    rs=st.executeQuery(query);
    String retDate=null;
    if(rs.next())
    retDate=rs.getString(1);
    if(flag)
    {
      query="select DATEDIFF('"+retDate+"', CURDATE())";
      st=cn.createStatement();
      rs=st.executeQuery(query);
      if(rs.next())
      {
        bill*=rs.getInt(1);
      }
    }
    result[0]=retDate;
    result[1]=Float.toString(bill);

  }
  catch(Exception e){
    System.out.println(e);
  }
  //return cn;

  finally{
  try{
    rs.close();
    cn.close();

  }
  catch(Exception e){
    System.out.println();
  }
  }
  return result;
}

  public static void main(String[] args)
  {
    SqlFunc obj= new SqlFunc();
    Scanner scan=new Scanner(System.in);
    System.out.println("Enter name");
    String name=scan.next();


}

}
