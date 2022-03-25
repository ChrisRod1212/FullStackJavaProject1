package com.revature;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.SessionController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Driver {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(9001);

        UserController userController = new UserController();
        ReimbursementController reimbursementController = new ReimbursementController();
        SessionController sessionController = new SessionController();

        app.routes(() -> {
            path("allreimbursements/user/{author}", () -> {
                get(reimbursementController::getAllReimbursementsGivenAuthor);
            });
            path("allreimbursements/", () -> {
                get(reimbursementController::getAllReimbursements);
                path("{reimbid}", () -> {
                    patch(reimbursementController::resolveReimbursement);
                });
            });
            path("allreimbursements/status/{status}/", () -> {
                get(reimbursementController::getAllReimbursementsGivenStatus);
                path("{reimbid}", () -> {
                    patch(reimbursementController::resolveReimbursement);
                });
            });
            path("reimbursement/{reimbId}", () -> {
                get(reimbursementController::getReimbursementGivenReimbId);
                patch(reimbursementController::resolveReimbursement);

            });
            path("session", () -> {
                post(sessionController::login);
                get(sessionController::checkSession);
                delete(sessionController::logout);
            });
            post("/register", userController::createUser);
            post("/newreimbursement",reimbursementController::createReimbursement);
        });

    }
}
