package org.udg.pds.springtodo.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.udg.pds.springtodo.controller.exceptions.ControllerException;
import org.udg.pds.springtodo.entity.IdObject;
import org.udg.pds.springtodo.serializer.JsonDateDeserializer;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RequestMapping(path="/group")
@RestController
public class GroupController extends BaseController {

    @PostMapping(consumes = "application/json")
    public IdObject addGroup(HttpSession session, @Valid @RequestBody GroupController.R_Group group) {

        Long userId = getLoggedUser(session);

        if (task.text == null) {
            throw new ControllerException("No text supplied");
        }
        if (task.dateCreated == null) {
            throw new ControllerException("No creation date supplied");
        }
        if (task.dateLimit == null) {
            throw new ControllerException("No limit date supplied");
        }

        return groupService.addTask(task.text, userId, task.dateCreated, task.dateLimit);
    }

    static class R_Group {

        @NotNull
        public String text;

        @NotNull
        @JsonDeserialize(using = JsonDateDeserializer.class)
        public Date dateCreated;

        @NotNull
        @JsonDeserialize(using = JsonDateDeserializer.class)
        public Date dateLimit;
    }
}
