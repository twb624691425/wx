package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbCheckin;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_checkin(签到表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbCheckin
*/
@Mapper
public interface TbCheckinMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbCheckin record);

    int insertSelective(TbCheckin record);

    TbCheckin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCheckin record);

    int updateByPrimaryKey(TbCheckin record);

}
