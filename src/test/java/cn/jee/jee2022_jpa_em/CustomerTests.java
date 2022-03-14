package cn.jee.jee2022_jpa_em;

import cn.jee.jee2022_jpa_em.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
    log.debug("{}",row);
  }

  /**
   * 根据id删除客户
   * 允许事务回滚
   */
  @Test
  @Transactional
  void deleteById() {
    Customer customer=em.find(Customer.class,1L);
    log.debug("{}",customer);
    em.remove(customer);
  }


}
