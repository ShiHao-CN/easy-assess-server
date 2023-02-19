package com.sh.eas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sh.eas.dto.base.ResponseBaseDTO;
import com.sh.eas.dto.common.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentQueryResDTO extends ResponseBaseDTO {

    @JsonProperty("TotalCount")
    private Integer totalCount;

    @JsonProperty("PageNumber")
    private Integer pageNumber;

    @JsonProperty("PageSize")
    private Integer pageSize;

    @JsonProperty("StudentList")
    private List<StudentDTO> studentList;


}
