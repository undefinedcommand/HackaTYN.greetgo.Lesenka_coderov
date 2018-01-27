package notifier.bus.controller.controller;

import notifier.bus.controller.model.BusInfo;
import notifier.bus.controller.register.BusRegister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class BusController {

    private BusRegister busRegister;

    public BusController(BusRegister busRegister) {
        this.busRegister = busRegister;
    }

    @RequestMapping(value = "/rest/bus/list")
    public List<BusInfo> createAuthenticationToken() {
        return busRegister.getBusesList();
    }
}
