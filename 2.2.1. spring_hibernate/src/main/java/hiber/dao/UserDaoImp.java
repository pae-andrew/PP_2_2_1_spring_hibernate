package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Autowired
   private CarService carService;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Car car = carService.getCarByModelAndSeries(model, series);
      String hql = "from User u where u.car.model = ?1 and u.car.series = ?2";
      Query query = sessionFactory.getCurrentSession().createQuery(hql)
              .setParameter(1, model)
              .setParameter(2, series);
      List<User> users = query.getResultList();
      return users.get(0);
   }

}
