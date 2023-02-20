package com.sh.eas.controller;


import com.sh.eas.dto.common.StudentDTO;
import com.sh.eas.dto.request.StudentQueryReqDTO;
import com.sh.eas.dto.response.StudentQueryResDTO;
import com.sh.eas.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(path = "/inquire")
    public StudentQueryResDTO queryStudentInfoByName(@RequestBody StudentQueryReqDTO studentQueryReqDTO) {
        StudentQueryResDTO studentQueryResDTO = new StudentQueryResDTO();
        if (ObjectUtils.isNotEmpty(studentQueryReqDTO) && StringUtils.isNotEmpty(studentQueryReqDTO.getName())) {
            List<StudentDTO> studentDTOList = studentService.getStudentList(studentQueryReqDTO.getName());
            studentQueryResDTO
                    .setStudentList(studentDTOList)
                    .setPageSize(studentQueryReqDTO.getPageSize())
                    .setPageNumber(studentQueryReqDTO.getPageNumber())
                    .setTotalCount(studentDTOList.size());
        }

        return studentQueryResDTO;
    }

    @PostMapping(path = "/retrieve")
    public StudentDTO queryStudentInfoById(@RequestBody StudentQueryReqDTO studentQueryReqDTO) {
        if (null != studentQueryReqDTO && null != studentQueryReqDTO.getId()) {
            StudentDTO studentDTO = studentService.getStudent(studentQueryReqDTO.getId());
            return studentDTO;
        }
        return null;
    }

    @PostMapping(path = "/save")
    public Boolean createStudentInfo(@RequestBody StudentDTO studentDTO) {
        if (null != studentDTO) {
            return studentService.saveStudentInfo(studentDTO);
        }
        return false;
    }

    @PostMapping(path = "/update")
    public Boolean updateStudentInfo(@RequestBody StudentDTO studentDTO){
        if(null != studentDTO){
            return studentService.updateStudentInfo(studentDTO);
        }
        return false;
    }


    @PostMapping(path = "/delete")
    public Boolean deleteStudentInfo(@RequestBody StudentDTO studentDTO) {
        if (null != studentDTO && null != studentDTO.getId()) {
            return studentService.deleteStudentInfoById(studentDTO.getId());
        }
        return false;
    }

    @GetMapping(path = "/get")
    public StudentDTO getStudentInfoById(@Param("Id") Long id) {
        return studentService.getStudent(id);
    }
}
