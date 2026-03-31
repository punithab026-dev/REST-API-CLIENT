import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApp {
    public static void main(String[] args) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=12.9716&longitude=77.5946&current_weather=true";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            // Extract only current_weather block
            String currentWeather = body.split("\"current_weather\":\\{")[1].split("\\}")[0];

            String temp = currentWeather.split("\"temperature\":")[1].split(",")[0];
            String wind = currentWeather.split("\"windspeed\":")[1].split(",")[0];
            String time = currentWeather.split("\"time\":\"")[1].split("\"")[0];

            System.out.println("===== Weather Report =====");
            System.out.println("Location: Bangalore");
            System.out.println("Time: " + time);
            System.out.println("Temperature: " + temp + " °C");
            System.out.println("Wind Speed: " + wind + " km/h");
            System.out.println("=========================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
