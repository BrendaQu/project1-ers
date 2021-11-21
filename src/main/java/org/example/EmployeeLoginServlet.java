package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Employee employee = new Employee();
        employee = HibernateUtil.getEmployee(email, password);
        String empId = String.valueOf(employee.getId());

        Cookie cookie = new Cookie("id", empId);
        response.addCookie(cookie);

        if(employee.getType().equals("regular")) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                    "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">\n" +
                    "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                    "    <title>Employee Page</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <nav class=\"navbar navbar-expand-lg navbar-dark\">\n" +
                    "        <div class=\"container-fluid\">\n" +
                    "            <a class=\"navbar-brand\" href=\"#\">One Inc.</a>\n" +
                    "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"\n" +
                    "                data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"\n" +
                    "                aria-label=\"Toggle navigation\">\n" +
                    "                <span class=\"navbar-toggler-icon\"></span>\n" +
                    "            </button>\n" +
                    "            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                    "                <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">\n" +
                    "                    <li class=\"nav-item\">\n" +
                    "                        <a class=\"nav-link\" href=\"employee-page.html\"></a>\n" +
                    "                    </li>\n" +
                    "                    <li class=\"nav-item\">\n" +
                    "                        <a class=\"nav-link\" href=\"index.html\">Logout</a>\n" +
                    "                    </li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </nav>\n" +
                    "    <div class=\"profile-wrapper\">\n" +
                    "        <div class=\"row\">\n" +
                    "        <div class=\"profile-img col-2\">\n" +
                    "            <img src=\"img/profile.jpg\">\n" +
                    "            <h3>" + employee.getName() + "</h3>\n" +
                    "        </div>\n" +
                    "        <div class=\"profile-info-item1 col-2\">\n" +
                    "                <p>Title: " + employee.getTitle() + "</p>\n" +
                    "                <p>Start Date: " + employee.getEmpStartDate() + "</p>\n" +
                    "                <p>Country: " + employee.getCountry() + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"profile-info-item2 col-8\">\n" +
                    "            <p>Email:" + employee.getEmail() + "</p>\n" +
                    "            <p>Phone: " + employee.getPhone() + "</p>\n" +
                    "        </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"expense-btn d-flex justify-content-end\">\n" +
                    "        <button class=\"btn btn-primary p2\"><a href=\"expense-form.html\">New Expense</a></button>\n" +
                    "        <button class=\"btn btn-primary p2\"><a href=\"employee-page-pending.html\">View Pending Requests</a></button>\n" +
                    "    </div>\n" +
                    "    <div class=\"expense-history\">\n" +
                    "        <h2>Expense Reimbursement History</h2>\n" +
                    "        <table class=\"table\">\n" +
                    "            <thead>\n" +
                    "              <tr>\n" +
                    "                <th>Request ID</th>\n" +
                    "                <th>Incurred Start Date</th>\n" +
                    "                <th>Incurred End Date</th>\n" +
                    "                <th>Purpose</th>\n" +
                    "                <th>Reported Expense</th>\n" +
                    "                <th>Submit Date</th>\n" +
                    "                <th>Status</th>\n" +
                    "              </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n" +
                    "              <tr>\n" +
                    "                <td>1</td>\n" +
                    "                <td>01/15/20</td>\n" +
                    "                <td>01/17/20</td>\n" +
                    "                <td>Florida Business Travel</td>\n" +
                    "                <td>$500.30</td>\n" +
                    "                <td>01/19/20</td>\n" +
                    "                <td>Approved</td>\n" +
                    "              </tr>\n" +
                    "              <tr>\n" +
                    "                <td>2</td>\n" +
                    "                <td>02/01/21</td>\n" +
                    "                <td>02/01/21</td>\n" +
                    "                <td>Client Dinner</td>\n" +
                    "                <td>$100.80</td>\n" +
                    "                <td>02/02/21</td>\n" +
                    "                <td>Approved</td>\n" +
                    "              </tr>\n" +
                    "              <tr>\n" +
                    "                <td>3</td>\n" +
                    "                <td>11/17/21</td>\n" +
                    "                <td>11/18/21</td>\n" +
                    "                <td>Confernce Expense</td>\n" +
                    "                <td>$300.30</td>\n" +
                    "                <td>011/19/21</td>\n" +
                    "                <td>Pending</td>\n" +
                    "              </tr>\n" +
                    "            </tbody>\n" +
                    "          </table>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <footer>\n" +
                    "        <h6>© Copyright 2021, One Inc.</h6>\n" +
                    "    </footer>\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n" +
                    "</body>\n" +
                    "</html>");
        }
        out.close();
        }

    }
