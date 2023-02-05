package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcp;

    public Connected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection has already connected");
    }

    @Override
    public void disconnect() {
        tcp.setState(new Disconnected(this.tcp));
    }

    @Override
    public void write(String data) {
        tcp.getData().add(data);
    }
}
// END
