package com.sh.eas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sh.eas.dto.base.RequestBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentQueryReqDTO extends RequestBaseDTO {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("PageNumber")
    private Integer pageNumber;

    @JsonProperty("PageSize")
    private Integer pageSize;

}
