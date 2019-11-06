package boundary;

import java.io.IOException;

import controller.*;

public interface Admin_UI extends SystemSettings_inf, Movie_mg_inf, Movie_admin_inf, MovieTop5_inf{
    public void printMenu();
    public void importData() throws IOException;
}
