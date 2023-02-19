package com.sh.eas.repository;

import com.sh.eas.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * 按照学生名称查询
     *
     * @param name 名称
     * @return 学生列表
     */
    @Query(value = "select * from eas_student where name like CONCAT('%',:name,'%') or nick_name like CONCAT('%',:name,'%') ", nativeQuery = true)
    List<Student> getStudentByName(@Param("name") String name);
}
