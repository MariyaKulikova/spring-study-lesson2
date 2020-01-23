package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Customer;
import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.ProductsOrdered;
import ru.geekbrains.entity.ProductsOrderedId;

import java.util.List;

public class DAO {

    private SessionFactory factory;
    private Session session;

    public DAO() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductsOrdered.class)
                .addAnnotatedClass(ProductsOrderedId.class)
                .buildSessionFactory();
        this.session = factory.getCurrentSession();
    }

//    public List<Product> getOrderedProductsByCustomerId(Long id) {
//        session.beginTransaction();
//        Customer customer = session.get(Customer.class, id);
//        return customer.getOrderedProductsList();
//    }
//
//    public List<Customer> getCustomersOrderedProductById(Long id) {
//        session.beginTransaction();
//        Product product = session.get(Product.class, id);
//        return product.getCustomersOrderedProductList();
//    }

    public List<ProductsOrdered> getOrderedProductsByCustomerId(Long id) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        return customer.getOrderedProductsList();
    }

    public List<ProductsOrdered> getCustomersOrderedProductById(Long id) {
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        return product.getCustomersOrderedProduct();
    }

    public void deleteCustomer(Customer customer) {
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
    }

    public void deleteProduct(Product product) {
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
    }

    public void deleteCustomerById(Long id) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        session.getTransaction().commit();
    }

    public void deleteProductById(Long id) {
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
    }

    public void saveProduct(Product product) {
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    public void close() {
        session.close();
        factory.close();
    }
}
