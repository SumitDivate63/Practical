import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Portfolio {
    private static final String PAGE = """
            <!doctype html>
            <html lang="en">
            <head>
              <meta charset="utf-8">
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <meta name="description" content="Enter your portfolio details.">
              <title>Portfolio Details</title>
              <style>
                * { box-sizing: border-box; }
                body { max-width: 640px; margin: 48px auto; padding: 0 20px; color: #222; font: 16px Arial, sans-serif; }
                h1 { margin-bottom: 8px; font-size: 28px; }
                p { margin: 0 0 24px; color: #555; }
                label { display: block; margin: 16px 0 6px; font-weight: 600; }
                input, textarea { width: 100%; padding: 10px; border: 1px solid #bbb; border-radius: 4px; font: inherit; }
                textarea { min-height: 100px; resize: vertical; }
              </style>
            </head>
            <body>
              <main>
                <h1>Portfolio details</h1>
                <p>Enter your details below.</p>
                <form>
                  <label for="name">Name</label>
                  <input id="name" name="name" type="text">

                  <label for="role">Role</label>
                  <input id="role" name="role" type="text">

                  <label for="about">About</label>
                  <textarea id="about" name="about"></textarea>

                  <label for="projects">Projects</label>
                  <textarea id="projects" name="projects"></textarea>

                  <label for="email">Email</label>
                  <input id="email" name="email" type="email">
                </form>
              </main>
            </body>
            </html>
            """;

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.createContext("/", Portfolio::handleRequest);
        server.start();
        System.out.println("Portfolio is running at http://localhost:" + port);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        int status = 200;
        String content;
        if (!"GET".equals(exchange.getRequestMethod())) {
            status = 405;
            content = "Method Not Allowed";
            exchange.getResponseHeaders().set("Allow", "GET");
        } else if (!"/".equals(exchange.getRequestURI().getPath())) {
            status = 404;
            content = "Not Found";
        } else {
            content = PAGE;
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        }

        byte[] response = content.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, response.length);
        try (OutputStream body = exchange.getResponseBody()) {
            body.write(response);
        } finally {
            exchange.close();
        }
    }
}
