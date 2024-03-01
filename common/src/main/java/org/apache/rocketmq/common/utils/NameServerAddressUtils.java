/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.rocketmq.common.utils;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.MixAll;

public class NameServerAddressUtils {
    public static final String INSTANCE_PREFIX = "MQ_INST_";
    public static final String INSTANCE_REGEX = INSTANCE_PREFIX + "\\w+_\\w+";
    public static final String ENDPOINT_PREFIX = "(\\w+://|)";
    public static final Pattern NAMESRV_ENDPOINT_PATTERN = Pattern.compile("^http://.*");
    public static final Pattern INST_ENDPOINT_PATTERN = Pattern.compile("^" + ENDPOINT_PREFIX + INSTANCE_REGEX + "\\..*");
    /**
     * System.getenv与System.getProperty的区别
     * 项	属性（System.getProperty）	环境变量（System.getenv）
     * 运行时是否可修改	可以	不可以
     * 有效范围	仅在java平台中有效	系统里所有程序都有效
     * 创建的时机	打包应用时属性必须存在	可以在任何时候创建环境变量
    * */
    public static String getNameServerAddresses() {
        return System.getProperty(MixAll.NAMESRV_ADDR_PROPERTY, System.getenv(MixAll.NAMESRV_ADDR_ENV));
    }

    public static boolean validateInstanceEndpoint(String endpoint) {
        return INST_ENDPOINT_PATTERN.matcher(endpoint).matches();
    }

    public static String parseInstanceIdFromEndpoint(String endpoint) {
        if (StringUtils.isEmpty(endpoint)) {
            return null;
        }
        return endpoint.substring(endpoint.lastIndexOf("/") + 1, endpoint.indexOf('.'));
    }

    public static String getNameSrvAddrFromNamesrvEndpoint(String nameSrvEndpoint) {
        if (StringUtils.isEmpty(nameSrvEndpoint)) {
            return null;
        }
        return nameSrvEndpoint.substring(nameSrvEndpoint.lastIndexOf('/') + 1);
    }
}
