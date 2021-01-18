package com.example.javaDemo.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AttendanceInfo {
    //年月
    @ExcelProperty(value = "月份")
    private String yearmonth;
    //四级部门
    @ExcelProperty(value = "四级部门")
    private String org4;
    //五级部门
    @ExcelProperty(value = "五级部门")
    private String org5;
    //六级部门
    @ExcelProperty(value = "六级部门")
    private String org6;
    //ERP工号
    @ExcelProperty(value = "工号")
    private String pernr;
    //姓名
    @ExcelProperty(value = "姓名")
    private String name;
    //职位
    @ExcelProperty(value = "岗位")
    private String position;
    //入职日期
    @ExcelProperty(value = "入职日期" )
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date entryDate;
    //离职日期
    @ExcelProperty(value = "离职日期" )
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date endDate;
    //计薪起日
    @ExcelProperty(value = "计薪起日" )
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date salaryBeg;
    //计薪止日
    @ExcelProperty(value = "计薪止日" )
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date salaryEnd;
    //当月实际计薪天数
    @ExcelProperty(value = "计薪天数")
    private String salaryNum;
    //事假天数
    @ExcelProperty(value = "事假天数")
    private String personalLeaveDays;
    //病假天数
    @ExcelProperty(value = "病假天数")
    private String sickLeaveDays;
    //旷工天数
    @ExcelProperty(value = "旷工天数")
    private String absenteeismDay;
    //迟到分钟数
    @ExcelProperty(value = "迟到早退分钟数")
    private String lateMinute;
    //迟到次数
    @ExcelProperty(value = "迟到早退次数")
    private String lateTimes;
    //忘打卡次数
    @ExcelProperty(value = "补打卡次数")
    private String fogetCardTimes;
    //忘打卡扣款
    @ExcelProperty(value = "补打卡扣款")
    private BigDecimal fogetCardDeduction;
    //婚假(天)
    @ExcelProperty(value = "婚假天数")
    @NumberFormat(value="0000.00")
    private BigDecimal marriageDays;
    //丧假(天)
    @ExcelProperty(value = "丧假天数")
    private BigDecimal bereavementLeaveDays;
    //产假(天)
    @ExcelProperty(value = "产假产检假计划生育假哺乳假天数")
    private BigDecimal maternityLeaveDays;
    //年假(天)
    @ExcelProperty(value = "年假天数")
    private BigDecimal annualLeave;
    //调休(天)
    @ExcelProperty(value = "调休假天数")
    private BigDecimal restSays;
    //店长全薪假
    @ExcelProperty(value = "店长全薪假天数")
    private BigDecimal mgrFullpayLeave;
    //店长非全薪假
    @ExcelProperty(value = "店长非全薪假天数")
    private BigDecimal mgrNofullpayLeave;
    //助店假期
    @ExcelProperty(value = "助店请假天数")
    private BigDecimal assmgrLeave;

    //迟到分钟数扣款
    @ExcelIgnore
    private BigDecimal lateMunuteDeduction;
    //事假扣款
    @ExcelIgnore
    private BigDecimal personalLeaveDeduction;
    //病假扣款
    @ExcelIgnore
    private BigDecimal sickLeaveDeduction;
    //旷工扣款
    @ExcelIgnore
    private BigDecimal absenteeismDeduction;
    //迟到次数扣款
    @ExcelIgnore
    private BigDecimal lateTimesDeduction;
    @ExcelIgnore
    private BigDecimal attendanceDeductionTotal;

    //创建人
    @ExcelIgnore
    private String creater;
    //创建日期
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Integer status;
}
