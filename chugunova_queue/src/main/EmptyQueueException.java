/**
 * @author Collin Chiang
 * @date 9/19/2022
 */
package main;

import java.lang.Exception;


public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super();
    }

    public EmptyQueueException(String errorMsg) {
        super(errorMsg);
    }
}
