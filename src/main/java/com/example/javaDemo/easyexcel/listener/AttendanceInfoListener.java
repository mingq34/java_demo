package com.example.javaDemo.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.javaDemo.easyexcel.dao.HrAttendanceInfoDao;
import com.example.javaDemo.easyexcel.domain.AttendanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AttendanceInfoListener extends AnalysisEventListener<AttendanceInfo> {
    private HrAttendanceInfoDao hrAttendanceInfoDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceInfoListener.class);
    /**
     * 每隔3000条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<AttendanceInfo> list = new ArrayList<AttendanceInfo>();


    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param hrAttendanceInfoDao
     */
    public AttendanceInfoListener(HrAttendanceInfoDao hrAttendanceInfoDao) {
        this.hrAttendanceInfoDao = hrAttendanceInfoDao;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param attendanceInfo
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(AttendanceInfo attendanceInfo, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(attendanceInfo));
        list.add(attendanceInfo);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }


    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        hrAttendanceInfoDao.save(list);
        LOGGER.info("存储数据库成功！");
    }
}
