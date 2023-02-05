package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    private String ip;
    private int port;
    private Connection state;
    private List<String> data = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return state.getCurrentState();
    }

    public void connect() {
        this.state.connect();
    }

    public void disconnect() {
        this.state.disconnect();
    }

    public void write(String data) {
        this.state.write(data);
    }

    public void setState(Connection state) {
        this.state = state;
    }

    public List<String> getData() {
        return data;
    }
}
// END
