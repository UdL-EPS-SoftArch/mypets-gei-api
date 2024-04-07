package cat.udl.eps.softarch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Bad post request")
public class InvalidPostRequest extends RuntimeException {}