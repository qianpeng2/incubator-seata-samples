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
        String xid = RootContext.getXID();
        if (StringUtils.isNotBlank(xid)) {
            requestTemplate.header(RootContext.KEY_XID, xid); // "TX_XID"
        }
    }
}
