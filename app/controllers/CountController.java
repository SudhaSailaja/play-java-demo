package controllers;

import Sample.AtomicCounter;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class CountController extends Controller {

    private AtomicCounter counter;

    @Inject
    public CountController(AtomicCounter counter){
        this.counter = counter;
    }

    public Result getCurrentCount(){
        return ok("Count is : " + counter.getCount());
    }
}
