package demo;

/**
 * 枚举实现单例
 *
 * @author : ZhuangJunHui
 * @date : 2022/5/6 9:58
 */
public enum Singleton {

    INSTANCE;

    private SingletonObject instance;

    Singleton() {
        instance = new SingletonObject();
    }

    public class SingletonObject{

    }

    public SingletonObject getInstance() {
        return instance;
    }
}