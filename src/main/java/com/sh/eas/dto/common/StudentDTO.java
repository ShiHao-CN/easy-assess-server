package com.sh.eas.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sh.eas.constant.EasConstants;
import com.sh.eas.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentDTO extends BaseDTO {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("NickName")
    private String nickName;

    @JsonProperty("BirthDate")
    @JsonFormat(pattern = EasConstants.DATE_FORMAT, timezone = "TIME_ZONE")
    private Date birthDate;
}
