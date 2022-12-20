package ng.com.createsoftware.gymbuddybe.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}