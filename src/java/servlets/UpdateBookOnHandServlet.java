
package servlets;

import business.InventoryDB;
import business.Store;
import java.io.IOException;
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
        long storeID = 0;
        long newBookCount = 0;
        
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
                    newBookCount = Long.parseLong(inputQuantity);
                    if (newBookCount < 0) {
                        msg += "* Quantity must not be negative.";
                        URL = "/Update.jsp";
                    }
                    else {
                        storeID = (long)(store.getStoreID());
                        int rowsUpdated = InventoryDB.updateBookOnHandInventory(newBookCount, bookId, storeID);
                        if (rowsUpdated == 1) {
                            msg += "Inventory for <em>" + bookName + "</em> at " + store.getStoreName() + 
                                    " branch was successfully updated<br>";
                        }
                        else if (rowsUpdated == 0) {
                            msg += "Update failed: no changes made<br>";
                        }
                        else {
                            msg += "Warning: " + rowsUpdated + " records updated.<br>";
                        }
                    }
                }
            } catch (Exception e) {
                msg += "Error processing input: " + e.getMessage();
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
