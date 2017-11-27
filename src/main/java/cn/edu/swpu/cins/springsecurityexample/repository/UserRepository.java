package cn.edu.swpu.cins.springsecurityexample.repository;

import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User getUserById(Long id);

    User save(User user);

}
