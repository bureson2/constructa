package cz.cvut.fel.constructa.security;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.nio.file.AccessDeniedException;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PermissionController {
    private final AuthenticationFacade authenticationFacade;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/api/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPermission() throws AccessDeniedException, JSONException {

        Collection<? extends GrantedAuthority> authorities = authenticationFacade.getAuthentication().getAuthorities();
        String permission = authorities.toArray()[0].toString();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("permission", permission);

        return ResponseEntity.ok().body(jsonObject.toString());
    }
}
