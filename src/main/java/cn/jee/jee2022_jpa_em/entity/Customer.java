package cn.jee.jee2022_jpa_em.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ChinoNya
 * @create 2022/3/13 21:21
 */

@Entity @Getter @Setter
public class Customer {

  /**
   * 用户id
   * 主键
   */
  @Id
  @GeneratedValue
  private Long id;

  /**
   * 用户姓名
   */
  private String name;

  /**
   * 用户性别
   */
  private String sex;

  /**
   * 用户年龄
   */
  private int age;

  /**
   * 用户订单
   */
  @OneToMany(mappedBy = "customer")
  @ToString.Exclude
  private List<CustomerOrder> customerOrders =new ArrayList<>();
}
