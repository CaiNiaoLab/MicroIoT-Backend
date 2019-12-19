package com.frame4j.mq.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ApplicationMapper {


    @Insert("insert into application(id,uuids,json) values(#{id},#{uuids},#{json})")
    boolean insertApplication(Map application);

    @Select("select id,uuids,json from application where id = #{id}")
    Map getApplication(String id);

}
