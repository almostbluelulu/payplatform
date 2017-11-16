package com.yeepay.g3.core.whitebroad.parser;

import java.util.List;

/**
 * Description: DTO转换接口
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 21:00
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface DTOConvert2<D, E1, E2> {

	List convert2Entity(D d);

	D convert2DTO(E1 e1, E2 e2);
}
