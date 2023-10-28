package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbWorkday;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_workday】的数据库操作Mapper
* @createDate 2023-10-28 16:17:22
* @Entity com.tang.wx.db.pojo.TbWorkday
*/
@Mapper
public interface TbWorkdayMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbWorkday record);

    int insertSelective(TbWorkday record);

    TbWorkday selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbWorkday record);

    int updateByPrimaryKey(TbWorkday record);

}
