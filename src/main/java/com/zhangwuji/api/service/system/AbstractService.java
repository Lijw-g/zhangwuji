package com.zhangwuji.api.service.system;

import com.zhangwuji.api.api.JsonMapper;
import com.zhangwuji.api.api.MapperBuilder;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public abstract class AbstractService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static JsonMapper mapper = MapperBuilder.getDefaultMapper();

    @Resource
    protected SqlSession sqlSession;
}