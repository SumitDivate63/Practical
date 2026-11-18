# Personal Portfolio

A simple, responsive personal portfolio website built with Java 21 and Spring Boot. The page is served by Spring Boot as a static resource and includes sample work, an about section, and contact links.

## Customize the portfolio

Edit `src/main/resources/static/index.html` to change the name, biography, projects, email address, and social links. Update `src/main/resources/static/styles.css` to adjust the visual design. The included project cards and profile details are sample content.

## Run locally with Java

Requirements: Java 21 or newer and Maven 3.6 or newer.

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080).

## Run locally with Docker

Requirements: Docker.

Build the image from the project root:

```bash
docker build -t personal-portfolio .
```

Start the container:

```bash
docker run --rm -p 8080:8080 personal-portfolio
```

Visit [http://localhost:8080](http://localhost:8080). Press `Ctrl+C` to stop the container.

## Run with Docker Compose

```bash
docker compose up --build
```

Open [http://localhost:8080](http://localhost:8080). Stop it with `Ctrl+C`; run `docker compose down` to remove the container.
