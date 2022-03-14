package cn.jee.jee2022_jpa_em;

import cn.jee.jee2022_jpa_em.entity.Customer;
import cn.jee.jee2022_jpa_em.entity.CustomerDTO;
import cn.jee.jee2022_jpa_em.entity.dao.CustomerDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


/**
 * @Author ChinoNya
 * @create 2022/3/13 21:52
 */

@SpringBootTest
@Slf4j
public class CustomerTests {
  @Autowired
  EntityManager em;

  /**
   * 添加客户
   * 禁止事务回滚
   */
  @Test
  @Transactional
  @Rollback(false)
  void addCustomer() {
    Random rand = new Random();
    String[] firstName = {"张", "王", "李", "林", "陈", "赵", "钱", "孙"};
    String[] lastName = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "零"};
    String[] customerSex = {"男", "女"};
    for (int i = 0; i < 10; i++) {
      Customer customer = new Customer();
      String name = firstName[rand.nextInt(firstName.length)] + lastName[rand.nextInt(lastName.length)];
      customer.setName(name);
      String sex = customerSex[rand.nextInt(2)];
      customer.setSex(sex);
      customer.setAge(18 + rand.nextInt(60 - 18));
      em.persist(customer);
    }
  }

  /**
   * 根据年龄删除客户
   * 允许事务回滚
   */
  @Test
  @Transactional
  void deleteByAge() {
    int row = em.createQuery("delete from Customer where age>=30").executeUpdate();
    log.debug("{}", row);
  }

  /**
   * 根据id删除客户
   * 允许事务回滚
   */
  @Test
  @Transactional
  void deleteById() {
    Customer customer = em.find(Customer.class, 1L);
    log.debug("{}", customer);
    em.remove(customer);
  }

  /**
   * 查询年龄大于30的林姓女性客户
   * 使用CriteriaBuilder构建复杂where条件
   */
  @Test
  @Transactional
  void selectByCriteriaBuilder() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    //定义where条件
    Root<Customer> root = cq.from(Customer.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb.like(root.get("name"), "林%"));
    predicates.add(cb.equal(root.get("sex"), "女"));
    predicates.add(cb.ge(root.get("age"), 30));
    cq.where(predicates.toArray(Predicate[]::new));

    cq.orderBy(cb.asc(root.get("id")));

    List<Customer> res = em.createQuery(cq).getResultList();
    log.debug("{}", res);
  }

  /**
   * 显示所有客户
   */
  @Test
  @Transactional
  void selectAll() {
    List<Customer> res = em.createQuery("from Customer", Customer.class).getResultList();
    log.debug("{}", res);
  }

  /**
   * 模糊查询林姓客户
   */
  @Test
  @Transactional
  void selectByLastName() {
    List<Customer> res = em.createQuery("from Customer where name like '林%'").getResultList();
    log.debug("{}", res);
  }

  /**
   * 使用Tuple返回部分值
   */
  @Test
  @Transactional
  void selectByTuple() {
    List<Tuple> res = em.createQuery("select customer.id,customer.name from Customer customer", Tuple.class).getResultList();
    res.forEach(tuple -> log.debug("id:{} name:{}", tuple.get(0), tuple.get(1)));
  }


  @Autowired
  private CustomerDao customerDao;

  /**
   * 用DTO返回部分值
   * 返回id、姓名
   */
  @Test
  @Transactional
  void selectByDTO(){
    Optional<CustomerDTO> customerDTO=customerDao.findById(1L,CustomerDTO.class);
    log.debug("{}",customerDTO);
  }
}
