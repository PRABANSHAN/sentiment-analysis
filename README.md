# Sentiment Analysis

A simple web application for analyzing sentiment in text using machine learning.

## 🚀 [Live Demo](https://sentiment-analysis-heroku.herokuapp.com/)

## Features

- Real-time sentiment analysis
- User-friendly interface
- Fast and accurate predictions
- Support for various text inputs

## Tech Stack

- **Backend**: Java
- **Frontend**: HTML, CSS, JavaScript
- **Deployment**: Heroku

## Getting Started

### Prerequisites

- Java 17+

### Installation

```bash
git clone https://github.com/PRABANSHAN/sentiment-analysis.git
cd sentiment-analysis
```

### Running Locally

```bash
javac SentimentServer.java
java SentimentServer
```

The application will be available at `http://localhost:5000`

## API Endpoint

### POST /api/sentiment

```json
{
  "text": "Your text here"
}
```

**Response:**
```json
{
  "label": "Positive",
  "scores": {
    "pos": 0.833,
    "neg": 0.0,
    "neu": 0.167,
    "compound": 0.6369
  }
}
```

## License

MIT License - Open source and free to use

---

**Built with ❤️ by PRABANSHAN**
