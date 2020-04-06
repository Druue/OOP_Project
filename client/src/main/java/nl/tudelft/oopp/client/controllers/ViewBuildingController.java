package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.tudelft.oopp.client.MainApp;


public class ViewBuildingController {

    private static final Logger LOGGER = Logger.getLogger(ViewBuildingController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at ViewBuilding";


    /**
     * Handles going to the mainScene.
     */
    public void goToAdmin() {
        try {
            MainApp.goToPage("admin", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToMain()");
        }
    }

    /**
     * Handles going to the room page for the admin.
     */
    public void goToAdminRoom() {
        try {
            MainApp.goToPage("admin-room", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAdminRoom()");
        }
    }

    /**
     * Handles going to the add buildings page for the admin.
     */
    public void goToAddBuildings() {
        try {
            MainApp.goToPage("admin-addBuilding", null);
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddBuildings()");
        }
    }

    /**
     * Handles going to the add rooms page for the admin.
     */
    public void goToAddRooms() {
        try {
            MainApp.goToPage("admin-addRoom", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddRooms()");
        }
    }

    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            MainApp.goToPage("reservations", "reservations");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }

    /**
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login", "login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }
}
