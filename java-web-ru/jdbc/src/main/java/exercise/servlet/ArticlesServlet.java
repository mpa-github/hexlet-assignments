package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        String page = request.getParameter("page");
        int pageValue = (page == null || page.equals("0")) ? 1 : Integer.parseInt(page);
        int itemsOnPage = 10;
        int offset = (pageValue - 1) * itemsOnPage;
        String sql1 = "SELECT id, title, body FROM articles ORDER BY id LIMIT 10 OFFSET ?;";
        String sql2 = "SELECT COUNT(*) FROM articles;";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> article = new HashMap<>(3);
                article.put("id", resultSet.getString("id"));
                article.put("title", resultSet.getString("title"));
                article.put("body", resultSet.getString("body"));
                articles.add(article);
            }
            resultSet.close();
            resultSet = connection.createStatement().executeQuery(sql2);
            int itemsCount = resultSet.next() ? resultSet.getInt(1) : 0;
            int maxPage = (itemsCount % itemsOnPage == 0) ? itemsCount / itemsOnPage : (itemsCount / itemsOnPage) + 1;
            request.setAttribute("articles", articles);
            request.setAttribute("page", pageValue);
            request.setAttribute("maxPage", maxPage);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String articleID = getAction(request);
        Map<String, String> article = new HashMap<>(3);
        String sql = "SELECT id, title, body FROM articles WHERE id=?;";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(articleID));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            article.put("id", resultSet.getString("id"));
            article.put("title", resultSet.getString("title"));
            article.put("body", resultSet.getString("body"));
            request.setAttribute("article", article);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
