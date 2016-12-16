
package servlets;

import business.Book;
import business.ConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class UpdateInventoryServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/Update.jsp";
        String msg = "";
        String bookId = "";
        String selectBookSQL = "";
        
        Book book = null;
        try {
            bookId = request.getParameter("bookCode");
            if (bookId.isEmpty()) {
                msg += "Viewing book details failed. BookID is invalid or empty.<br>";
            }
            else {
                selectBookSQL = "SELECT * FROM booklist " +
                                "WHERE bookID ='" + bookId +"'";
                
                ConnectionPool pool = ConnectionPool.getInstance();
                Connection conn = pool.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(selectBookSQL);

                if (resultSet.next()) {
                     book = new Book();
                     book.setBookId(resultSet.getString("bookID"));
                     book.setTitle(resultSet.getString("title"));
                     book.setAuthor(resultSet.getString("author")); 
                }
            }
            
            if (!msg.isEmpty()) {
                URL = "/StoreSelection.jsp";
            }
            //TODO retrieve the current book quantity from the selected branch
        } catch(SQLException e) {
            msg = "SQL Exception " + e.getMessage();
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }
        request.getSession().setAttribute("book", book);
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
