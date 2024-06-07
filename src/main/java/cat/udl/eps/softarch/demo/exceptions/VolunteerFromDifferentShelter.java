package cat.udl.eps.softarch.demo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "The volunteers must be in the same shelter")
public class VolunteerFromDifferentShelter extends RuntimeException { }

