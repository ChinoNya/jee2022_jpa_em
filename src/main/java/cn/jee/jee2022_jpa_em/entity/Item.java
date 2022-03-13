package cn.jee.jee2022_jpa_em.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @Author ChinoNya
 * @create 2022/3/13 21:23
 */
@Entity @Getter @Setter
public class Item {
  /**
   * 商品id
   * 主键
   */
  @Id
  private Long id;

  /**
   * 商品名称
   */
  private String name;

  /**
   * 商品价格
   */
  private double price;

  /**
   * 商品描述
   */
  private String description;

  @ManyToOne
  @ToString.Exclude
  private Order order;
}
