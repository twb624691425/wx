package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbAction;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_action(行为表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:21
* @Entity com.tang.wx.db.pojo.TbAction
*/
@Mapper
public interface TbActionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbAction record);

    int insertSelective(TbAction record);

    TbAction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbAction record);

    int updateByPrimaryKey(TbAction record);

}
