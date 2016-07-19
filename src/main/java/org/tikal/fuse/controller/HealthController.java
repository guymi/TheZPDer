package org.tikal.fuse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guy on 7/19/16.
 */

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping(method = RequestMethod.GET)
    public boolean health() {
        return true;
    }
}
