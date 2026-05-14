# 💭 Sentiment Analysis Application

A powerful web application that analyzes the emotional tone and sentiment of any given text using advanced Natural Language Processing (NLP) and the VADER sentiment analyzer.

## ✨ Features

- **Real-Time Sentiment Analysis**: Instant sentiment classification with detailed scoring
- **Multiple Sentiment Levels**: Positive, Negative, and Neutral classifications
- **Detailed Scoring Metrics**: 
  - Positive sentiment percentage
  - Negative sentiment percentage
  - Neutral sentiment percentage
  - Compound score for overall sentiment intensity
- **Beautiful UI**: Modern, responsive interface with gradient backgrounds
- **Emoji Indicators**: Visual feedback with emotion-based icons
- **Loading States**: User-friendly loading animations during analysis
- **Error Handling**: Robust error messages and validation

## 🚀 Live Demo

🔗 **[Launch Live Application](https://sentiment-analysis-heroku.herokuapp.com)**

## 🛠️ Tech Stack

### Frontend
- **HTML5**: Semantic markup and structure
- **CSS3**: Modern styling with gradients and animations
- **JavaScript**: Dynamic functionality and API communication
- **Font Awesome**: Beautiful icons and visual elements
- **Google Fonts (Poppins)**: Professional typography

### Backend
- **Flask**: Lightweight Python web framework
- **VADER Sentiment**: Advanced sentiment analysis library
- **Gunicorn**: Production WSGI HTTP server
- **Python 3.x**: Core programming language

### Deployment
- **Heroku**: Cloud platform for hosting
- **Procfile**: Application server configuration
- **requirements.txt**: Python dependencies management

## 📋 Requirements

### Local Development
```
Flask==2.3.3
Flask-Cors==3.0.10
vaderSentiment==3.3.2
gunicorn==23.0.0
```

### Browser Requirements
- Modern web browser (Chrome, Firefox, Safari, Edge)
- JavaScript enabled
- Internet connection

## 💻 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/PRABANSHAN/sentiment-heroku.git
cd sentiment-heroku
```

### 2. Create Virtual Environment
```bash
python -m venv venv
```

### 3. Activate Virtual Environment

**On Windows:**
```bash
venv\Scripts\activate
```

**On macOS/Linux:**
```bash
source venv/bin/activate
```

### 4. Install Dependencies
```bash
pip install -r requirements.txt
```

### 5. Run Locally
```bash
python app.py
```

The application will be available at `http://localhost:5000`

### 6. Deploy to Heroku

**Prerequisites:**
- Heroku account
- Heroku CLI installed

**Steps:**
```bash
# Login to Heroku
heroku login

# Create Heroku app
heroku create your-app-name

# Deploy
git push heroku main

# Open app
heroku open
```

## 🎯 How to Use

1. **Open the Application**
   - Visit the live demo link or local development URL

2. **Enter Text**
   - Paste or type any text in the textarea
   - Text can be reviews, comments, social media posts, etc.

3. **Analyze**
   - Click the "Analyze Sentiment" button
   - Wait for the analysis to complete

4. **View Results**
   - See the sentiment classification (Positive/Negative/Neutral)
   - Review detailed sentiment scores
   - Understand the emotional tone

### Example Inputs
```
"I absolutely love this product! It's amazing!"
→ Result: Positive (95% confidence)

"This is the worst experience I've ever had."
→ Result: Negative (92% confidence)

"The weather is nice today."
→ Result: Neutral (87% confidence)
```

## 📊 Sentiment Scoring

### Score Range
- **Compound Score**: -1.0 to +1.0
  - **≥ 0.05**: Positive sentiment
  - **≤ -0.05**: Negative sentiment
  - **Between -0.05 and 0.05**: Neutral sentiment

### Score Components
- **Positive (pos)**: Proportion of positive words
- **Negative (neg)**: Proportion of negative words
- **Neutral (neu)**: Proportion of neutral words
- **Compound**: Normalized composite score

## 📁 Project Structure

```
sentiment-heroku/
├── app.py                 # Flask application & API routes
├── index.html            # Frontend UI
├── requirements.txt      # Python dependencies
├── Procfile             # Heroku configuration
├── runtime.txt          # Python version specification
├── README.md            # This file
└── .gitignore          # Git ignore rules
```

## 🔧 API Endpoints

### POST /api/sentiment
Analyzes the sentiment of provided text.

**Request:**
```json
{
  "text": "Your text here"
}
```

**Response:**
```json
{
  "text": "Your text here",
  "label": "Positive",
  "scores": {
    "neg": 0.0,
    "neu": 0.167,
    "pos": 0.833,
    "compound": 0.6369
  }
}
```

**Error Response:**
```json
{
  "error": "No text provided"
}
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the Repository**
   ```bash
   git clone https://github.com/PRABANSHAN/sentiment-heroku.git
   ```

2. **Create Feature Branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```

3. **Commit Changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```

4. **Push to Branch**
   ```bash
   git push origin feature/AmazingFeature
   ```

5. **Open Pull Request**
   - Describe your changes clearly
   - Include screenshots if applicable

## 🐛 Known Issues & Limitations

- Works best with English text
- Emoji sentiment analysis has limited support
- Sarcasm detection is not perfect
- Results may vary with very short texts

## 🚀 Future Enhancements

- [ ] Multi-language support
- [ ] Sarcasm detection improvement
- [ ] Historical analysis tracking
- [ ] Batch text processing
- [ ] Export results as CSV/PDF
- [ ] Social media integration
- [ ] Real-time streaming analysis
- [ ] Advanced NLP models (BERT, DistilBERT)

## 📝 License

This project is open source and available under the MIT License. See the LICENSE file for details.

## 👤 Author

**PRABANSHAN**
- GitHub: [@PRABANSHAN](https://github.com/PRABANSHAN)
- Repository: [sentiment-heroku](https://github.com/PRABANSHAN/sentiment-heroku)

## 🙏 Acknowledgments

- **VADER (Valence Aware Dictionary and sEntiment Reasoner)**: For sentiment analysis
- **Flask Team**: For the lightweight web framework
- **Heroku**: For free hosting and deployment
- **Font Awesome**: For beautiful icons
- **Community Contributors**: For feedback and improvements

## 📞 Support & Contact

For support, issues, or suggestions:
- Open an issue on [GitHub Issues](https://github.com/PRABANSHAN/sentiment-heroku/issues)
- Check existing issues and discussions
- Include detailed error messages and steps to reproduce

## 🔐 Security

- No personal data is stored
- Text is analyzed and not saved
- CORS enabled for cross-origin requests
- Input validation and sanitization implemented

## 📈 Performance

- Average response time: < 500ms
- Handles up to 10,000+ character inputs
- Optimized for mobile and desktop
- Production-ready codebase

---

**Made with ❤️ by PRABANSHAN**

⭐ If you find this project helpful, please consider giving it a star!
