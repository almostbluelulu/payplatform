package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.PushMsgEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PushMsgDao {

	void save(PushMsgEntity record);

	PushMsgEntity findById(@Param("id") Long id);

	PushMsgEntity findByMessageNo(@Param("messageNo") String messageNo);

	int updateAfterSend(PushMsgEntity record);

	int updateBeforeSend(PushMsgEntity record);

	int delete(PushMsgEntity entity);

	long nextSequence();

	int findTotalCount(@Param("startDate") String startDate,
					   @Param("endDate") String endDate,
					   @Param("msgType") String msgType,
					   @Param("userNumber") String userNumber);

	List<PushMsgEntity> findMsgByDate(@Param("userNumber") String userNumber,
									  @Param("fromIndex") int fromIndex,
									  @Param("pageSize") int pageSize,
									  @Param("msgType") String msgType,
									  @Param("startDate") String startDate,
									  @Param("endDate") String endDate);
}