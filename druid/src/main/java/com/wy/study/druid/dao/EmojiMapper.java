package com.wy.study.druid.dao;

import com.wy.study.druid.domain.Emoji;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmojiMapper {

    int insert(Emoji emoji);
}
