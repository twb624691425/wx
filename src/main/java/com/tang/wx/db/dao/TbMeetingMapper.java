package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbMeeting;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【tb_meeting(会议表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:22
* @Entity com.tang.wx.db.pojo.TbMeeting
*/
@Mapper
public interface TbMeetingMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbMeeting record);

    int insertSelective(TbMeeting record);

    TbMeeting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbMeeting record);

    int updateByPrimaryKey(TbMeeting record);

}
