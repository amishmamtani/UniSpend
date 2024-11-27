package app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarketHealthService {
    private static final String API_URL = "https://api.api-ninjas.com/v1/inflation?country=Canada";
    private static final String API_KEY = System.getenv("API_NINJA_KEY");

    private static Double mockMarketHealth = null;

    // Method to set a mock market health value for testing
    public static void setMockMarketHealth(double mockHealth) {
        mockMarketHealth = mockHealth;
    }

    // Method to reset the mock value
    public static void resetMockMarketHealth() {
        mockMarketHealth = null;
    }

    public static double getEconomicIndicator() {
        // If a mock value is set, use it for testing
        if (mockMarketHealth != null) {
            return mockMarketHealth;
        }

        // Otherwise, fetch the actual market health from the API
        return fetchMarketHealthFromApi();
    }

    private static double fetchMarketHealthFromApi() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.setRequestProperty("Accept", "application/json");

            int status = connection.getResponseCode();
            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return parseEconomicIndicator(response.toString());
            } else {
                System.out.println("Error: " + status + " " + connection.getResponseMessage());
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.1;
    }

    private static double parseEconomicIndicator(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);

        // Loop through the array to retrieve inflation data
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject inflationData = jsonArray.getJSONObject(i);

            // Return the yearly inflation rate percentage
            return inflationData.getDouble("yearly_rate_pct");
        }

        // Default return in case no valid data is found
        return 2.1;
    }
}
