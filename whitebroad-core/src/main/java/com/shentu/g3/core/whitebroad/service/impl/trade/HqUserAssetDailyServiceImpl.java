//package com.xiangshang.hq.service.impl;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.EnumSet;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.xiangshang.constant.em.hq.FundsType;
//import com.xiangshang.hq.dao.HqUserAssetDailyDao;
//import com.xiangshang.hq.service.HqUserAssetDailyService;
//import com.xiangshang.model.hq.HqUserAssetDaily;
//
//@Service("hqUserAssetDailyService")
//public class HqUserAssetDailyServiceImpl implements HqUserAssetDailyService {
//
//	@Resource(name = "hqUserAssetDailyDao")
//	private HqUserAssetDailyDao hqUserAssetDailyDao;
//	
//	/**
//	 * 新增一条日志记录
//	 * @param entity
//	 * @return
//	 */
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.RuntimeException.class)
//	public HqUserAssetDaily add(HqUserAssetDaily entity) {
//		return hqUserAssetDailyDao.add(entity);
//	}
//	
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public List<HqUserAssetDaily> getIncomeListByUserId(Integer userId, Integer from, Integer pageSize) {
//		return hqUserAssetDailyDao.queryIncomeListByUserId(userId, from, pageSize);
//	}
//	
//	/**
//	 * 查询用户某一天的收益数据
//	 * @param userId 用户id
//	 * @param date 某天
//	 * @return
//	 */
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public HqUserAssetDaily queryByUserDaily(Integer userId, Date date) {
//		return hqUserAssetDailyDao.queryByUserDaily(userId, date);
//	}
//
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public int getUserIncomeCount(Integer userId) {
//		return hqUserAssetDailyDao.queryUserIncomCount(userId);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public List<Map> queryUserFundsRecordByType(Integer userId, EnumSet<FundsType> fundsTypes, int from, int pageSize) {
//		List<Map> list = hqUserAssetDailyDao.queryFundsListByType(userId, fundsTypes, from, pageSize);
//		return list;
//	}
//
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public BigDecimal queryTotalIncomeByUserDaily(Integer userId, Date date) {
//		return hqUserAssetDailyDao.queryUserTotalIncomeByDaily(userId, date);
//	}
//
//	@Override
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public int countUserFundsRecordByType(Integer userId, EnumSet<FundsType> fundsTypes) {
//		return hqUserAssetDailyDao.countUserTotalIncomeByDaily(userId, fundsTypes);
//	}
//}
