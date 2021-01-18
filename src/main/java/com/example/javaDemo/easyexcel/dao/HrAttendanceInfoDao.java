package com.example.javaDemo.easyexcel.dao;

import com.example.javaDemo.easyexcel.domain.AttendanceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HrAttendanceInfoDao {
    int save(@Param("hrAttendanceInfos") List<AttendanceInfo> hrAttendanceInfos);
    List<AttendanceInfo> list(Map<String,Object> map);
}
