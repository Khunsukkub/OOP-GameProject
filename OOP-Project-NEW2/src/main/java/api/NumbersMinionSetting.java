package api;

import exception.BaseException;
import exception.UserException;
import model.GameState;
import model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class NumbersMinionSetting {

    @PostMapping("/api/NumbersMinionSetting")
    public ResponseEntity<Void> NumbersMinionSetting(@RequestBody String NumberOfMinion) throws BaseException {
        return null;
    }
}
