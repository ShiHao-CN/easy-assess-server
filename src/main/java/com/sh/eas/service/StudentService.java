package com.sh.eas.service;

import com.sh.eas.base.exception.EasBusinessException;
import com.sh.eas.domain.Student;
import com.sh.eas.dto.common.StudentDTO;
import com.sh.eas.repository.StudentRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getStudentList(String name) {

        List<Student> students = studentRepository.getStudentByName(name);
        logger.debug("查询结束");
        if (CollectionUtils.isNotEmpty(students)) {
            logger.debug("查询的学生为：{}", StringUtils.join(students.stream().map(Student::toString).collect(Collectors.toList())));
            ModelMapper modelMapper = new ModelMapper();
            return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }


    public Boolean createStudentInfo(StudentDTO studentDTO) {
        if (null != studentDTO) {
            ModelMapper modelMapper = new ModelMapper();
            Student student = modelMapper.map(studentDTO, Student.class);
            student.setCreateAt(Date.valueOf(LocalDate.now()));
            student.setCreateBy("testuser");
            student.setUpdateAt(Date.valueOf(LocalDate.now()));
            student.setUpdateBy("testuser");
            Student savedStudent = studentRepository.save(student);
            return null != savedStudent.getId();
        }
        return false;
    }

    public Boolean deleteStudentInfoById(Long id) {
        if (null != id) {
            boolean exists = studentRepository.existsById(id);
            if (exists) {
                studentRepository.deleteById(id);
                return true;
            } else {
                throw new EasBusinessException("学生信息不存在");
            }
        }
        return false;
    }

    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.getReferenceById(id);
        if (null != student.getId()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(student, StudentDTO.class);
        }
        return null;
    }

}
