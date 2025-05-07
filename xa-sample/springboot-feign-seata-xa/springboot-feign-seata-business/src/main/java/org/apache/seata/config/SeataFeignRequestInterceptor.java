package org.apache.seata.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeataFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 在Feign调用中传递参数的常见方式有多种
        // 路径参数:@PathVariable("id")
        // 查询参数:@RequestParam("role")
        // 请求体:@RequestBody User
        // 请求头:@RequestHeader("Authorization") String token
        // 多个参数组合:(@PathVariable("id") Long id, @RequestBody Object obj,@RequestHeader("X-Trace-Id") String traceId)
        // 使用Map传递多个查询参数: (@SpringQueryMap Map<String, Object> params)
        // 配置全局请求头:为所有请求添加相同的header，可以通过配置类实现,
        // 即本类中的 通过实现 RequestInterceptor接口注入的 拦截器 SeataFeignRequestInterceptor
        String xid = RootContext.getXID();
        if (StringUtils.isNotBlank(xid)) {
            requestTemplate.header(RootContext.KEY_XID, xid); // "TX_XID"
        }
    }
}
