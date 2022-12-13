package com.example.BerikoTry20;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends JpaRepository<User,String> {
//    @Query(value = "SELECT  FROM berikousers u where u.userName=?#{#name} and u.password=?#{#pwd}",nativeQuery = true)
//    public
        @Query(value = "SELECT user_name  from berikousers u where u.email_id=?#{#email} and u.password=?#{#pwd}",nativeQuery = true)
        public String loginRoute(@Param("email")String email,@Param("pwd") String pwd);

}
