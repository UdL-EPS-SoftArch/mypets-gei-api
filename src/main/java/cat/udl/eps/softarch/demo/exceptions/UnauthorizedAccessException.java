package cat.udl.eps.softarch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Unauthorized access")
public class UnauthorizedAccessException extends RuntimeException {}
