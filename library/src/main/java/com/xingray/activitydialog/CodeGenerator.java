package com.xingray.activitydialog;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author      : leixing
 * Date        : 2017-06-12
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : user for generate unique code as long type.
 */

/*package*/ class CodeGenerator {
    private static volatile CodeGenerator INSTANCE;

    /**
     * unique code
     */
    private AtomicLong mCode;

    static CodeGenerator getInstance() {
        if (INSTANCE == null) {
            synchronized (CodeGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CodeGenerator();
                }
            }
        }

        return INSTANCE;
    }

    private CodeGenerator() {
        mCode = new AtomicLong(0);
    }

    /**
     * generate a long type unique code
     *
     * @return code
     */
    long next() {
        return mCode.getAndAdd(1);
    }
}
