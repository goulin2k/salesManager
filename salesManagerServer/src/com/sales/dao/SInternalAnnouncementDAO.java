package com.sales.dao;

import com.sales.model.SInternalAnnouncement;

public interface SInternalAnnouncementDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_internal_announcement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    void insert(SInternalAnnouncement record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_internal_announcement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKey(SInternalAnnouncement record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_internal_announcement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKeySelective(SInternalAnnouncement record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_internal_announcement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    SInternalAnnouncement selectByPrimaryKey(Integer announcementId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_internal_announcement
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int deleteByPrimaryKey(Integer announcementId);
}