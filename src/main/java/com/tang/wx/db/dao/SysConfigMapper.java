package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【sys_config】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.SysConfig
*/
@Mapper
public interface SysConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

}
