
package servlets;

import business.ConnectionPool;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
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
              
        int newBookCount = 0;
        try {
            Store store = (Store) request.getSession().getAttribute("store");
            bookId = request.getParameter("bookID");
            bookName = request.getParameter("title");
            String inputQuantity = request.getParameter("quantity").trim();
            try {
                if (inputQuantity.isEmpty()) {
                    msg += "* Please enter quantity<br>";
                    URL = "/Update.jsp";
                }
                else {
                    newBookCount = Integer.parseInt(inputQuantity);
                    if (newBookCount < 0) {
                        msg += "* Quantity must not be negative.";
                        URL = "/Update.jsp";
                    }
                    else {
                        storeID = (int)(store.getStoreID());
                        updateBookCountSQL = "UPDATE bookinv SET " +
                                 " onHand = ? " + 
                                 " WHERE bookID = ? AND storeID = ? ";

                        ConnectionPool pool = ConnectionPool.getInstance();
                        Connection conn = pool.getConnection();
                        PreparedStatement ps = conn.prepareStatement(updateBookCountSQL);
                        ps.setInt(1, newBookCount);
                        ps.setString(2, bookId);
                        ps.setInt(3, storeID);

                        int rc = ps.executeUpdate();

                        if (rc == 0) {
                            msg += "Update failed: no changes <br>.";
                        } else if (rc == 1) {
                            msg += "Inventory for <em>" + bookName + "</em> at " + store.getStoreName() + " branch was successfully updated<br>";
                        } else {
                            msg += "Warning: " + rc + " records updated.<br>";
                        }
                        
                        pool.freeConnection(conn);
                    }
                }
            } catch(SQLException e) {
                msg += "SQL Error: " + e.getMessage() + "<br>";
            } catch (Exception e) {
                msg += "Input error. Please enter a nonnegative number to update inventory. <br>";
            }
        } catch (Exception e) {
            msg += "Update failed." + e.getMessage();
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
