package org.pdm.ib.pmt.router.controls;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource {

    @PostMapping
    public Boolean authorize(User user) {

    }
}
