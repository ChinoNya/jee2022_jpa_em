package cn.jee.jee2022_jpa_em.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author ChinoNya
 * @create 2022/3/14 10:39
 */
@Data @ToString
@AllArgsConstructor
public class CustomerDTO {

  private Long id;

  private String name;

}
