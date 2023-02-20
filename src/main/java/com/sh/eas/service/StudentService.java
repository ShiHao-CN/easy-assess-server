package com.sh.eas.service;

import com.sh.eas.core.exception.EasBusinessException;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    public Boolean saveStudentInfo(StudentDTO studentDTO) {
        if (null != studentDTO) {
            ModelMapper modelMapper = new ModelMapper();
            Student student = modelMapper.map(studentDTO, Student.class);
            student.setCreateAt(LocalDateTime.now());
            student.setCreateBy("testuser");
            student.setUpdateAt(LocalDateTime.now());
            student.setUpdateBy("testuser");
            Student savedStudent = studentRepository.save(student);
            return null != savedStudent.getId();
        }
        return false;
    }

    public Boolean updateStudentInfo(StudentDTO studentDTO) {
        if (null != studentDTO && null != studentDTO.getId()) {
            Optional<Student> oriStudent = studentRepository.findById(studentDTO.getId());
            if (oriStudent.isPresent()) {
                // TODO 增量更新，需要写工具类
                ModelMapper modelMapper = new ModelMapper();
                Student updStudent = modelMapper.map(oriStudent.get(), Student.class);
                modelMapper.map(studentDTO, updStudent);
                updStudent.setUpdateBy("updater");
                updStudent.setUpdateAt(LocalDateTime.now());
                Student savedStudent = studentRepository.save(updStudent);
                return null != savedStudent.getId();
            } else {
                throw new EasBusinessException("学生信息不存在!");
            }

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
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(student.get(), StudentDTO.class);
        }
        return null;
    }

}
