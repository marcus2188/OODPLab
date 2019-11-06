package boundary;

import controller.MovieTop5_inf;
import controller.Movie_admin_inf;
import controller.MovieScreening_inf;
import controller.SystemSettings_inf;
import java.io.IOException;

import controller.*;

public interface Admin_UI extends Movie_mg_inf, Movie_admin_inf, MovieTop5_inf{
    public void printMenu();
    public void importData() throws IOException;
}
