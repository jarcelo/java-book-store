
package servlets;

import business.Book;
import business.Store;
import business.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class UpdateBookOnHandServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/StoreSelection.jsp";
        String msg = "";
        String bookId = "";
        String bookName = "";
        int storeID = 0;
        String updateBookCountSQL = "";
        
        String dbURL = "jdbc:mysql://localhost:3306/HenryBooks_IS288";
        String dbUser = "root";
        String dbPwd = "uftbutefade1";
        
        int newBookCount = 0;
        try {
            Store store = (Store) request.getSession().getAttribute("store");
            bookId = request.getParameter("bookID");
            bookName = request.getParameter("title");
            User user = (User) request.getSession().getAttribute("user");
            newBookCount = Integer.parseInt(request.getParameter("quantity"));
            storeID = (int)(store.getStoreID());
            updateBookCountSQL = "UPDATE bookinv SET " +
                     " onHand = ? " + 
                     " WHERE bookID = ? AND storeID = ? ";
            
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            PreparedStatement ps = conn.prepareStatement(updateBookCountSQL);
            ps.setInt(1, newBookCount);
            ps.setString(2, bookId);
            ps.setInt(3, storeID);
                
            int rc = ps.executeUpdate();
            
            if (rc == 0) {
                msg += "Update failed: no changes <br>.";
            } else if (rc == 1) {
                msg += "Inventory for " + bookName + " at " + store.getStoreName() + " branch was successfully updated.<br>";
            } else {
                msg += "Warning: " + rc + " records updated.<br>";
            }
            
            request.setAttribute("bookId", bookId);
            request.setAttribute("quantity", newBookCount);
            request.setAttribute("userStoreId", storeID);
            
        } catch(SQLException e) {
            msg += "SQL Error: " + e.getMessage() + "<br>";
        } catch (Exception e) {
            msg += "Update Error.";
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