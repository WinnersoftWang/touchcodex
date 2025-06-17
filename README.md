# touchcodex

This repository contains a simple Spring Boot application demonstrating how to generate Excel reports using [ExCella Reports](https://github.com/excella-core/excella-reports).

## Project structure

- `report-app/` - Spring Boot application
    - `pom.xml` defines dependencies
    - `src/main/java` contains the application and controller
    - `src/main/resources/templates/index.html` is the start page
    - `/report` endpoint generates an Excel file and triggers download

## Running

Assuming Maven and JDK are installed, navigate to `report-app` and run:

```bash
mvn spring-boot:run
```

Then open `http://localhost:8080` in your browser.

Press the "帳票作成" button to download the generated report.
