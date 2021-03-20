package service;

/**
 * @Description 自定义异常类
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -1702031019931249948L;

    public TeamException() {
        super();
    }

    public TeamException(String message) {
        super(message);
    }
}
