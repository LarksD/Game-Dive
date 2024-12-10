import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIRequests {

    private static final String API_KEY = ""; //Coloque sua chave de API aqui


    public static void searchGame(String queryParam, String queryValue) {
        try {
            String urlString = "https://api.rawg.io/api/games?key=" + API_KEY + "&" + queryParam + "=" + queryValue + "&page_size=5";
            String response = makeApiRequest(urlString);
            if (response != null) {
                List<Game> games = parseGames(response);
                new SearchResultsWindow(games);
            } else {
                System.out.println("Erro ao recuperar dados da API.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Game> parseGames(String response) {
        List<Game> games = new ArrayList<>();
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray results = jsonResponse.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject gameJson = results.getJSONObject(i);
            String name = gameJson.getString("name");
            String releaseYear = gameJson.optString("released", "Ano não encontrado.");
            double rating = gameJson.optDouble("rating", 0.0);
            String img = gameJson.optString("background_image", "Imagem não disponível.");
            int gameId = gameJson.getInt("id");

            // Puxar os detalhes do jogo utilizando o ID do jogo
            String description = fetchGameDetails(gameId);

            Game game = new Game(name, rating, description, releaseYear, img);
            games.add(game);
        }
        return games;
    }


    private static String makeApiRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Game fetchGame(String queryParam, String queryValue) {
        try {
            String urlString = "https://api.rawg.io/api/games?key=" + API_KEY + "&" + queryParam + "=" + queryValue + "&page_size=1";
            String response = makeApiRequest(urlString);
            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray games = jsonResponse.getJSONArray("results");
                if (games.length() > 0) {
                    JSONObject gameJson = games.getJSONObject(0);
                    String name = gameJson.getString("name");
                    String releaseYear = gameJson.optString("released", "Ano não encontrado.");
                    double rating = gameJson.optDouble("rating", 0.0);
                    String img = gameJson.optString("background_image", "Imagem não disponível.");
                    int gameId = gameJson.getInt("id");

                    // puxar os detalhes do jogo utilizando o ID do jogo
                    String description = fetchGameDetails(gameId);

                    return new Game(name, rating, description, releaseYear, img);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metado para buscar detalhes do jogo
    private static String fetchGameDetails(int gameId) {
        try {
            String urlString = "https://api.rawg.io/api/games/" + gameId + "?key=" + API_KEY;
            String response = makeApiRequest(urlString);
            if (response != null) {
                JSONObject gameDetails = new JSONObject(response);
                return gameDetails.optString("description", "Descrição não disponível.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Descrição não disponível.";
    }
}