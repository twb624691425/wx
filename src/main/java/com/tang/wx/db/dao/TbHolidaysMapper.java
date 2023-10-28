package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbHolidays;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_holidays(节假日表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbHolidays
*/
@Mapper
public interface TbHolidaysMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbHolidays record);

    int insertSelective(TbHolidays record);

    TbHolidays selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbHolidays record);

    int updateByPrimaryKey(TbHolidays record);

}
