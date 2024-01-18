package com.example.zadanie_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LokalizacjaActivity extends AppCompatActivity {

    private TextView cityTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView countryTextView;
    private TextView populationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokalizacja);

        // Initialize TextViews
        cityTextView = findViewById(R.id.cityTextView);
        latitudeTextView = findViewById(R.id.latitudeTextView);
        longitudeTextView = findViewById(R.id.longitudeTextView);
        countryTextView = findViewById(R.id.countryTextView);
        populationTextView = findViewById(R.id.populationTextView);

        // Fetch location information
        new GetLocationInfoTask().execute("Białystok");
    }

    private class GetLocationInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cityName = params[0];
            return getLocationInfo(cityName);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Process the result and update TextViews
            updateLocationViews(result);
        }

        private String getLocationInfo(String cityName) {
            try {
                // Insert your API key
                String apiKey = "pMToD98MDVpNcc/CcLBlSA==h4ECtGfaugN1Sekv";
                URL url = new URL("https://api.api-ninjas.com/v1/city?name=" + cityName + "&limit=1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("X-Api-Key", apiKey);
                connection.setRequestProperty("accept", "application/json");

                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);

                // Get location information
                JsonNode cityInfo = root.get(0);
                String city = cityInfo.path("name").asText();
                double latitude = cityInfo.path("latitude").asDouble();
                double longitude = cityInfo.path("longitude").asDouble();
                String country = cityInfo.path("country").asText();
                int population = cityInfo.path("population").asInt();

                return "City: " + city + "\nLatitude: " + latitude + "\nLongitude: " + longitude +
                        "\nCountry: " + country + "\nPopulation: " + population;
            } catch (IOException e) {
                e.printStackTrace();
                return "Error occurred: " + e.getMessage();
            }
        }

        private void updateLocationViews(String result) {
            // Update TextViews based on the result
            String[] parts = result.split("\n");
            if (parts.length >= 5) {
                cityTextView.setText(parts[0]);
                latitudeTextView.setText(parts[1]);
                longitudeTextView.setText(parts[2]);
                countryTextView.setText(parts[3]);
                populationTextView.setText(parts[4]);
            } else {
                Log.e("Location Task", "Invalid result format");
            }
        }
    }
}
