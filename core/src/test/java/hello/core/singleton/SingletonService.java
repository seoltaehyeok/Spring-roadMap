package hello.core.singleton;

public class SingletonService {

    // JVM이 동작할 때 static 메서드가 동작하면서 SingletonService 객체를 생성해서 instance 변수에 담아둔다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 외부에서 new 키워드를 사용한 객체 생성을 못하게 private 접근제한자를 사용해준다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글돈 객체 로직을 호출함");
    }
}
