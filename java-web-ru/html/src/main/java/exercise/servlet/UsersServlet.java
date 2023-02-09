package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

@WebServlet("/users/*")
public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path path = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        String content = Files.readString(path);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> users;
        users = mapper.readValue(content, new TypeReference<List<Map<String, String>>>() { });
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
            <!DOCTYPE html>
            <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <title>Example application | Users</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                          rel="stylesheet"
                          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                          crossorigin="anonymous">
                </head>
                <body>
                    <div class="container">
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>User name</th>
                            </tr>
            """);

        users.forEach(user -> body.append("""
                            <tr>
                                <td>%s</td>
                                <td><a href="/users/%s">%s</a></td>
                            </tr>""".formatted(user.get("id"), user.get("id"), user.get("firstName") + " " + user.get("lastName"))));

        body.append("""
                        </table>
                    </div>
                </body>
            </html>
            """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        Optional<Map<String, String>> optUser;
        optUser = users.stream().filter(userMap -> userMap.get("id").equals(id)).findFirst();
        if (optUser.isEmpty()) {
            response.sendError(404);
        } else {
            Map<String, String> user = optUser.get();
            StringBuilder body = new StringBuilder();
            body.append("""
            <!DOCTYPE html>
            <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <title>Example application | Users</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                          rel="stylesheet"
                          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                          crossorigin="anonymous">
                </head>
                <body>
                    <div class="container">
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Email</th>
                            </tr>
            """);
            body.append("""
                            <tr>
                                <td>%s</td>
                                <td>%s</td>
                                <td>%s</td>
                                <td>%s</td>
                            </tr>""".formatted(user.get("id"), user.get("firstName"), user.get("lastName"), user.get("email")));

            body.append("""
                        </table>
                    </div>
                </body>
            </html>
            """);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(body.toString());
        }
        // END
    }
}
