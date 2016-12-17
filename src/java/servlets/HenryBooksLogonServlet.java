
package servlets;

import business.Store;
import business.StoreDB;
import business.User;
import business.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class HenryBooksLogonServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/Logon.jsp";
        String msg = "", sql = "", userID = "";
        long passattempt;
        User user = null;
     
        try {
            userID = request.getParameter("userid").trim();
            passattempt = Long.parseLong(request.getParameter("password").trim());
            user = UserDB.getMemberById(Long.parseLong(userID));
            if (user == null) {
                msg = "User can't be found.";
            }
            else {
                user.setPasswordAttempt(passattempt);
                if (!user.isAuthenticated()) {
                    msg = "Member found but not authenticated";
                }
                else {
                    msg = "Member authenticated";
                    URL = "/StoreSelection.jsp";
                    request.getSession().setAttribute("user", user);
                    // get the store list
                    List<Store> stores = new ArrayList<>();
                    stores = StoreDB.getStores();
                    request.getSession().setAttribute("stores", stores);
                }
            }
        } 
        catch(Exception e) {
             msg = "Servlet error :" + e.getMessage();
        }
        
        request.setAttribute("msg", msg);
        // Add cookie for userID
        Cookie uid = new Cookie("userid", userID);
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URL);
        dispatcher.forward(request, response);
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
