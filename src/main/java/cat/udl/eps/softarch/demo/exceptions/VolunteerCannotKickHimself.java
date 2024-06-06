package cat.udl.eps.softarch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "No te puedes expulsar a ti mismo")
public class VolunteerCannotKickHimself extends RuntimeException { }