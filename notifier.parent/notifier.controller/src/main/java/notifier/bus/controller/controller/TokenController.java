package notifier.bus.controller.controller;

import notifier.bus.controller.register.TokenRegister;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    private TokenRegister tokenRegister;

    public TokenController(TokenRegister tokenRegister) {
        this.tokenRegister = tokenRegister;
    }

    @CrossOrigin
    @RequestMapping(value = "/rest/token", method = RequestMethod.POST)
    public void getToken(@RequestParam(value = "token")String token) {
        tokenRegister.saveToken(token);
    }

}
