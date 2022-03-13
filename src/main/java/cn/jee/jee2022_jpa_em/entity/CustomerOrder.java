package cn.jee.jee2022_jpa_em.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ChinoNya
 * @create 2022/3/13 21:22
 */
@Entity @Getter @Setter
public class CustomerOrder {
  /**
   * 订单id
   * 主键
   */
  @Id
  private Long id;

  /**
   * 订单名称
   */
  private String name;

  /**
   * 订单创建时间
   */
  private LocalDate date;

  @ManyToOne
  @ToString.Exclude
  private Customer customer;

  /**
   * 订单内商品
   */
  @OneToMany(mappedBy = "customerOrder")
  @ToString.Exclude
  List<Item> items=new ArrayList<>();
}
