# 💭 Sentiment Analysis

A lightweight sentiment analysis application built with **Java backend** and a modern **HTML/CSS/JavaScript** frontend. Analyze the emotional tone of your text instantly with a clean, responsive interface.

## ✨ Features

- 🚀 **Real-Time Analysis** - Get instant sentiment classification
- 🎨 **Modern UI** - Beautiful gradient interface with smooth animations
- 📊 **Detailed Scores** - Positive, Negative, Neutral, and Compound scoring
- ⚡ **Fast & Lightweight** - Pure Java using JDK HTTP server (no external dependencies)
- 📱 **Responsive Design** - Works seamlessly on desktop and mobile
- 🎯 **Easy to Use** - Simple text input and instant results

## 🛠 Tech Stack

| Component | Technology |
|-----------|-----------|
| **Backend** | Java (JDK HTTP Server) |
| **Frontend** | HTML5, CSS3, JavaScript |
| **UI Components** | Font Awesome Icons, Google Fonts |
| **Port** | 5000 |

## 📋 Requirements

- **Java 17+** (JDK)
- No external backend dependencies
- Modern web browser with JavaScript enabled

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/PRABANSHAN/sentiment-analysis.git
cd sentiment-analysis
```

### 2. Compile and Run

**Windows:**
```bash
javac SentimentServer.java
java SentimentServer
```

**macOS/Linux:**
```bash
javac SentimentServer.java
java SentimentServer
```

Or use the provided startup scripts:
- **Windows**: `run-java.bat`
- **Unix**: `./start.sh`

### 3. Access the Application

Open your browser and navigate to:
```
http://localhost:5000
```

## 💻 Usage

1. **Enter Text** - Paste or type any text in the textarea
2. **Click Analyze** - Press the "Analyze Sentiment" button
3. **View Results** - See the sentiment classification and detailed scores

### Example Results

```
Input: "I absolutely love this product! It's amazing!"
Output: ✅ Positive (Compound: 0.637)

Input: "This is the worst experience ever."
Output: ❌ Negative (Compound: -0.651)

Input: "The weather is nice today."
Output: 😐 Neutral (Compound: 0.052)
```

## 📊 Sentiment Scoring

### Score Components

| Component | Description |
|-----------|-------------|
| **Positive (pos)** | Percentage of positive words (0.0 - 1.0) |
| **Negative (neg)** | Percentage of negative words (0.0 - 1.0) |
| **Neutral (neu)** | Percentage of neutral words (0.0 - 1.0) |
| **Compound** | Normalized composite score (-1.0 to +1.0) |

### Classification Rules

- **Positive**: Compound score ≥ 0.05
- **Negative**: Compound score ≤ -0.05
- **Neutral**: Compound score between -0.05 and 0.05

## 🔧 API Endpoint

### POST `/api/sentiment`

**Request:**
```json
{
  "text": "Your text to analyze"
}
```

**Response:**
```json
{
  "text": "Your text to analyze",
  "label": "Positive",
  "scores": {
    "pos": 0.833,
    "neg": 0.0,
    "neu": 0.167,
    "compound": 0.637
  }
}
```

## 📁 Project Structure

```
sentiment-analysis/
├── SentimentServer.java    # Java backend with HTTP server
├── index.html              # Frontend UI
├── run-java.bat            # Windows startup script
├── start.sh                # Unix startup script
├── .gitignore              # Git ignore rules
└── README.md               # This file
```

## 🎯 How It Works

1. **Frontend** sends text to the backend via POST request
2. **SentimentServer** receives the request at `/api/sentiment`
3. **SentimentAnalyzer** tokenizes the text and counts sentiment words
4. **Scores** are calculated based on word frequencies
5. **Result** is returned with label and detailed metrics

## 🚀 Recent Updates

- ✅ Converted from Python Flask to pure Java backend
- ✅ Removed external dependencies
- ✅ Native JDK HTTP Server implementation
- ✅ Improved startup scripts and deployment
- ✅ Enhanced UI with modern styling

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report issues
- Suggest improvements
- Submit pull requests
- Share feature ideas

## 📝 License

This project is open source and available under the MIT License.

---

**Built with ❤️ by PRABANSHAN**

⭐ If you find this useful, please consider giving it a star!
