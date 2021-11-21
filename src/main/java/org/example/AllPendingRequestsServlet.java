package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AllPendingRequestsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        Integer empId = Integer.valueOf(cookies[0].getValue());

        Employee employee = HibernateUtil.getEmployeeById(empId);

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
                "                        <a class=\"nav-link\" href=\"EmployeeLoginServlet\">Report</a>\n" +
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
                "                <p>Title:"  + employee.getTitle() + "</p>\n" +
                "                <p>Start Date:"  + employee.getEmpStartDate() + "</p>\n" +
                "                <p>Country:"  + employee.getCountry() + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"profile-info-item2 col-8\">\n" +
                "            <p>Email:" + employee.getEmail() + "</p>\n" +
                "            <p>Phone:" + employee.getPhone() + "</p>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"expense-btn d-flex justify-content-end\">\n" +
                "        <button class=\"btn btn-primary p2\"><a href=\"expense-form.html\">New Expense</a></button>\n" +
                "        <button class=\"btn btn-primary p2\"><a href=\"AllPendingRequestsServlet\">View Pending Requests</a></button>\n" +
                "    </div>\n" +
                "    <div class=\"expense-history\">\n" +
                "        <h2>Pending Expense Reimbursements</h2>\n" +
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
                "            <tbody>\n");

        List<Request> pendingRequests = HibernateUtil.getPendingRequests(empId);
        for(Request r : pendingRequests){
            out.println("<tr>" +
                    "<td>" + r.getRequestId() + "</td>" +
                    "<td>" + r.getIncStartDate() + "</td>" +
                    "<td>" + r.getIncEndDate() + "</td>" +
                    "<td>" + r.getPurpose() + "</td>" +
                    "<td>" + r.getExpense() + "</td>" +
                    "<td>" + r.getSubmitDate() + "</td>" +
                    "<td>" + r.getStatus() + "</td>");
        }
        out.println("</tbody>\n" +
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
    }
