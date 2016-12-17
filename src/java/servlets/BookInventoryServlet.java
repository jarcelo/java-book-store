
package servlets;

import business.ConnectionPool;
import business.Inventory;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
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
        String sqlStoreQuery = "";
        String sqlInventoryQuery = "";
        Store store = null;
        
        try {
            storeId = request.getParameter("store");
            msg += "Store ID: " + storeId;
            
            sqlStoreQuery = "SELECT * FROM stores WHERE storeID = '" + storeId + "'";
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            Statement storeStatement = conn.createStatement();
            ResultSet storeResultSet = storeStatement.executeQuery(sqlStoreQuery);
            
            if (storeResultSet.next()) {
                store = new Store();
                store.setStoreID(Long.parseLong(storeId));
                store.setStoreName(storeResultSet.getString("storeName"));
                store.setStoreAddress(storeResultSet.getString("storeAddr"));
            }
            
            request.getSession().setAttribute("store", store);
            
            sqlInventoryQuery = "SELECT bookinv.storeID, bookinv.bookID, booklist.title, booklist.price, bookinv.OnHand " +
                                "FROM bookinv, booklist " +
                                "WHERE bookinv.bookID = booklist.bookID AND bookinv.storeID = '" + storeId + "' " +
                                "ORDER BY bookID";
            
            Statement inventoryStatement = conn.createStatement();
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
            
            pool.freeConnection(conn);
            
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
