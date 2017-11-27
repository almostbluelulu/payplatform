package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.order.SettleEntity;
import com.shentu.g3.facade.whitebroad.enumtype.SettleTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface SettleDao {

	int insert(@Param("pojo") SettleEntity pojo);

	int insertSelective(@Param("pojo") SettleEntity pojo);

	int insertList(@Param("pojos") List<SettleEntity> pojo);

	int update(@Param("pojo") SettleEntity pojo);

	List<SettleEntity> findCuzSettleByDate(@Param("customerNumber") String customerNumber,
										   @Param("fromIndex") int fromIndex,
										   @Param("pageSize") int pageSize,
										   @Param("settleType") String settleType,
										   @Param("settleDateStart") String settleDateStart,
										   @Param("settleDateEnd") String settleDateEnd);

	SettleEntity findCuzByDateAndType(@Param("customerNumber") String customerNumber,
									  @Param("settleDate") Date settleDate,
									  @Param("type") SettleTypeEnum type);

	int findTotalCount(@Param("startDate") String startDate,
					   @Param("endDate") String endDate,
					   @Param("settleType") String settleType,
					   @Param("customerNumber") String customerNumber);

	BigDecimal getTotalSum(@Param("startDate") String startDate,
						   @Param("endDate") String endDate,
						   @Param("settleType") String settleType,
						   @Param("customerNumber") String customerNumber);
}
