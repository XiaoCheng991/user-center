package com.change.userCenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.change.userCenter.mapper.TagMapper;
import com.change.userCenter.model.domain.Tag;
import com.change.userCenter.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author cyq
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-11-24 20:48:07
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




