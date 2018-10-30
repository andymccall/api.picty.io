package io.picty.api.controller;

import io.picty.api.Application;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// TODO: Fix mapping via properties file
//@RequestMapping("${controller.api.request-mapping-base}")
@RequestMapping("api/v1/")
public class ApiController {

    private static final Logger logger =
            LoggerFactory.getLogger(ApiController.class);


    @ApiOperation(value = "Read the build number ", notes = "Will return details of the current build number")
    @RequestMapping(value = "/build", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    ResponseEntity<String> getBuild() {

        ResponseEntity response;

        String buildDetails = Application.class.getPackage().getImplementationVersion();

        response = new ResponseEntity<>(buildDetails, HttpStatus.OK);

        return response;
    }

}
