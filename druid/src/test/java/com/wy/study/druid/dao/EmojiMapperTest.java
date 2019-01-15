package com.wy.study.druid.dao;

import com.wy.study.druid.Runner;
import com.wy.study.druid.domain.Emoji;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Runner.class)
public class EmojiMapperTest {

    @Resource
    private EmojiMapper emojiMapper;
    @Test
    public void insert() {
        Emoji emoji = new Emoji();
        emoji.setEmoji("哈哈😊哈哈😢略略略");
        emojiMapper.insert(emoji);

    }
}