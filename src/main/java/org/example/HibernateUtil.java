package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil {
    //add employees to employee table
    public static void addEmployee(Employee employee){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();

        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        session.save(employee);
        t.commit();
        session.close();
    }
    //update employee in database
    public static void updateEmployee(Employee employee){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();

        //get the connection
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        session.update(employee);
        txn.commit();
        session.close();
    }

    //get employee by email and password
    public static Employee getEmployee(String email, String password){
        Employee employee = new Employee();
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        String hql = "from Employee where email= :email AND password= :password";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        List employees = query.list();
        employee = (Employee) employees.get(0);

        txn.commit();
        session.close();
        return employee;

    }
    //get employee by id
    public static Employee getEmployeeById(int empId){
        Employee employee = new Employee();
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        String hql = "from Employee where empId= :empId";
        Query query = session.createQuery(hql);
        query.setParameter("empId", empId);
        List employees = query.list();
        employee = (Employee) employees.get(0);

        txn.commit();
        session.close();
        return employee;

    }
    //add new request into request table
    public static void addRequest(int empId, Request request){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        Employee employee = session.load(Employee.class, empId);
        request.setEmployee(employee);
        session.save(request);
        txn.commit();
        session.close();

    }
    //get all requests from request table for a specific employee
    public static List<Request> getAllRequests(int empId){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        String hql = "from Request where empId= :empId";
        Query query = session.createQuery(hql);
        query.setParameter("empId", empId);
        List requests = query.list();

        txn.commit();
        session.close();

        return requests;
    }
    //get only requests will pending status from specific employee
    public static List<Request> getPendingRequests(int empId){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        String hql = "from Request where empId= :empId AND status= :status";
        Query query = session.createQuery(hql);
        query.setParameter("empId", empId);
        query.setParameter("status", "pending");
        List requests = query.list();

        txn.commit();
        session.close();

        return requests;
    }
    //get all employee's requests
    public static List<Request> getRequestHistory() {
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        String hql = "from Request";
        Query query = session.createQuery(hql);
        List requests = query.list();

        txn.commit();
        session.close();

        return requests;
    }
    //get all employee requests will pending status
    public static List<Request> getAllPendingRequests() {
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        String hql = "from Request where status= :status";
        Query query = session.createQuery(hql);
        query.setParameter("status", "pending");
        List requests = query.list();

        txn.commit();
        session.close();

        return requests;
    }
    //update the status of requests
    public static void updateRequestStatus(int requestId, String status){
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        Request request = session.load(Request.class, requestId);
        request.setStatus((status));
        session.update(request);

        txn.commit();
        session.close();
    }

    //get all requests of a specific employee
    public static List<Request> getRequestsByEmployeeId(int empId) {
        SessionFactory sessionFactory = ConnectionFactory.sessionFactory();
        Session session = sessionFactory.openSession();

        Transaction txn = session.beginTransaction();

        String hql = "from Request where empId= :empId";
        Query query = session.createQuery(hql);
        query.setParameter("empId", empId);
        List requests = query.list();

        txn.commit();
        session.close();

        return requests;
    }
}
