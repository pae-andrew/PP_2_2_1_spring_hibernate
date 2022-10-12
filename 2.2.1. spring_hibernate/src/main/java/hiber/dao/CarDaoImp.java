package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public Car getCarByModelAndSeries(String model, int series) {
        String hql = "from Car c where c.model = ?1 and c.series = ?2";
        Query query = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter(1, model)
                .setParameter(2, series);
        List<Car> car = query.getResultList();
        return car.get(0);
    }
}
