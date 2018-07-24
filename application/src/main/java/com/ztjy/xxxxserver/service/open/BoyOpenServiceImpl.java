package com.ztjy.xxxxserver.service.open;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.ztjy.xxxxserver.facade.BoyOpenService;
import com.ztjy.xxxxserver.model.Words;

/**
 * @author Administrator
 */
@Service(interfaceClass = BoyOpenService.class)
@Component
public class BoyOpenServiceImpl implements BoyOpenService {
    @Override
    public Words sayHello() {
        Words words = new Words();
        words.setFirst("hello");
        words.setSecond("world");
        return words;
    }
}
