package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.client.MainApp;


public class AdminCopyrightsController {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
    private static final Logger LOGGER = Logger.getLogger(AdminCopyrightsController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at AdminController";

    /**
     * Handles going to the add buildings page for the admin.
     */
    public void goToAddBuildings() {
        try {
            MainApp.goToPage("admin-addBuilding");
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddBuildings()");
        }
    }

    /**
     * Handles going to the delete buildings page for the admin.
     */
    public void goToDeleteBuildings() {
        try {
            MainApp.goToPage("admin-deleteBuilding");
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToDeleteBuildings()");
        }
    }

    /**
     * Handles going to the add rooms page for the admin.
     */
    public void goToAddRooms() {
        try {
            MainApp.goToPage("admin-addRoom");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddRooms()");
        }
    }

    /**
     * Handles going to the delete room page for the admin.
     */
    public void goToDeleteRooms() {
        try {
            MainApp.goToPage("admin-deleteRoom");
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToDeleteRooms()");
        }
    }

    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            MainApp.goToPage("reservations");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }

    /**
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToLogIn()");
        }
    }

    /**
     * Handles going to the copyrights.
     */
    public void goToCopyrights() {
        try {
            MainApp.goToPage("copyrights");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToCopyrights()");
        }
    }

    /**
     * Handles going to the admin homepage.
     */
    public void goToAdmin() {
        try {
            MainApp.goToPage("admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
