# Simple Java Portfolio

A small, responsive portfolio website served by a single Java file. It uses only the JDK—no Spring, Maven, or external dependencies. The sample name, contact address, projects, and styling are in `Portfolio.java`.

## Run locally

Requirements: Java 21 or newer.

From the project directory, run:

```bash
java --add-modules jdk.httpserver Portfolio.java
```

Open [http://localhost:8080](http://localhost:8080). Press `Ctrl+C` to stop the server.

If port 8080 is already in use, choose another port. In PowerShell:

```powershell
$env:PORT=8081
java --add-modules jdk.httpserver Portfolio.java
```

Then open [http://localhost:8081](http://localhost:8081).

## Run with Docker

Requirements: Docker.

Build and start the container from the project directory:

```bash
docker build -t personal-portfolio .
docker run --rm -p 8080:8080 personal-portfolio
```

Visit [http://localhost:8080](http://localhost:8080). Press `Ctrl+C` to stop the container.

If port 8080 is already in use on your computer, publish the container on a different host port:

```powershell
docker run --rm -p 8081:8080 personal-portfolio
```

Then open [http://localhost:8081](http://localhost:8081).
