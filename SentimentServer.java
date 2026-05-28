import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/api/sentiment", new SentimentHandler());
        server.setExecutor(null);

        System.out.println("SentimentServer started on http://127.0.0.1:" + port);
        server.start();
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\": \"Method not allowed\"}");
                return;
            }

            Path file = Paths.get("index.html");
            if (!Files.exists(file)) {
                sendJson(exchange, 404, "{\"error\": \"index.html not found\"}");
                return;
            }

            byte[] bytes = Files.readAllBytes(file);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    static class SentimentHandler implements HttpHandler {
        private static final SentimentAnalyzer ANALYZER = new SentimentAnalyzer();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\": \"Method not allowed\"}");
                return;
            }

            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String text = parseText(body);
            if (text == null || text.isBlank()) {
                sendJson(exchange, 400, "{\"error\": \"No text provided\"}");
                return;
            }

            AnalysisResult result = ANALYZER.analyze(text);
            sendJson(exchange, 200, result.toJson());
        }
    }

    private static String parseText(String json) {
        if (json == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("\\\"text\\\"\\s*:\\s*\\\"(.*?)\\\"", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(json);
        if (!matcher.find()) {
            return null;
        }

        return unescapeJsonString(matcher.group(1));
    }

    private static String unescapeJsonString(String value) {
        return value.replaceAll("\\\\\\\"", "\"")
                    .replaceAll("\\\\\\\\", "\\");
    }

    private static void sendJson(HttpExchange exchange, int statusCode, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    static class AnalysisResult {
        final String text;
        final String label;
        final double neg;
        final double neu;
        final double pos;
        final double compound;

        AnalysisResult(String text, String label, double neg, double neu, double pos, double compound) {
            this.text = text;
            this.label = label;
            this.neg = neg;
            this.neu = neu;
            this.pos = pos;
            this.compound = compound;
        }

        String toJson() {
            return String.format(Locale.ROOT,
                    "{\"text\": \"%s\", \"label\": \"%s\", \"scores\": {\"neg\": %.3f, \"neu\": %.3f, \"pos\": %.3f, \"compound\": %.3f}}",
                    escapeJson(text), label, neg, neu, pos, compound);
        }
    }

    static class SentimentAnalyzer {
        private static final Set<String> POSITIVE = createSet(
                "good", "great", "excellent", "awesome", "love", "loved", "amazing",
                "happy", "fantastic", "best", "nice", "positive", "wonderful", "perfect",
                "enjoy", "enjoyed", "like", "liked", "brilliant", "delightful", "pleasant",
                "superb", "beautiful", "fabulous", "smile", "smiled", "strong", "confident",
                "success", "successful", "win", "winning", "excited", "exciting", "enjoyable"
        );

        private static final Set<String> NEGATIVE = createSet(
                "bad", "terrible", "awful", "hate", "hated", "worst", "sad", "angry",
                "disappointing", "disappointed", "poor", "negative", "angst", "pain", "hurt",
                "unhappy", "upset", "frustrated", "annoyed", "boring", "fail", "failure",
                "broken", "ugly", "mess", "difficult", "problem", "issue", "worse", "hateable"
        );

        AnalysisResult analyze(String text) {
            String normalized = text.toLowerCase(Locale.ROOT);
            String[] tokens = normalized.split("[^a-z']+");
            int positiveCount = 0;
            int negativeCount = 0;
            int totalCount = 0;

            for (String token : tokens) {
                if (token.isBlank()) {
                    continue;
                }
                totalCount++;
                if (POSITIVE.contains(token)) {
                    positiveCount++;
                } else if (NEGATIVE.contains(token)) {
                    negativeCount++;
                }
            }

            double pos = 0.0;
            double neg = 0.0;
            double neu = 1.0;
            double compound = 0.0;

            if (totalCount > 0) {
                pos = (double) positiveCount / totalCount;
                neg = (double) negativeCount / totalCount;
                compound = (double) (positiveCount - negativeCount) / totalCount;
                neu = Math.max(0.0, 1.0 - pos - neg);
            }

            String label;
            if (compound >= 0.05) {
                label = "Positive";
            } else if (compound <= -0.05) {
                label = "Negative";
            } else {
                label = "Neutral";
            }

            return new AnalysisResult(text, label, neg, neu, pos, compound);
        }

        private static Set<String> createSet(String... values) {
            Set<String> set = new HashSet<>();
            for (String value : values) {
                set.add(value);
            }
            return set;
        }
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
