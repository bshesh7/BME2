package Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bme_003.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HomeFragment extends Fragment {

    String ip ="";
    TextView astrodataView2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //Text views
        astrodataView2 = view.findViewById(R.id.astrodataView2);
        //for API call
        DownloadTask task = new DownloadTask();
        task.execute("https://api.ipify.org?format=json");


        return view;
    }
    public class DownloadTask extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data!=-1){
                    char current= (char) data;
                    result +=current;
                    data = reader.read();
                }
                return result;


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Log.i("JSON",s);
            try {
                //Log.i("JSON",s);
                JSONObject jsonObject = new JSONObject(s);
                String ipInfo = jsonObject.getString("ip");
                Log.i("ip :: ",ipInfo);
                DownloadTask2 task2 = new DownloadTask2();
                task2.execute("https://api.ipgeolocation.io/astronomy?apiKey=876e2dc0a8624653a7d243e0cb8e1257&ip="+ipInfo+"&lang=cn");



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class DownloadTask2 extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Log.i("JSON",s);
            try {
                Log.i("JSON", s);
                String astroInfo = "";
                JSONObject jsonObject = new JSONObject(s);
                String sunriseInfo = jsonObject.getString("sunrise");
                String sunsetInfo = jsonObject.getString("sunset");
                String moon_altitude_string = jsonObject.getString("moon_altitude");

                //
                double moon_altitude = Double.parseDouble(moon_altitude_string);

                int scaling_factor = 0;
                double c = 0;
                double e = 0;
                double jd = 0;
                double b = 0;
                int day = 2;
                int year = 2020;
                int month = 6;
                if (month < 3) {
                    year--;
                    month += 12;
                }
                ++month;
                c = 365.25 * year;
                e = 30.6 * month;
                jd = c + e + day - 694039.09;
                jd = jd / 29.5305882;
                b = Math.floor(jd);
                jd = jd - b;
                b = Math.round(jd * 8);

                if (b >= 8) {
                    b = 0;
                }
                if (b == 0) {
                    scaling_factor = 10;
                } else if (b == 1) {
                    scaling_factor = 7;
                } else if (b == 2) {
                    scaling_factor = 2;
                } else if (b == 3) {
                    scaling_factor = 7;
                } else if (b == 4) {
                    scaling_factor = 10;
                } else if (b == 5) {
                    scaling_factor = 7;
                } else if (b == 6) {
                    scaling_factor = 2;
                } else if (b == 7) {
                    scaling_factor = 7;
                }
                double pi = 3.14;
                double radian_angle = (pi / 180) * (90 - moon_altitude);
                double tidal_potential = ((3 * Math.cos(radian_angle) * Math.cos(radian_angle)) - 1) / 2;
                double lunar_meter = tidal_potential * scaling_factor;
                String lunar_scale = "";

                if (lunar_meter > -5 && lunar_meter < -3.5) {
                    lunar_scale = "Lunar meter : 1";
                } else if (lunar_meter > -3.5 && lunar_meter < -2) {
                    lunar_scale = "Lunar meter : 2";
                } else if (lunar_meter > -2 && lunar_meter < -0.5) {
                    lunar_scale = "Lunar meter : 3";
                } else if (lunar_meter > -0.5 && lunar_meter < 1) {
                    lunar_scale = "Lunar meter : 4";
                } else if (lunar_meter > 1 && lunar_meter < 2.5) {
                    lunar_scale = "Lunar meter : 5";
                } else if (lunar_meter > 2.5 && lunar_meter < 4) {
                    lunar_scale = "Lunar meter : 6";
                } else if (lunar_meter > 4 && lunar_meter < 5.5) {
                    lunar_scale = "Lunar meter : 7";
                } else if (lunar_meter > 5.5 && lunar_meter < 7) {
                    lunar_scale = "Lunar meter : 8";
                } else if (lunar_meter > 7 && lunar_meter < 8.5) {
                    lunar_scale = "Lunar meter : 9";
                } else if (lunar_meter > 8.5 && lunar_meter < 10) {
                    lunar_scale = "Lunar meter : 10";
                }
                if (!sunriseInfo.equals("") && !sunsetInfo.equals("")) {
                    astroInfo += "  Sunrise: " + sunriseInfo + "   Sunset: " + sunsetInfo + "  " + lunar_scale;
                }

                if (!astroInfo.equals("")) {
                    astrodataView2.setText(astroInfo);
                }
                //
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
