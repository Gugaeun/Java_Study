package exception;

public class DuplicateMemberDaoException extends RuntimeException{

    public DuplicateMemberDaoException(String message) {
        super(message);
    }
}
