package notifier.bus.stand.register_stand_impl;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.controller.register.TokenRegister;
import notifier.bus.stand.register_stand_impl.db.Db;
import org.springframework.stereotype.Service;

@Service
public class TokenRegisterStandImpl implements TokenRegister{

    private Db db;

    public TokenRegisterStandImpl(Db db) {
        this.db = db;
    }

    @Override
    public void saveToken(String token) {
        db.tokens.add(token);
    }
}
