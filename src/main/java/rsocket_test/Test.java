package rsocket_test;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

import java.net.URI;

public class Test {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("os.arch = " + System.getProperty("os.arch"));
        System.out.println("os.version = " + System.getProperty("os.version"));
        System.out.println("java.vendor = " + System.getProperty("java.vendor"));
        System.out.println("java.vendor.version = " + System.getProperty("java.vendor.version"));
        System.out.println("java.version = " + System.getProperty("java.version"));
        System.out.println("java.vm.name = " + System.getProperty("java.vm.name"));
        System.out.println("java.vm.version = " + System.getProperty("java.vm.version"));
        System.out.println("--------------------------------------------------------------------");
        WebsocketClientTransport ws = WebsocketClientTransport.create(URI.create("ws://rsocket-demo.herokuapp.com/ws"));
        RSocket clientRSocket = RSocketConnector.connectWith(ws).block();

        try {
            Flux<Payload> s = clientRSocket.requestStream(DefaultPayload.create("peace"));

            s.take(10).doOnNext(p -> System.out.println(p.getDataUtf8())).blockLast();
        } finally {
            clientRSocket.dispose();
        }
    }
}