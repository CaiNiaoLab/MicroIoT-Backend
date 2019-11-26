package com.frame4j.kafka;

import com.frame4j.base.entity.DataEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * KAFKA数据数据采集类
 *
 * @author MJ
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "SYS_KAFKA_DATA")
@Entity
public class KafkaData extends DataEntity {
    private static final long serialVersionUID = 1L;
    // 字典key
    private String skey;
    // 字典value
    private String svalue;
    // 预留字段1
    private String string1;
    // 预留字段2
    private String string2;
    // 预留字段3
    private String string3;
    // 预留字段4
    private String string4;
    // 预留字段5
    private String string5;
    // 预留字段6
    private Integer integer1;
    // 预留字段7
    private Integer integer2;
    // 预留字段8
    private Integer integer3;
    // 预留字段9
    private Integer integer4;
    // 预留字段10
    private Integer integer5;
    // 预留字段11
    private Date data1;
    // 预留字段12
    private Date data2;
    // 预留字段13
    private Date data3;
    // 预留字段14
    private Date data4;
    // 预留字段15
    private Date data5;
}
