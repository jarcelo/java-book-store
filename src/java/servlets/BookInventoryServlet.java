
package servlets;

import business.Book;
import business.Inventory;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class BookInventoryServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/Inventory.jsp";
        String msg = "";
        String storeId = "";
        String storeName = "";
        String sqlStoreQuery = "";
        String sqlInventoryQuery = "";
        Store store = null;
        
        String dbURL = "jdbc:mysql://localhost:3306/HenryBooks_IS288";
        String dbUser = "root";
        String dbPwd = "uftbutefade1";
        // get the inventories of the selected branch
        try {
            //Store s = (Store) request.getSession().getAttribute("s");
            storeId = request.getParameter("store");
            storeName = request.getParameter("storeName");
            msg += "Store ID: " + storeId;
            // write query for the store details
            sqlStoreQuery = "SELECT * FROM stores WHERE storeID = '" + storeId + "'";
            Connection storeConnection = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            Statement storeStatement = storeConnection.createStatement();
            ResultSet storeResultSet = storeStatement.executeQuery(sqlStoreQuery);
            if (storeResultSet.next()) {
                store = new Store();
                store.setStoreID(Long.parseLong(storeId));
                store.setStoreName(storeResultSet.getString("storeName"));
                store.setStoreAddress(storeResultSet.getString("storeAddr"));
            }
            //request.setAttribute("store", store);
            request.getSession().setAttribute("store", store);
            
            // Write query for book inventory
            sqlInventoryQuery = "SELECT bookinv.storeID, bookinv.bookID, booklist.title, booklist.price, bookinv.OnHand " +
                                "FROM bookinv, booklist " +
                                "WHERE bookinv.bookID = booklist.bookID AND bookinv.storeID = '" + storeId + "' " +
                                "ORDER BY bookID";
            
            Connection inventoryConnection = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            Statement inventoryStatement = inventoryConnection.createStatement();
            ResultSet inventoryResultSet = inventoryStatement.executeQuery(sqlInventoryQuery);

            ArrayList<Inventory> invs = new ArrayList<>();
            while (inventoryResultSet.next()) {
                Inventory i = new Inventory();
                i.setStoreId(inventoryResultSet.getLong("storeID"));
                i.setBookId(inventoryResultSet.getString("bookID"));
                i.setNumberOfBooksOnHand(inventoryResultSet.getLong("OnHand"));
                i.setBookTitle(inventoryResultSet.getString("title"));
                i.setPrice(inventoryResultSet.getString("price"));
                invs.add(i);
            }
            inventoryResultSet.last();
            request.setAttribute("invs", invs);
            String itemCount = inventoryResultSet.getRow() + " books found!";
            request.setAttribute("count", itemCount);
        } catch (Exception e) {
            msg += "Error: " + e.getMessage();
        }
        
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
