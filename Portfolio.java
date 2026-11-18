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
              <meta name="description" content="Alex Morgan's personal portfolio.">
              <title>Alex Morgan — Developer</title>
              <style>
                :root { color-scheme: light; font-family: Arial, sans-serif; color: #23302a; background: #f7f6f1; }
                * { box-sizing: border-box; }
                body { margin: 0; }
                header, main, footer { width: min(940px, calc(100% - 40px)); margin: auto; }
                header { display: flex; justify-content: space-between; align-items: center; padding: 24px 0; border-bottom: 1px solid #deded5; }
                a { color: inherit; text-decoration: none; }
                header a, .eyebrow { font-size: .82rem; }
                header nav { display: flex; gap: 22px; }
                .logo { font-weight: 800; font-size: 1.2rem; }
                .hero { padding: 100px 0 90px; }
                .eyebrow { color: #55725e; font-weight: 700; letter-spacing: .12em; text-transform: uppercase; }
                h1 { max-width: 720px; margin: 18px 0; font-size: clamp(2.8rem, 8vw, 5.6rem); line-height: 1.02; letter-spacing: -.06em; }
                h1 span { color: #55725e; font-family: Georgia, serif; font-weight: 400; font-style: italic; }
                .intro { max-width: 560px; color: #606860; font-size: 1.12rem; line-height: 1.8; }
                .button { display: inline-block; margin-top: 16px; padding: 13px 18px; border-radius: 3px; background: #23302a; color: white; }
                section { padding: 55px 0; border-top: 1px solid #deded5; }
                h2 { margin: 0 0 24px; font-size: 2rem; letter-spacing: -.04em; }
                .projects { display: grid; grid-template-columns: repeat(auto-fit, minmax(230px, 1fr)); gap: 18px; }
                article { padding: 24px; background: #eeede6; }
                article h3 { margin: 0 0 10px; }
                article p, section > p { color: #606860; line-height: 1.7; }
                footer { padding: 28px 0; border-top: 1px solid #deded5; color: #606860; font-size: .9rem; }
                @media (max-width: 520px) { .hero { padding: 70px 0; } header nav { gap: 12px; } }
              </style>
            </head>
            <body>
              <header>
                <a class="logo" href="#home">AM.</a>
                <nav aria-label="Main navigation">
                  <a href="#work">Work</a>
                  <a href="#about">About</a>
                  <a href="mailto:hello@example.com">Contact</a>
                </nav>
              </header>
              <main id="home">
                <div class="hero">
                  <p class="eyebrow">Software developer</p>
                  <h1>Building useful things for <span>real people.</span></h1>
                  <p class="intro">Hi, I’m Alex. I turn complex problems into clear, thoughtful digital experiences.</p>
                  <a class="button" href="#work">Explore my work ↓</a>
                </div>
                <section id="work">
                  <p class="eyebrow">A few things I’ve made</p>
                  <h2>Selected work</h2>
                  <div class="projects">
                    <article><h3>Fieldnotes</h3><p>A calmer way to plan your days. Product design and web development.</p></article>
                    <article><h3>Tide</h3><p>A simple way to make sustainable choices and track their impact.</p></article>
                  </div>
                </section>
                <section id="about">
                  <p class="eyebrow">A bit about me</p>
                  <h2>Good work starts with good questions.</h2>
                  <p>I enjoy building dependable software with care, from the first sketch to the final details. My focus is Java, web development, and product thinking.</p>
                  <a class="button" href="mailto:hello@example.com">Say hello ↗</a>
                </section>
              </main>
              <footer>Made with care · © 2026 Alex Morgan</footer>
            </body>
            </html>
            """;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
        server.createContext("/", Portfolio::handleRequest);
        server.start();
        System.out.println("Portfolio is running at http://localhost:8080");
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
