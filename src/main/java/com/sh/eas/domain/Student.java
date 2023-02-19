package com.sh.eas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "eas_student", schema = "easy_assess")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "version")
    private Long version;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
