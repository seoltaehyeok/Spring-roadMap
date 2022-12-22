package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출 url : " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    // 연결한 서버에 메시지를 전달한다고 가정
    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    // 서비스가 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }
}
