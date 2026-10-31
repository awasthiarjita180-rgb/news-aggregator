# News Aggregator AI Backend

A Java-based backend service developed with Spring Boot to explore AI-powered news processing, text extraction, content summarisation, sentiment analysis, language translation and personalised article recommendations.

This repository contains a separately developed backend codebase. It is not currently integrated with the News Aggregator frontend.

## Overview

The project provides REST APIs for processing news-related content using Java, Spring Boot, OpenAI APIs, Tesseract OCR and Google Cloud Translation.

It focuses on building modular backend services that handle text processing, image-based text extraction and user reading-history-based recommendations.

## Features

### 1. AI-Powered Article Summarisation

* Accepts article text through a REST API.
* Uses the OpenAI Chat Completions API.
* Generates concise summaries in approximately 2–3 sentences.

### 2. Sentiment Analysis

* Analyses text using an OpenAI-powered service.
* Classifies sentiment into Positive, Negative or Neutral.
* Provides the result through a REST endpoint.

### 3. Image Text Extraction (OCR)

* Accepts uploaded image files.
* Uses Tesseract OCR through Tess4J.
* Extracts English text from supported images.

### 4. Language Translation

* Uses the Google Cloud Translation API.
* Accepts text and a target language.
* Returns translated text and detected source language.

### 5. Article Recommendation

* Maintains article and reading-history entities using Spring Data JPA.
* Analyses users' reading history and frequently read categories.
* Recommends unread articles from preferred categories.
* Returns recent articles as a fallback when reading history is unavailable.

## Technology Stack

| Category                  | Technologies                        |
| ------------------------- | ----------------------------------- |
| Language                  | Java 17                             |
| Backend Framework         | Spring Boot                         |
| Build Tool                | Maven                               |
| API Development           | Spring Web, REST APIs               |
| Database                  | PostgreSQL                          |
| ORM                       | Spring Data JPA, Hibernate          |
| AI Integration            | OpenAI API                          |
| OCR                       | Tesseract OCR, Tess4J               |
| Translation               | Google Cloud Translation API        |
| HTTP Client               | Spring WebFlux, WebClient           |
| Security Framework        | Spring Security                     |
| Additional Infrastructure | Redis, Apache Kafka, Docker Compose |

## Project Structure

```text
news-aggregator/
│
├── src/
│   ├── main/
│   │   ├── java/com/newsaggregator/newsaggregator/
│   │   │
│   │   ├── client/
│   │   │   └── OpenAiClient.java
│   │   │
│   │   ├── config/
│   │   │   ├── OcrConfig.java
│   │   │   ├── SecurityConfig.java
│   │   │   └── WebClientConfig.java
│   │   │
│   │   ├── entity/
│   │   │   ├── Article.java
│   │   │   ├── ArticleRepository.java
│   │   │   ├── ReadingHistory.java
│   │   │   └── ReadingHistoryRepository.java
│   │   │
│   │   ├── ocr/
│   │   ├── recommendation/
│   │   ├── sentiment/
│   │   ├── summarization/
│   │   └── translation/
│   │
│   └── resources/
│       └── application.yml
│
├── compose.yaml
├── pom.xml
└── mvnw
```

## REST API Endpoints

| Method | Endpoint                        | Purpose                               |
| ------ | ------------------------------- | ------------------------------------- |
| POST   | `/api/summarize`                | Generate an article summary           |
| POST   | `/api/sentiment`                | Analyse text sentiment                |
| POST   | `/api/ocr`                      | Extract text from an uploaded image   |
| POST   | `/api/translate`                | Translate text into a target language |
| GET    | `/api/recommendations/{userId}` | Retrieve article recommendations      |

## Prerequisites

* Java 17
* Maven
* PostgreSQL
* Tesseract OCR
* OpenAI API key
* Google Cloud Translation API key (for translation)

Docker Desktop is useful for starting the supporting infrastructure defined in `compose.yaml`.

## Configuration

Update `src/main/resources/application.yml` with your local database configuration and API credentials.

For security, use environment variables for sensitive values rather than committing API keys, passwords or tokens to GitHub.

The OCR configuration currently uses a Windows Tesseract installation path. Update the path in `OcrConfig.java` to match your local installation.

## Running the Project

Clone the repository:

```bash
git clone https://github.com/awasthiarjita180-rgb/news-aggregator.git
```

Navigate to the project directory:

```bash
cd news-aggregator
```

Start the supporting infrastructure if required:

```bash
docker compose up -d
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application is configured to run on:

```text
http://localhost:8081
```

## Development Notes

* The AI services use external API credentials and require appropriate configuration.
* OCR requires a local Tesseract installation.
* PostgreSQL is configured for article and reading-history persistence.
* Redis and Kafka are included in the project dependencies and Docker Compose configuration; active application-level processing with these technologies is not yet documented as implemented.
* This backend is maintained separately from the News Aggregator frontend.

## Author

**Arjita Awasthi**

Java Full Stack Developer | Software Engineer

GitHub: https://github.com/awasthiarjita180-rgb
