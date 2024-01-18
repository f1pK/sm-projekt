package com.example.zadanie_3;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import com.example.zadanie_3.R;

public class WeatherActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private Sensor pressureSensor;

    private TextView temperatureTextView;
    private TextView pressureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Inicjalizacja widoków
        temperatureTextView = findViewById(R.id.temperatureTextView);
        pressureTextView = findViewById(R.id.pressureTextView);

        // Inicjalizacja menedżera sensorów
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Inicjalizacja czujników temperatury i ciśnienia atmosferycznego
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Rejestracja listenera dla czujników
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Wyrejestrowanie listenera przy pauzie aktywności
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Obsługa zmiany wartości czujników
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperatureValue = sensorEvent.values[0];
            temperatureTextView.setText("Temperatura: " + temperatureValue + "°C");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float pressureValue = sensorEvent.values[0];
            pressureTextView.setText("Ciśnienie atmosferyczne: " + pressureValue + " hPa");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Obsługa zmiany dokładności czujnika (nie używane w tym przykładzie)
    }
}