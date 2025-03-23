package api;

import exception.BaseException;
import exception.RoomSearchingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/game")
public class RoomSearching {

    @PostMapping("/api/roomSearching")
    public ResponseEntity<Void> roomSearching(@RequestBody String input) throws BaseException {

        if (input == null) throw RoomSearchingException.idNull();
        if (input == "1234") throw RoomSearchingException.notFound();

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/" + input)
                .build();
    }
}