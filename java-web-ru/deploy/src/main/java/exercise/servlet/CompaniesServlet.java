package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {
        // BEGIN
        List<String> allCompanies = getCompanies();
        List<String> searchedCompanies = new ArrayList<>();
        PrintWriter writer = response.getWriter();
        StringJoiner sj = new StringJoiner("\n");
        String searchParamValue = request.getParameter("search");
        if (searchParamValue != null && !searchParamValue.equals("")) {
            searchedCompanies = getCompanies().stream()
                                              .filter(name -> name.contains(searchParamValue))
                                              .collect(Collectors.toList());
            if (searchedCompanies.size() > 0) {
                searchedCompanies.forEach(sj::add);
            } else {
                sj.add("Companies not found");
            }
            writer.write(sj.toString());
        } else {
            allCompanies.forEach(sj::add);
            writer.write(sj.toString());
        }
        // END
    }
}





















