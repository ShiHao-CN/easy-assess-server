package com.sh.eas.controller;


import com.sh.eas.dto.common.StudentDTO;
import com.sh.eas.dto.request.StudentQueryReqDTO;
import com.sh.eas.dto.response.StudentQueryResDTO;
import com.sh.eas.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(path = "/inquire")
    public StudentQueryResDTO queryStudentInfo(@RequestBody StudentQueryReqDTO studentQueryReqDTO) {
        StudentQueryResDTO studentQueryResDTO = new StudentQueryResDTO();
        if (ObjectUtils.isNotEmpty(studentQueryReqDTO) && StringUtils.isNotEmpty(studentQueryReqDTO.getQueryType())) {
            switch (studentQueryReqDTO.getQueryType()) {
                case "1":
                    // by id
                    break;
                case "2":
                    // by name
                    List<StudentDTO> studentDTOList = studentService.getStudentList(studentQueryReqDTO.getName());
                    studentQueryResDTO
                            .setStudentList(studentDTOList)
                            .setPageSize(studentQueryReqDTO.getPageSize())
                            .setPageNumber(studentQueryReqDTO.getPageNumber())
                            .setTotalCount(studentDTOList.size());

            }
        }

        return studentQueryResDTO;
    }

}
