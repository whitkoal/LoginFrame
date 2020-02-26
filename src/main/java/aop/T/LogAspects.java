package aop.T;

public class LogAspects {

    @befo
    public  void logStart(){
        System.out.println("日志打印开始...");
    }

    public void logEnd(){
        System.out.println("日志打印结束...");
    }

    public void logReturn(){
        System.out.println("除法正常返回...");
    }

    public void logException(){
        System.out.println("除法异常...异常信息： {}");
    }
}
