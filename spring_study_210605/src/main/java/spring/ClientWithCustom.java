package spring;

public class ClientWithCustom {
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void startConnect() {
        System.out.println("ClientWithCustom.startConnect() to " + host);
    }
    public void send() {
        System.out.println("ClientWithCustom2.send() to " + host);
    }
    public void close() {
        System.out.println("ClientWithCustom2.close()");
    }
}
