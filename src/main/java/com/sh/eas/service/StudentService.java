package com.sh.eas.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getStudentList(String name) {

        List<Student> students = studentRepository.getStudentByName(name);
        logger.debug("查询结束");
        if (CollectionUtils.isNotEmpty(students)) {
            logger.debug("查询的学生为：{}", StringUtils.join(students.stream().map(Student::toString).collect(Collectors.toList())));
            ModelMapper modelMapper = new ModelMapper();
            List<StudentDTO> studentDTOList = students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
            return studentDTOList;
        }

        return new ArrayList<>();
    }
}
