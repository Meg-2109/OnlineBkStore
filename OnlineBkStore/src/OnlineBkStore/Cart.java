package OnlineBkStore;

import java.sql.*;
import java.util.*;

public class Cart {

	ArrayList items = new ArrayList();
	   
	   public Item find(String isbn)
	   {
	     Iterator itr = items.iterator();
	     Item itm;
	     while ( itr.hasNext())
	     {
	       itm =  (Item) itr.next();
	       if ( itm.getIsbn().equals(isbn))
	       {
	          return itm;
	       }
	     }  // end of while

	     return null;
	   }
	   
	   // adds an item if not already existing
	  // otherwise add 1 to qty
	   public void addItem(String isbn )
	   {
	     //check whether isbn is already present
	     Item item =  find(isbn);
	     if ( item != null)
	       item.addQty(1);
	     else
	     {
	       // get detais from Books tables
	     
	       Connection con =null;
	       OnlineBkStore.User  user  = new OnlineBkStore.User();
	       try
	       {
	        con = user.getConnection();
	        PreparedStatement ps = con.prepareStatement("select title,price from books where isbn = ? ");
	        ps.setString(1,isbn);

	        ResultSet rs  = ps.executeQuery();
	        if ( rs.next())
	        {
	          item = new Item(isbn, rs.getString(1), rs.getInt(2) );
	          items.add(item);
	        }

	        rs.close();
	        ps.close();

	       }
	       catch(Exception ex)
	       {
	         System.out.println(ex.getMessage());
	       }
	       finally
	       {  
	         try {con.close();} catch(Exception ex) {}
	       }
	     }  // end of else
	   }       

	   public  ArrayList  getItems()
	   {  return items; }

	   public  void removeItem(String isbn)
	   {
	       Item item = find(isbn);
	       if  ( item != null)
	           items.remove(item);
	   } // end or removeItem

	    public void clearAll()
	    {
	       items.clear();
	   }

	   public void updateQty(String isbn, int qty)
	   {
	        Item  item = find(isbn);
	        if ( item != null)
	          item.setQty(qty);
	   } // end of updateQty()


}
