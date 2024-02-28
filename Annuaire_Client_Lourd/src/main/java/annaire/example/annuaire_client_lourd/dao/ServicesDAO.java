package annaire.example.annuaire_client_lourd.dao;

import annaire.example.annuaire_client_lourd.models.ServicesDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class ServicesDAO {

    public static ServicesDTO getServiceById(int id) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/services/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // On spécifie l'encodage UTF-8 pour que les accents soient gérés et s'affichent bien dans le client lourd
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("Erreur de réponse de l'API. Code : ").append(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.append("Erreur lors de l'appel de l'API : ").append(e.getMessage());
        }
        ServicesDTO apiServise = parseJsonString(response.toString());
        return apiServise;
    }

    private static ServicesDTO parseJsonString(String jsonString) {
        try {
            // Utilisation de Jackson ObjectMapper pour mapper la chaîne JSON vers un objet Java
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ServicesDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Gérer les erreurs de parsing ici
            return null;
        }
    }

    public List<ServicesDTO> getAllServices() {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/services");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)); // Spécifiez UTF-8 comme encodage
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("Erreur de réponse de l'API. Code : ").append(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.append("Erreur lors de l'appel de l'API : ").append(e.getMessage());
        }

        return parseJsonArray(response.toString());
    }
    private List<ServicesDTO> parseJsonArray(String jsonArray) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonArray, new TypeReference<List<ServicesDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static ServicesDTO getServicesById(int servicesId) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/services/"+servicesId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // On spécifie l'encodage UTF-8 pour que les accents soient gérés et s'affichent bien dans le client lourd
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("Erreur de réponse de l'API. Code : ").append(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.append("Erreur lors de l'appel de l'API : ").append(e.getMessage());
        }
        ServicesDTO apiServices = parseJsonString(response.toString());
        return apiServices;
    }

    public static String createServices(String table, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5678/api/v1/" + table))
                .setHeader("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public static String updateServices(int id, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5678/api/v1/services/" + id))
                .setHeader("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public static void deleteServices(int servicesId) {
        try {
            URL url = new URL("http://localhost:5678/api/v1/services/" + servicesId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // La suppression a réussi
                System.out.println("Service supprimé avec succès.");
            } else {
                System.out.println("Erreur de réponse de l'API. Code : " + responseCode);
            }

            // Ferme la connexion
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO Gérer les erreurs ici
        }
    }
}
