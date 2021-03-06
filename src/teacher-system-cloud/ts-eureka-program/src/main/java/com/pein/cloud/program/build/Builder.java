package com.pein.cloud.program.build;

/**
 * Created by qiuw on 2017/6/28.
 */

import com.pein.cloud.program.vo.ApiVo;

/**
 * @author qiuwei
 * @create 2017-06-28 16:03
 **/
public class Builder {

    public static ApiVo build(String data) {
        ApiVo apiVo = new ApiVo();
        apiVo.setSuccess(true);
        apiVo.setData(data);
        return apiVo;
    }

    public static ApiVo build() {
        return new ApiVo();
    }

}
