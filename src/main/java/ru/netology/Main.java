package ru.netology;

import java.io.IOException;

//public class Main {
//    public static void main(String[] args) {
//        Server server = new Server(64);
//        server.start(9999);
//    }
//}
//
//public class Main {
//    public static void main(String[] args){
//        final var server = new Server();
//        // код инициализации сервера (из вашего предыдущего ДЗ)
//
//        // добавление хендлеров (обработчиков)
//        server.addHandler("GET", "/messages", new Handler() {
//            public void handle(Request request, BufferedOutputStream responseStream) {
//                // TODO: handlers code
//            }
//        });
//        server.addHandler("POST", "/messages", new Handler() {
//            public void handle(Request request, BufferedOutputStream responseStream) {
//                // TODO: handlers code
//            }
//        });
//
//        server.listen(9999);
//    }
//}
public class Main {
    private static final int SERVER_SOCKET = 9999;
    private static final int poolSizeThreads = 64;
    public static void main(String[] args) throws InterruptedException {

        Server server = new Server(SERVER_SOCKET, poolSizeThreads);
        server.addHandler("GET", "/messages", ((request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream,"404", "Not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        server.addHandler("POST", "/messages", (request, responseStream) ->
                server.responseWithoutContent(responseStream, "404", "Not found"));
        server.addHandler("GET", "/", ((request, responseStream) -> server.defaultHandler(responseStream, "spring.png")));
        server.start();
    }
}