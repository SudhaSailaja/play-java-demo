package controllers.HelloAbstract;

import javax.inject.Singleton;

@Singleton
public class HelloMethods {
    private String message = "Hello Sym. This is your controller!!!";
    public String doResultMessage(){
        return message;
    }
}
