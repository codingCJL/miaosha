package com.cjl.miaosha.exception;

import com.cjl.miaosha.result.CodeMsg;

/**
 * @author cjl
 * @date 2020/3/25 14:09
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
