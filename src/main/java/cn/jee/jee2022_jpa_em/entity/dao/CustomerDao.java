package cn.jee.jee2022_jpa_em.entity.dao;

import cn.jee.jee2022_jpa_em.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @Author ChinoNya
 * @create 2022/3/14 10:41
 */
public interface CustomerDao extends JpaRepository<Customer,Long> {

  /**
   * 根据id查询
   * @param id
   * @param type
   * @param <T>
   * @return
   */
  <T> Optional<T> findById(Long id,Class<T> type);
}
