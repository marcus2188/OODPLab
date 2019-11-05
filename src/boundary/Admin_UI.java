package boundary;

import controller.MovieTop5_inf;
import controller.Movie_admin_inf;
import controller.MovieScreening_inf;
import controller.SystemSettings_inf;
import java.io.IOException;

public interface Admin_UI extends MovieTop5_inf, Movie_admin_inf, SystemSettings_inf, MovieScreening_inf{
    public void printMenu();
    public void importData() throws IOException;
}
